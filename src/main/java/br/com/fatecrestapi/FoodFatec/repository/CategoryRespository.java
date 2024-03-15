package br.com.fatecrestapi.FoodFatec.repository;

import br.com.fatecrestapi.FoodFatec.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRespository extends JpaRepository<Category,Long> {
}
