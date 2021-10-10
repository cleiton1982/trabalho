package br.com.gamestore.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.gamestore.dominio.Carrinho;
import br.com.gamestore.dominio.Produto;
import br.com.gamestore.repository.ProdutoRepository;
import br.com.gamestore.share.Cookies;
import br.com.gamestore.share.UploadeFiles;
import io.swagger.annotations.ApiOperation;

@RestController

@CrossOrigin("*")
@RequestMapping("/api")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private UploadeFiles uploadeFiles;
	@Autowired
	private Cookies cookies;

	@ApiOperation(value = "Cadastrar novo produto")
	@PostMapping("/produto")
	public ResponseEntity<Produto> novo(@Valid Produto form) {
		Produto pd = null;
		try {
			pd = produtoRepository.save(form);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return new ResponseEntity<>(pd, HttpStatus.OK);

	}

	@RequestMapping(value = "/produtos")
	@ApiOperation(value = "Listar produtos, ordenação opcional.")
	/**
	 * 
	 * @param orderByNome,            paramentro para ordenar por nome (asc,desc)
	 * @param orderByPreco,           paramentro para ordenar por preço (asc,desc)
	 * @param orderByScore,paramentro para ordenar por score (asc,desc)
	 * @return
	 */
	public ResponseEntity<Collection<Produto>> listar(@RequestParam Optional<String> orderByNome,
			@RequestParam Optional<String> orderByPreco, @RequestParam Optional<String> orderByScore) {
		System.out.println("nome ordenacao " + orderByNome);
		List<Order> orders = new ArrayList<Order>();
		Order orderName = null;
		Order orderPreco = null;
		Order orderScore;
		if (orderByNome.isPresent()) {
			System.out.println("nome ordenacao valor " + orderByNome.get());
			if (orderByNome.get().equalsIgnoreCase("asc")) {
				orderName = new Order(Sort.Direction.ASC, "name");
			} else {
				orderName = new Order(Sort.Direction.DESC, "name");
			}

			orders.add(orderName);
		}
		if (orderByPreco.isPresent()) {
			if (orderByPreco.get().equalsIgnoreCase("asc")) {
				orderPreco = new Order(Sort.Direction.ASC, "price");
			} else {
				orderPreco = new Order(Sort.Direction.DESC, "price");
			}

			orders.add(orderPreco);
		}
		if (orderByScore.isPresent()) {
			if (orderByScore.get().equalsIgnoreCase("asc")) {
				orderScore = new Order(Sort.Direction.ASC, "score");
			} else {
				orderScore = new Order(Sort.Direction.DESC, "score");
			}

			orders.add(orderScore);
		}

		Collection<Produto> produtosListado = (Collection<Produto>) produtoRepository.findAll(Sort.by(orders));
		return new ResponseEntity<>(produtosListado, HttpStatus.OK);
	}

	@PostMapping("/uploadimg")
	@ApiOperation(value = "upload da imagem do produto")
	/**
	 * 
	 * @param file recebe um arquivo como paramentro
	 * @return retorna o caminho temporario da imagem
	 */
	public String uploadImg(@RequestPart("file") MultipartFile file) {
		uploadeFiles.upload(file);
		return file.getOriginalFilename();
	}

	@PostMapping("/addcar/{idProduto}")
	@ApiOperation(value = "adicionar produto no carrinho")
	/**
	 * 
	 * @param idProduto     id do produto
	 * @param jsonCarrinho, cookie do corrinho
	 * @param response
	 * @return retorna o carrinho populado
	 */
	public String adicionarProdutoAoCarrinho(@PathVariable Long idProduto,
			@CookieValue("cat") Optional<String> jsonCarrinho, HttpServletResponse response) {
		Carrinho carrinho = Carrinho.criar(jsonCarrinho);
		Produto p = produtoRepository.findById(idProduto).get();
		carrinho.adicionar(p);
		Cookie cookie = cookies.writeAsJson("cat", carrinho, response);
		try {
			response.addCookie(cookie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return carrinho.toString();
	}

	@PutMapping("/removecar/{idProduto}")
	@ApiOperation(value = "Remover produto do carrinho")
	public String removerProdutoCarrinho(@PathVariable Long idProduto,
			@CookieValue("cat") Optional<String> jsonCarrinho, HttpServletResponse response) {
		Carrinho carrinho = Carrinho.criar(jsonCarrinho);
		Produto p = produtoRepository.findById(idProduto).get();
		System.out.println(carrinho);
		carrinho.remover(p);
		Cookie cookie = cookies.writeAsJson("cat", carrinho, response);
		try {
			response.addCookie(cookie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "remover";
	}
}
