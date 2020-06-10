package br.faj.padaria.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.faj.padaria.domain.model.Categoriaproduto;

public interface CategoriaprodutoRepository extends JpaRepository<Categoriaproduto, Long> {

}
