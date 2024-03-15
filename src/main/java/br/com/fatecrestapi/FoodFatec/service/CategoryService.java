package br.com.fatecrestapi.FoodFatec.service;

import br.com.fatecrestapi.FoodFatec.entity.Category;

import br.com.fatecrestapi.FoodFatec.repository.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRespository categoryRespository;

    public boolean validationCategory(Category category){
        if (category.getNameCategory() !=null && category.getDescriptionCategory() != null &&
                category.getNameCategory().equals("") && category.getDescriptionCategory().equals("") && category.getNameCategory().length()>=3 &&
                category.getDescriptionCategory().length()>=3){
                return true;
        }
        else {
            return false;
        }
    }

    public List<Category> getInfoCategory(){
        return categoryRespository.findAll();
    }

    public  Category savecategory(Category category){
        if(validationCategory(category)){
            return categoryRespository.saveAndFlush(category);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Os dados da categoria está incompeto ");
        }
    }

    public HashMap<String ,Object> deleteCategory(Long idCategory){
        Optional<Category> category =
                Optional.of(categoryRespository.findById(idCategory)).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Categoria não encontrada"));

        categoryRespository.delete(category.get());
        HashMap<String,Object> result = new HashMap<>();
        result.put("result","categoria: "+category.get().getNameCategory()+
                " "+category.get().getDescriptionCategory()+"categoria excluida com sucesso");
        return  result;
    }

    public  Optional<Category> findCategoryById(Long idCategory){
        return Optional.ofNullable(categoryRespository.findById(idCategory)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoria não encontrada")));
    }

    public Category updateCategory(Category category){
        if(validationCategory(category)){
            if(category.getIdCategory()!=null){
                return categoryRespository.saveAndFlush(category);
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Categoria não encontrada");
            }

        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A categoria está incompleta");
        }
    }

}
