package br.faj.padaria.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.faj.padaria.domain.model.Grupoacesso;
import br.faj.padaria.domain.model.Usuario;

@Repository
public interface GrupoacessoRepository extends JpaRepository<Grupoacesso, Long> {

	List<Grupoacesso> findByUsuarios(Usuario usuario);
}
