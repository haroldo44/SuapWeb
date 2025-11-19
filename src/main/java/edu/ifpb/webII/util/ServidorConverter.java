package edu.ifpb.webII.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import edu.ifpb.webII.model.Servidor;
import edu.ifpb.webII.model.service.ServidorService;

public class ServidorConverter implements Converter<String, Servidor> {
	
	@Autowired
	private ServidorService servidorService;
	
	@Override
	public Servidor convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long codigo = Long.valueOf(text);
		return servidorService.listarServidor(codigo);
	}

}
