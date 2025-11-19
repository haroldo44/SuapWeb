package edu.ifpb.webII.util;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import edu.ifpb.webII.model.Curso;
import edu.ifpb.webII.model.service.CursoService;

@Component
public class CursoConverter implements Converter<String, Curso> {
	
	@Autowired
	private CursoService cursoService;
	
	@Override
	public Curso convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long codigo = Long.valueOf(text);
		return cursoService.listarCurso(codigo);
	}

}
