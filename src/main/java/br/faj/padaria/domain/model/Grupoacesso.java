package br.faj.padaria.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "grupoacesso")
public class Grupoacesso extends AbstractEntityWithID {

	@NonNull
	@NotNull
	@Column(length = 60, nullable = false)
	private String codigo;

	@NonNull
	@NotNull
	@Column(length = 255, nullable = false)
	private String descricao;

	@NonNull
	@NotNull
	@Column(length = 50, nullable = false)
	private String nome;

	@ManyToMany
	@JoinTable(name = "grupo_permissao", joinColumns = {
			@JoinColumn(referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(referencedColumnName = "id") })
	private List<Permissao> permissoes;

	@ManyToMany
	@JoinTable(name = "usuario_grupoacesso", joinColumns = {
			@JoinColumn(referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(referencedColumnName = "id") })
	private List<Usuario> usuarios;
}
