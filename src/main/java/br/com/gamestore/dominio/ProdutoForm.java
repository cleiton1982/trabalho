package br.com.gamestore.dominio;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;

import br.com.gamestore.share.UploadeFiles;


public class ProdutoForm {
	@NotBlank
	private String name;
	@NotBlank
    private BigDecimal price;
	@NotBlank
    private int score;
	@NotNull
    private MultipartFile image;
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
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public Produto ProdutoFormNovo(UploadeFiles uploadeFiles) {
		String urlImg=uploadeFiles.upload(image);
		
		return new Produto(null,name,price,score,urlImg);
	}
	public ProdutoForm(@NotBlank String name, @NotBlank BigDecimal price, @NotBlank int score, MultipartFile image) {
		super();
		this.name = name;
		this.price = price;
		this.score = score;
		this.image = image;
	}
}
