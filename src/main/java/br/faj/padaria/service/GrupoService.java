package br.faj.padaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.faj.padaria.domain.model.Grupoacesso;
import br.faj.padaria.domain.view.GrupoacessoView;
import br.faj.padaria.persistence.repository.GrupoacessoRepository;

@Service
@Transactional
public class GrupoService {

	@Autowired
	private GrupoacessoRepository repository;

	// Consulta os grupos cadastrados
	@Transactional(readOnly = true)
	public List<GrupoacessoView> consultarGrupos() {
		List<GrupoacessoView> views = new ArrayList<>();

		List<Grupoacesso> entities = this.repository.findAll();

		entities.forEach(grupoacesso -> views.add(new GrupoacessoView(grupoacesso)));

		return views;
	}
}
