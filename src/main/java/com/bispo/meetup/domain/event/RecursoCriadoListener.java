package com.bispo.meetup.domain.event;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent event) {
		HttpServletResponse resp = event.getResponse();
		Long cod = event.getCodigo();
		
		adicionarHeaderLocation(resp, cod);
	}

	private void adicionarHeaderLocation(HttpServletResponse resp, Long cod) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
				path("/{codigo}").
				buildAndExpand(cod).
				toUri();
		resp.setHeader("Location", uri.toASCIIString());
		
	}

}
