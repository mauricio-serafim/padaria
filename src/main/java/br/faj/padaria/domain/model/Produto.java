package br.faj.padaria.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Produto extends AbstractEntityWithID {

	@NonNull
	@NotNull
	@Column(length = 50, nullable = false)
	private String codigo;

	@NonNull
	@NotNull
	@Column(length = 120, nullable = false)
	private String descricao;

	@NotNull
	@Column(length = 50, nullable = false)
	private String gtin;

	@ManyToOne
	private Unidade unidade;

	@ManyToOne
	private Categoriaproduto categoria;

	private Boolean comercializavel;

	private Boolean vendaLiberada;
}
