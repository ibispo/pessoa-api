package com.bispo.meetup.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(length = 100)
	private String nome;
	
	@NotBlank
	@Column(length = 20)
	private String cpf;

	@Valid
	@JsonIgnoreProperties(value = "pessoa")
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contato> contatos = new ArrayList<>();
	
	
}
