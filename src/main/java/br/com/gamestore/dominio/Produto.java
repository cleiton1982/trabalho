package br.com.gamestore.dominio;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
    private String name;
    private BigDecimal price;
    private int score;
    private String image;

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
	@Deprecated
	public Produto() {
		super();
	}
	public Produto(Long id, String name, BigDecimal price, int score, String image) {
	
		this.id = id;
		this.name = name;
		this.price = price;
		this.score = score;
		this.image = image;
	}
	
}
