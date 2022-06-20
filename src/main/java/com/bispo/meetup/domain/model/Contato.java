package com.bispo.meetup.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false, length = 100)
	private String nome;
	
	@NotBlank
	@Column(nullable = false, length = 20)
	private String telefone;

	@Email
	@NotBlank
	@Column(nullable = false, length = 60)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
	
}

