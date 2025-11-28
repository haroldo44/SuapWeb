package edu.ifpb.webII.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="perfil")
public class Perfil {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="usuario_seq")
    @SequenceGenerator(name="usuario_seq", sequenceName="usuario_seq", allocationSize=1)
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@ManyToMany(mappedBy = "perfis")
	@JsonIgnore
	private List<Usuario> usuarios;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
