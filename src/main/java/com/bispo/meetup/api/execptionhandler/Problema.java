package com.bispo.meetup.api.execptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Problema {

	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Campo> campos;
	
	@Getter
	public static class Campo {
		
		private String campo;
		private String mensagem;
		
		public Campo( String cam, String msg) {
			this.campo = cam;
			this.mensagem = msg; 
		}
		
	}
	
}
