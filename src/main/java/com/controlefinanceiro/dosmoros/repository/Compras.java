package com.controlefinanceiro.dosmoros.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.controlefinanceiro.dosmoros.model.Compra;




@Repository
public interface Compras extends JpaRepository<Compra, Long>{

	
	//@Query("select c from Compra c where ifnull(c.nmEstabelecimento,'') like %?1% order by c.codigo desc")Query Mysql
	//lower(nullif(coalesce(c.nmEstabelecimento,null,'ND'),'')) like lower(concat('%', concat(?1,'%')))
	@Query("select c from Compra c inner join Estabelecimento e on e.id = c.estabelecimento "
			+ " where c.usuarioCadastro = coalesce(nullif(?2,0), c.usuarioCadastro, ?2) "
			+ " and lower(nullif(coalesce(e.nmEstabelecimento,null,'ND'),'')) "
			+ " like lower(concat('%', concat(?1,'%'))) order by c.codigo desc")
	Page<Compra> porLoja(String nmEstabelecimento, int visibilidade, Pageable pageable);
	//List<Compra> porLoja(String nmEstabelecimento);
	

	@Query(value = "select count(c) from Compra c where month(c.dataCompra) = month(current_date)"
			+ " AND YEAR(dataCompra) = YEAR(CURRENT_DATE)")
	long countPorMes();
	
	@Query("select sum(c.valorCompra) from Compra c where month(c.dataCompra) = month(current_date)"
			+ " AND YEAR(dataCompra) = YEAR(CURRENT_DATE)")
	String sumCompras();
	
	
	@Query(value="select * from Compra_inserir(:dsjson)", nativeQuery=true)
	int webComprasInserir(String dsjson);
	
	
	@Query(value="select to_char(date_part('month',dt_compra),'99')mes " + 
			"  from compra " + 
			" group by date_part('month',dt_compra), date_part('year', dt_compra) " + 
			" order by  date_part('year', dt_compra) desc, date_part('month',dt_compra) desc", nativeQuery=true)
	List<String> totalComprasMes();

	@Query(value="select to_char(date_part('year', dt_compra),'9999')ano" + 
			"  from compra " + 
			" group by date_part('month',dt_compra), date_part('year', dt_compra) " + 
			" order by  date_part('year', dt_compra)desc , date_part('month',dt_compra) desc", nativeQuery=true)
	List<String> totalComprasAno();

	@Query(value="select count(*)total" + 
			"  from compra " + 
			" group by date_part('month',dt_compra), date_part('year', dt_compra) " + 
			" order by  date_part('year', dt_compra) desc, date_part('month',dt_compra) desc", nativeQuery=true)
	List<Integer> totalComprasQtd();	
	
	@Query(value="select nm_produto from produtos_mais_comprados", nativeQuery=true)
	List<String> produtosMaisComprados();
	
	@Query(value="select total from produtos_mais_comprados", nativeQuery=true)
	List<Integer> totalProdutosMaisComprados();
}
