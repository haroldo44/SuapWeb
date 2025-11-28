package edu.ifpb.webII.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="usuario")
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="usuario_seq")
    @SequenceGenerator(name="usuario_seq", sequenceName="usuario_seq", allocationSize=1)
	private Long id;
	
	@Column(name="username", unique = true, nullable = false)
	private String username;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="senha_hash")
	private String senha;
	
	@Column(name="status")
	private String status;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToMany
	@JoinTable(
			name = "usuario_perfil",
			joinColumns = { @JoinColumn(name = "id_usuario")},
			inverseJoinColumns = { @JoinColumn(name = "id_perfil")}
			)
	private List<Perfil> perfis;
	
	public long getId() {
		return id;
	}
	
}
