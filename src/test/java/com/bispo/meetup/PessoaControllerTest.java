package com.bispo.meetup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bispo.meetup.domain.model.Pessoa;
import com.bispo.meetup.domain.repository.PessoaRepository;
import com.bispo.meetup.domain.service.PessoaService;

// @WebMvcTest


@RunWith(SpringRunner.class)
public class PessoaControllerTest {

	@TestConfiguration
	static class PessoaServiceTestConfigurator {
		
		@Bean
		public PessoaService pessoaService() {
			return new PessoaService();
		}
		
	}
	
	/*
	 * Não podemos usar a implementação real, pois essa contém acesso a base de dados anyway...
	 * Se o teste fosse assim, teriamos o cenário de "teste integrado", o que difere de "teste unitário".
	 * --------------
	 * Por isso "MOCK". 
	 */
	@Autowired
	private PessoaService pessoaService;
	
	@MockBean
	PessoaRepository pessoaRepository;
	
	@Test
	public void testePesquisa() {
		
		Pessoa p = new Pessoa();
		p.setCpf("987.654.231-00"); // 140.156.358-92
		
		Pessoa pAchado = this.pessoaService.buscarPessoa("987.654.231-00");
		
		System.out.println("================================");
		System.out.println(p);
		System.out.println("---------------");
		System.out.println(pAchado);
		
		
		assertEquals(p.getCpf(), pAchado.getCpf());
		
		
	}
	
	@Before
	public void setup() {
		
		Pessoa pessoa = new Pessoa(); 
		
		pessoa.setCpf("987.654.231-00");
		
		Mockito.when(pessoaRepository.findByCpf(pessoa.getCpf()))
			.thenReturn(Optional.of(pessoa));		
		
	}
	
}
