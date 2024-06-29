package br.com.fatecrestapi.FoodFatec.repository;


import br.com.fatecrestapi.FoodFatec.dto.ProductDTO;
import br.com.fatecrestapi.FoodFatec.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products,Long> {



}
