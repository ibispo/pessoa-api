package com.bispo.meetup.domain.model;

import com.bispo.meetup.api.validator.Cpf;

import lombok.Getter;

@Getter
public class PessoaDTO {

    @Cpf
    private String cpf;

}
