package edu.ifpb.webII.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.ifpb.webII.model.Curso;
import edu.ifpb.webII.model.Disciplina;
import edu.ifpb.webII.model.service.CursoService;
import edu.ifpb.webII.model.service.DisciplinaService;

@Component
public class DisciplinaConverter implements Converter<String, Disciplina> {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Override
	public Disciplina convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long codigo = Long.valueOf(text);
		return disciplinaService.listarDisciplina(codigo);
	}

}
