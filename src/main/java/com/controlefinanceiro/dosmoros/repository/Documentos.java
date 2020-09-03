package com.controlefinanceiro.dosmoros.repository;

//import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.controlefinanceiro.dosmoros.model.Documento;

@Repository
public interface Documentos extends JpaRepository<Documento, Long>{
	
	@Query(value = "insert into documento (nm_doc, nm_link, conta_id) VALUES "
			+ " (:name,:link,:conta)", nativeQuery = true)
	@Transactional
	@Modifying
	void saveFile(@Param("name") String name, @Param("link") String link, @Param("conta") Long conta);
	
	@Query(value = "select nm_doc from documento where conta_id = " +"(:conta)", nativeQuery=true)
	String verDocs(@Param("conta") Long conta);
	
	@Query(value="select concat(nm_link , '/', nm_doc) url from documento where conta_id = " + "(:conta)", nativeQuery=true)
	String url(@Param("conta") Long conta);
	
	@Query(value="delete from documento where conta_id = " + "(:conta)", nativeQuery=true)
	@Transactional
	@Modifying
	void deleteFile(@Param("conta")Long conta);
	
}
