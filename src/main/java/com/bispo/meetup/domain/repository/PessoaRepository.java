package com.bispo.meetup.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bispo.meetup.domain.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
}
