package br.com.fatecrestapi.FoodFatec.service;


import br.com.fatecrestapi.FoodFatec.dto.ProductDTO;
import br.com.fatecrestapi.FoodFatec.entity.Products;
import br.com.fatecrestapi.FoodFatec.repository.CategoryRepository;
import br.com.fatecrestapi.FoodFatec.repository.ProductsRepository;
import br.com.fatecrestapi.FoodFatec.util.ConverterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ConverterData converterData;

    @Autowired
    private CategoryService categoryService;


    public Boolean validationProducts(Products products) {

        if (products.getDescriptionProduct() != null &&
                products.getAmountProduct().compareTo(BigDecimal.valueOf(0)) >= 0 && products.getSkuProduct() != null &&
                products.getEanProduct() != null &&
                products.getCostPriceProduct().compareTo(BigDecimal.valueOf(0)) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Products> getInfoProducts() {
        return productsRepository.findAll();
    }



    public Products saveProducts(ProductDTO productDTO) {

        Products product = new Products();
        product.setIdProduct(Long.valueOf(productDTO.getIdProduct()));
        product.setNameProduct(productDTO.getNameProduct());
        product.setDescriptionProduct(productDTO.getDescriptionProduct());
        product.setEanProduct(productDTO.getEanProduct());
        product.setSkuProduct(productDTO.getSkuProduct());

        product.setCostPriceProduct(converterData.convertingStringtoBigDecimal(productDTO.getCostPriceProduct()));
        product.setAmountProduct(converterData.convertingStringtoBigDecimal(productDTO.getAmountProduct()));

        product.setCategory(categoryService.findCategoryById(productDTO.getIdCategory()).orElseThrow());


        if (validationProducts(product)) {
            return  productsRepository.saveAndFlush(product);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "jjjj");
        }
    }

    public HashMap<String,Object> deleteProducts(Long idProduct){
        Optional<Products> products =
                Optional.of(productsRepository.findById(idProduct)).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não encontrado"));

        productsRepository.delete(products.get());
        HashMap<String,Object> result = new HashMap<>();
        result.put("result","Product: " + products.get().getNameProduct()+
                " "+products.get().getNameProduct()+ " excluido com sucesso!");
        return result;
    }


    public Optional<Products> findProductsById(Long idProduct){
        return  Optional.ofNullable(productsRepository.findById(idProduct)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado")));
    }

    public Products updateProduct(ProductDTO productDTO){

        Products product = new Products();
        product.setIdProduct(Long.valueOf(productDTO.getIdProduct()));
        product.setNameProduct(productDTO.getNameProduct());
        product.setDescriptionProduct(productDTO.getDescriptionProduct());
        product.setEanProduct(productDTO.getEanProduct());
        product.setSkuProduct(productDTO.getSkuProduct());

        product.setCostPriceProduct(converterData.convertingStringtoBigDecimal(productDTO.getCostPriceProduct()));
        product.setAmountProduct(converterData.convertingStringtoBigDecimal(productDTO.getAmountProduct()));

        product.setCategory(categoryService.findCategoryById(productDTO.getIdCategory()).orElseThrow());

        product.setPubLishedProduct(Boolean.parseBoolean(productDTO.getPublishedProduct()));
        LocalDate dateCreatedProduct = LocalDate.parse(productDTO.getDateCreateProduct(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        product.setDateCreateProduct(dateCreatedProduct);

        if(validationProducts(product)){
            if(findProductsById(product.getIdProduct())!= null ){
                return productsRepository.saveAndFlush(product);
            }
            else{
                return null;
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "\"Problemas ao cadastrar produto, os valores preço de venda e preço de custo \" +\n" +
                            "                            \"devem ser maiores ou igual a 0 (zero)!\"");
        }
    }

}