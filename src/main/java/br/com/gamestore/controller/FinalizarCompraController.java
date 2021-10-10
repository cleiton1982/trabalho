package br.com.gamestore.controller;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gamestore.dominio.Carrinho;
import br.com.gamestore.dominio.ClienteForm;
import br.com.gamestore.dominio.Compra;
import br.com.gamestore.dominio.ItenCompra;
import br.com.gamestore.repository.CompraRepository;
import br.com.gamestore.repository.ProdutoRepository;

@RestController

@CrossOrigin("*")
public class FinalizarCompraController {
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CompraRepository compraRepository;

	@PostMapping(value = "/carrinho/finalizar")
	public String finalizarCompra(@Valid ClienteForm form, @CookieValue("cat") String jsonCarrinho) {
		Carrinho carrinho = Carrinho.criar(Optional.of(jsonCarrinho));
		Set<ItenCompra> itensCompra = carrinho.gerarItensCompra(produtoRepository);
		Compra novaCompra = form.novaCompra(itensCompra);

		compraRepository.save(novaCompra);
		return novaCompra.toString();
	}
}
