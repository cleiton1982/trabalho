package br.com.gamestore.dominio;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String documento;
	@NotNull
	private String endereco;
	@ElementCollection
	@Size(min = 1)
	private Set<ItenCompra> itensCompra = new HashSet<>();
	@PastOrPresent
	private LocalDateTime localDateTime = LocalDateTime.now();

	public Compra(@NotNull String nome, @NotNull @Email String email, @NotNull String documento,
			@NotNull String endereco, @Size(min = 1) Set<ItenCompra> itensCompra) {
		this.nome = nome;
		this.email = email;
		this.documento = documento;
		this.endereco = endereco;
		this.itensCompra.addAll(itensCompra);
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", nome=" + nome + ", email=" + email + ", documento=" + documento + ", endereco="
				+ endereco + ", itensCompra=" + itensCompra + ", localDateTime=" + localDateTime + "]";
	}

}
