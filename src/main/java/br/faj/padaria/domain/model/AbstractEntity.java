package br.faj.padaria.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = -7069730068704929380L;

	@NotNull
	@Column(nullable = false)
	private Boolean ativo = Boolean.TRUE;

	@NotNull
	@CreatedDate
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date criadoEm = new Date();

	@NotNull
	@LastModifiedDate
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificadoEm = new Date();

	private Boolean getAtivo() {
		return this.ativo;
	}

	public Boolean isAtivo() {
		return this.getAtivo();
	}
}
