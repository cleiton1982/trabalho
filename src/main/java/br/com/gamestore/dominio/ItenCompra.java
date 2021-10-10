package br.com.gamestore.dominio;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@Embeddable
public class ItenCompra {
	private Long id;
	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;
	@NotNull
	@Positive
	private int quantidade;
	@NotNull
	@Positive
	private BigDecimal price;
	@NotNull
	@Positive
	private BigDecimal total;
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ItenCompra(@NotNull @Valid Produto produto, @NotNull @Positive int quantidade,
			@NotNull @Positive BigDecimal price, @NotNull @Positive BigDecimal total) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
		this.price = price;
		this.total = total;
		this.id=produto.getId();
	}
	@Override
	public String toString() {
		return "ItenCompra [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", price=" + price
				+ ", total=" + total + "]";
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
		ItenCompra other = (ItenCompra) obj;
		return Objects.equals(id, other.id);
	}
	@Deprecated
	public ItenCompra() {
		
	}



}
