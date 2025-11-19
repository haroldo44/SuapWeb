package edu.ifpb.webII.model;

import java.util.Objects;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="PROFESSOR")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="professor_seq")
    @SequenceGenerator(name="professor_seq", sequenceName="professor_seq",allocationSize=1)
    private Long matricula;

    @NotBlank(message = "Informe o nome do professor")
    @Size(min = 3, max = 40, message="O nome deve ter entre {min} e {max} caracteres")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Nome deve conter apenas letras")
    @Column(name = "NOME", length = 40, nullable = false)
    private String nome;

    @NotBlank(message = "Informe a área do professor")
    @Size(min = 3, max = 40, message="A área deve ter entre {min} e {max} caracteres")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Área deve conter apenas letras")
    @Column(name = "AREA", length = 40, nullable = false)
    private String area;

    public Long getMatricula() {
        return matricula;
    }
    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Professor other = (Professor) obj;
        return Objects.equals(matricula, other.matricula);
    }
}
