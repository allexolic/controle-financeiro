package com.controlefinanceiro.dosmoros.repository;

import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.controlefinanceiro.dosmoros.dto.UsuarioDTO;
import com.controlefinanceiro.dosmoros.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long>{

	Usuario findByUsername(String username);
	
	@Query(value = "select u from Usuario u where lower(u.username) like lower(concat('%', concat(?1,'%')))"
			+ " or (lower(u.nome) like lower(concat('%', concat(?2,'%') ) )) order by"
			+ " u.id")
	Page<Usuario> porUsername(String username, String nome, Pageable pageable);
	
	@Query("select new com.controlefinanceiro.dosmoros.dto.UsuarioDTO(u.id, u.username) from Usuario u "
			+ " where u.username like %?1%")
	List<UsuarioDTO> filtradas(String username);
	
	/*
	@Query(value="update usuario set nm_usuario = " + "(:nome)" + " where "
			+ " id = " + "(:id)", nativeQuery=true)
	
	@Transactional
	@Modifying
	void editarUsuario(@Param("id") Long usuario, @Param("nome")String nome);
	*/
}


