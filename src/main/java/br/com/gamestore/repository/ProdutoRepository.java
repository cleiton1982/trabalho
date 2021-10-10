package br.com.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gamestore.dominio.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
