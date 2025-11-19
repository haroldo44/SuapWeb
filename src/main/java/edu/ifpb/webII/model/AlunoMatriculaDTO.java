package edu.ifpb.webII.model;

import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class AlunoMatriculaDTO {

    @NotNull(message = "A matrícula do aluno é obrigatória.")
    @Positive(message = "A matrícula deve ser um número válido.")
    private Long matricula;

    @NotBlank(message = "O período é obrigatório.")
    @Pattern(regexp = "^\\d{4}\\.[1-2]$", message = "Formato inválido. Use AAAA.1 ou AAAA.2")
    private String periodo;

    @NotEmpty(message = "Selecione pelo menos uma disciplina.")
    private List<Long> disciplinas;

    public Long getMatricula() { return matricula; }
    public void setMatricula(Long matricula) { this.matricula = matricula; }

    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }

    public List<Long> getDisciplinas() { return disciplinas; }
    public void setDisciplinas(List<Long> disciplinas) { this.disciplinas = disciplinas; }
}
