package com.controlefinanceiro.dosmoros.service;


import java.util.Collection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controlefinanceiro.dosmoros.dto.UsuarioDTO;
import com.controlefinanceiro.dosmoros.model.Permissao;
import com.controlefinanceiro.dosmoros.model.Usuario;

import com.controlefinanceiro.dosmoros.repository.Usuarios;

@Service
@Transactional
public class UsuariosService implements UserDetailsService {

	@Autowired
	private Usuarios usuarios;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserDetails loadUserByUsername(String username) {
		Usuario usuario = usuarios.findByUsername(username);
		
		if(usuario == null)
			throw new UsernameNotFoundException(username);
			
			return new org.springframework.security.core.userdetails.User(usuario.getUsername(),
					usuario.getPassword(), getAuthorities(usuario));
		
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
		// TODO Auto-generated method stub
		String[] usuarioPermissoes = usuario.getPermissoes().stream().map((permissao) -> permissao.getName()).toArray(String[]::new);
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(usuarioPermissoes);
		return authorities;
	}
	
	public void salvar(Usuario usuario) {
		
		usuario.setUsername(usuario.getUsername());
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setNome(usuario.getNome());
		usuario.setEmail(usuario.getEmail());
		usuario.setPermissoes((List<Permissao>) usuario.getPermissoes());
		usuario.setVisibilidade(usuario.getVisibilidade());
		usuarios.save(usuario);
	}
	
	public void remover(Long id) {
		usuarios.deleteById(id);
	}

	public void editar(Usuario usuario) {
		usuario.setNome(usuario.getNome());
		usuario.setEmail(usuario.getEmail());
		usuario.setPermissoes((List<Permissao>) usuario.getPermissoes());
		
		usuarios.save(usuario);
	}
	
	public Page<Usuario> porUsername(String username, String nome, Pageable pageable){
		return usuarios.porUsername(username, nome, pageable);
	}
	
	public List<UsuarioDTO> filtradas(String username){
		return usuarios.filtradas(username);
	}
	
	public int usuarioId(String name) {
		return usuarios.usuarioId(name);
	}
	
	public int usuarioVisibilidade(int id) {
		return usuarios.usuarioVisibilidade(id);
	}
	
	public List<Usuario> findAll(){
		return usuarios.findAll();
	}
}
