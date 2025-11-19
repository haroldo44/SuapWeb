package edu.ifpb.webII.model;

import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="Curso")
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="curso_seq")
    @SequenceGenerator(name="curso_seq", sequenceName="curso_seq", allocationSize=1)
    private Long codigo;

    @NotBlank(message = "Informe o nome do curso")
    @Size(min = 4, max = 50, message = "O nome do curso deve ter entre {min} e {max} caracteres")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Informe a área do curso")
    @Size(min = 4, max = 50, message = "A área do curso deve ter entre {min} e {max} caracteres")
    @Column(nullable = false)
    private String area;

    // GETTERS E SETTERS
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    // EQUALS & HASHCODE
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Curso other = (Curso) obj;
        return Objects.equals(codigo, other.codigo);
    }
}
