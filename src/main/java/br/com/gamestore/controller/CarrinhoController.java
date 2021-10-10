package br.com.gamestore.controller;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gamestore.dominio.Carrinho;
import br.com.gamestore.dominio.Produto;
import br.com.gamestore.repository.ProdutoRepository;
import br.com.gamestore.share.Cookies;

@RestController

@CrossOrigin("*")
public class CarrinhoController {
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private Cookies cookies;

	@PostMapping("/api/updateTocat/{idProduto}/update")
	public void atualizarCarrinho(@PathVariable Long idProduto, @RequestParam int novaQuantidade,
			@CookieValue("cat") String jsonCarrinho, HttpServletResponse response) {
		System.out.println("id produto " + idProduto + " Quantidade nova " + jsonCarrinho);
		Carrinho carrinho = Carrinho.criar(Optional.of(jsonCarrinho));
		System.out.println("lista para atualizar " + carrinho.getLista());
		Produto produto = produtoRepository.findById(idProduto).get();
		carrinho.atualiza(produto, novaQuantidade);

		Cookie cookie;
		try {
			cookie = cookies.writeAsJson("cat", carrinho, response);
			response.addCookie(cookie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
