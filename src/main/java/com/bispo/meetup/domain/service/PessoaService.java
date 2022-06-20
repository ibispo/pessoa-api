package com.bispo.meetup.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bispo.meetup.domain.exception.EntidadeNaoEncontradaException;
import com.bispo.meetup.domain.model.Pessoa;
import com.bispo.meetup.domain.repository.PessoaRepository;

@Service
public class PessoaService {

	private PessoaRepository pessoaRepository;

	@Autowired
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	// GET
	public List<Pessoa> listarPessoa() {
		return this.pessoaRepository.findAll();
	}
	
	// GET
	public Pessoa buscarPessoa(Long id) {
		return findOrFail(id);
	}
	
	// POST
	public Pessoa salvarPessoa(Pessoa p) {
		p.getContatos().
			forEach(c -> c.setPessoa(p));
		return this.pessoaRepository.save(p);
	}

	// PUT
	public Pessoa atualizarPessoa(Long id, Pessoa p) {

		Pessoa pSalva = findOrFail(id);
		pSalva.getContatos().clear();
		pSalva.getContatos().addAll(p.getContatos());
		
		BeanUtils.copyProperties(p, pSalva, "id", "contatos");
		
		return salvarPessoa(pSalva); 
	}

	// DELETE
	public void deletarPessoa(Long id) {
		Pessoa pDeleta = findOrFail(id);
		this.pessoaRepository.delete(pDeleta);
	}
	
	private Pessoa findOrFail(Long id) {
		return this.pessoaRepository.findById(id).
			orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não localizada"));
	}
	
}
