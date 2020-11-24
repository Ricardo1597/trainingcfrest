package com.accenture.trainingcf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.trainingcf.domain.ProductsEntity;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, String>{
	
	
	public List<ProductsEntity> findByNameOrderByName(String name);
	
	// He knows we want to find by x and order by y in descendent order just by the name
	public List<ProductsEntity> findByManufacturerOrderByNameDesc(String manufacturer);
	public List<ProductsEntity> findByManufacturerAndNameOrderByNameDesc(String manufacturer, String name);

	// Not very fast but it works
	@Query("Select k from ProductsEntity as k where k.name like :keyword or k.manufacturer like :keyword")
    public List<ProductsEntity> findByKeyword(@Param("keyword") String keyword);
	
	@Query("SELECT k FROM ProductsEntity k WHERE function('contains', k.name , :keyword, function('fuzzy', 0.5)) = function('contains_rhs') or function('contains', k.manufacturer , :keyword, function('fuzzy', 0.5)) = function('contains_rhs')")
	public List<ProductsEntity> findByKeywordWithFuzzy(@Param("keyword") String keyword);
}
