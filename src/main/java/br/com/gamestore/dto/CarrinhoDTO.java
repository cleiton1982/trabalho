package br.com.gamestore.dto;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.gamestore.dominio.ItenCompra;
import br.com.gamestore.dominio.Produto;
import br.com.gamestore.repository.ProdutoRepository;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarrinhoDTO {
	private Long id;
	private String name;
	private BigDecimal price;
	private int score;
	private String image;
	private int quantidade = 1;

	public CarrinhoDTO(Produto produto) {
		this.name = produto.getName();
		this.price = produto.getPrice();
		this.score = produto.getScore();
		this.image = produto.getImage();
		this.id = produto.getId();
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "CarrinhoDTO [name=" + name + ", price=" + price + ", score=" + score + ", image=" + image + "]";
	}

	@Deprecated
	public CarrinhoDTO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoDTO other = (CarrinhoDTO) obj;
		return Objects.equals(id, other.id);
	}

	public void icrementaQuantidade() {
		this.quantidade++;

	}

	public void decrementar() {
		this.quantidade -= 1;
	}

	public BigDecimal getTotal() {
		return price.multiply(new BigDecimal(quantidade));
	}

	public void atualizaQuantidade(@Positive int novaQuantidade) {
		this.quantidade = novaQuantidade;

	}

	public ItenCompra novoItemCompra(ProdutoRepository produtoRepository) {
		// (@NotNull @Valid Produto produto, @NotNull @Positive int quantidade,
		// @NotNull @Positive BigDecimal price, @NotNull @Positive BigDecimal total)
		return new ItenCompra(produtoRepository.findById(this.id).get(), this.quantidade, this.price, this.getTotal());
	}
}
