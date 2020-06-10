package br.faj.padaria.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.faj.padaria.domain.model.Categoriaproduto;

@Repository
public interface CategoriaprodutoRepository extends JpaRepository<Categoriaproduto, Long> {

}
