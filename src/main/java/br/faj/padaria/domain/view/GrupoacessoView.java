package br.faj.padaria.domain.view;

import java.util.Date;

import br.faj.padaria.domain.model.Grupoacesso;
import lombok.Data;

@Data
public class GrupoacessoView {

	private Boolean ativo;
	private String codigo;
	private Date criadoEm;
	private Long id;
	private Date modificadoEm;
	private String nome;

	public GrupoacessoView(Grupoacesso g) {
		this.ativo = g.isAtivo();
		this.codigo = g.getCodigo();
		this.criadoEm = g.getCriadoEm();
		this.id = g.getId();
		this.modificadoEm = g.getModificadoEm();
		this.nome = g.getNome();
	}
}
