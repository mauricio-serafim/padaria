package br.faj.padaria.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "permissao")
public class Permissao extends AbstractEntityWithID {

	@NonNull
	@NotNull
	@Column(length = 50, nullable = false)
	private String codigo;

	@NonNull
	@NotNull
	@Column(length = 255, nullable = false)
	private String descricao;

	@ManyToMany(mappedBy = "permissoes")
	private List<Grupoacesso> gruposacesso;

	@NonNull
	@NotNull
	@Column(length = 60, nullable = false)
	private String nome;
}
