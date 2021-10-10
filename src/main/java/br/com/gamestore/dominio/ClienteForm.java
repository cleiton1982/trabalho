package br.com.gamestore.dominio;

import java.util.Set;

import javax.validation.constraints.Email;

import com.sun.istack.NotNull;

public class ClienteForm {
	@NotNull
	private String nome;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String documento;
	@NotNull
	private String endereco;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Compra novaCompra(Set<ItenCompra> itensCompra) {
		// TODO Auto-generated method stub
		return new Compra(this.nome,this.email,this.documento,this.endereco,itensCompra);
	}
}
