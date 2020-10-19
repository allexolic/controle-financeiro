package com.controlefinanceiro.dosmoros.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name="usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nm_login", nullable=false, length=10, unique=true)
	private String username;
	
	@Column(name="ds_senha", nullable=false, updatable=false)
	private String password;
	
	@Column(name="nm_usuario", length=175)
	private String nome;
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(name="usuario_permissao",
			   joinColumns= {@JoinColumn(name="usuario_id", referencedColumnName="id")},
			   inverseJoinColumns= {@JoinColumn(name="permissao_id", referencedColumnName="id")})
	private List<Permissao> permissoes;

	@Column(name="nm_email", length=175)
	private String email;
	
	@Column(name="id_visibilidade")
	private int visibilidade;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getVisibilidade() {
		return visibilidade;
	}

	public void setVisibilidade(int visibilidade) {
		this.visibilidade = visibilidade;
	}
	
	
}
