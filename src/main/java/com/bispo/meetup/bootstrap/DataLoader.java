package com.bispo.meetup.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bispo.meetup.domain.model.Contato;
import com.bispo.meetup.domain.model.Pessoa;
import com.bispo.meetup.domain.service.PessoaService;

@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private PessoaService pessoaService;
	
	@Override
	public void run(String... args) throws Exception {
		saveData();
	}

	private void saveData() {

		Pessoa p = new Pessoa();
		p.setNome("Joao da Silva");
		p.setCpf( "987.654.231-00" );
		
		Contato c = new Contato();
		c.setEmail("email@email.com");
		c.setNome("Nome do contato");
		c.setTelefone("789 456 123");
		
		p.getContatos().add(c);
		
		pessoaService.salvarPessoa(p);
	}

	
	
}
