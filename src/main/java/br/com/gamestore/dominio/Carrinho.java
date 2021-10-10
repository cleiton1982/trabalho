package br.com.gamestore.dominio;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gamestore.dto.CarrinhoDTO;
import br.com.gamestore.repository.ProdutoRepository;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Carrinho {

	Set<CarrinhoDTO> lista = new LinkedHashSet<>();

	public void adicionar(Produto produto) {
		CarrinhoDTO novoItem = new CarrinhoDTO(produto);
		boolean result = lista.add(novoItem);
		if (!result) {
			CarrinhoDTO itemExistente = lista.stream().filter(novoItem::equals).findFirst().get();
			itemExistente.icrementaQuantidade();

		}
	}

	public void remover(Produto produto) {
		CarrinhoDTO novoItem = new CarrinhoDTO(produto);
		CarrinhoDTO itemExistente = lista.stream().filter(novoItem::equals).findFirst().get();
		if (itemExistente.getQuantidade() > 1) {
			itemExistente.decrementar();
		} else {
			lista.remove(novoItem);
		}

	}

	public Set<CarrinhoDTO> getLista() {
		return lista;
	}

	public void setLista(Set<CarrinhoDTO> lista) {
		this.lista = lista;
	}

	public static Carrinho criar(Optional<String> jsonCarrinho) {
		return jsonCarrinho.map(json -> {
			try {
				return new ObjectMapper().readValue(json, Carrinho.class);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}).orElse(new Carrinho());

	}

	public void atualiza(@NotNull Produto p, @Positive int novaQuantidade) {

		CarrinhoDTO possivelProduto = new CarrinhoDTO(p);

		Optional<CarrinhoDTO> possivelItem = lista.stream().filter(possivelProduto::equals).findFirst();
		if (possivelItem.isPresent()) {
			// System.out.println(possivelItem.get());
			CarrinhoDTO produtoCapturado = possivelItem.get();
			produtoCapturado.atualizaQuantidade(novaQuantidade);
		} else {
			System.out.println("nao existe esse produto na lista");
		}

	}

	public BigDecimal getPrecoTotal() {
		return lista.stream().map(item -> item.getTotal()).reduce(BigDecimal.ZERO,
				(atual, proximo) -> atual.add(proximo));

	}

	public BigDecimal getFrete() {
		int contador = 0;
		for (CarrinhoDTO carrinhoDTO : lista) {
			contador += carrinhoDTO.getQuantidade();
		}
		BigDecimal frete2 = new BigDecimal("10.00");
		BigDecimal frete = getPrecoTotal();
		if (frete.intValue() > 250) {
			return frete2 = new BigDecimal("0.00");
		} else {
			return frete2.multiply(new BigDecimal(contador));
		}

	}

	@Override
	public String toString() {
		return "Carrinho [lista=" + lista + "]";
	}

	public Set<ItenCompra> gerarItensCompra(ProdutoRepository produtoRepository) {
		return this.lista.stream().map(itemCarrinho -> {
			return itemCarrinho.novoItemCompra(produtoRepository);
		}).collect(Collectors.toSet());

	}

}
