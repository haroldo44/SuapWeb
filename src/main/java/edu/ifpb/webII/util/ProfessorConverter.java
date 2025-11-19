package edu.ifpb.webII.util;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import edu.ifpb.webII.model.Professor;
import edu.ifpb.webII.model.service.ProfessorService;

@Component
public class ProfessorConverter implements Converter<String, Professor> {
	
	@Autowired
	private ProfessorService professorService;
	
	@Override
	public Professor convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long matricula = Long.valueOf(text);
		return professorService.listarProfessor(matricula);
	}

}
