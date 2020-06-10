package br.faj.padaria.domain.view;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import br.faj.padaria.domain.model.Usuario;
import lombok.Data;

@Data
public class UsuarioView {

	private Boolean ativo;
	private Date criadoEm;

	@NotEmpty(message = "Não existe nenhum grupo selecionado")
	private List<Long> gruposacesso;
	private Long id;
	private Date modificadoEm;

	@NotEmpty(message = "O nome é de preenchimento obrigatório")
	private String nome;

	@NotEmpty(message = "A senha é de preenchimento obrigatório")
	private String senha;

	public UsuarioView(Usuario u) {
		this.ativo = u.isAtivo();
		this.criadoEm = u.getCriadoEm();
		this.id = u.getId();
		this.modificadoEm = u.getModificadoEm();
		this.nome = u.getNome();
		this.senha = u.getSenha();
		this.gruposacesso = this.getGruposacesso();
	}

	private Boolean getAtivo() {
		return this.ativo;
	}

	public Boolean isAtivo() {
		return this.getAtivo();
	}
}
