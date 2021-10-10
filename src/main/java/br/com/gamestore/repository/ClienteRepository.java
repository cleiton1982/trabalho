package br.com.gamestore.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.gamestore.dominio.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{

}
