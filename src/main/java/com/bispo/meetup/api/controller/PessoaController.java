package com.bispo.meetup.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bispo.meetup.domain.event.RecursoCriadoEvent;
import com.bispo.meetup.domain.model.Pessoa;
import com.bispo.meetup.domain.model.PessoaDTO;
import com.bispo.meetup.domain.service.PessoaService;

@RestController
@RequestMapping(path = "/pessoa", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	public ApplicationEventPublisher publisher;

	@GetMapping
	public List<Pessoa> listarPessoa() {
		return this.pessoaService.listarPessoa();
	}

	/**
	 * buscar pessoa
	 * 
	 * @param idP
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscarPessoa(@PathVariable(name = "id") Long idP) {
		return ResponseEntity.ok(this.pessoaService.buscarPessoa(idP));
	}

	/**
	 * Buscar pessoa pelo CPF
	 * 
	 * @param cpfP
	 * @return {@link ResponseEntity}
	 */
	@GetMapping("/cpf")
	public ResponseEntity<Pessoa> buscarPessoa(@Validated @RequestBody PessoaDTO p) {
		return ResponseEntity.ok(this.pessoaService.buscarPessoa(p.getCpf()));
	}

	/**
	 * Metodo que salva pessoa
	 * 
	 * @param pSalva
	 * @param resp
	 * @return {@link ResponseEntity}
	 */
	@PostMapping
	public ResponseEntity<Pessoa> salvarPessoa(@Validated @RequestBody Pessoa pSalva, HttpServletResponse resp) {
		Pessoa p = this.pessoaService.salvarPessoa(pSalva);
		publisher.publishEvent(new RecursoCriadoEvent(this, resp, p.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(p);
	}

	/**
	 * Metodo que atualiza pessoa
	 * 
	 * @param id
	 * @param pessoa
	 * @return {@link ResponseEntity}
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		return ResponseEntity.ok(this.pessoaService.atualizarPessoa(id, pessoa));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagarPessoa(@PathVariable Long id) {
		this.pessoaService.deletarPessoa(id);
	}

}
