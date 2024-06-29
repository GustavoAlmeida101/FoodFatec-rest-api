package br.com.fatecrestapi.FoodFatec.controller;


import br.com.fatecrestapi.FoodFatec.dto.ProductDTO;
import br.com.fatecrestapi.FoodFatec.entity.Products;
import br.com.fatecrestapi.FoodFatec.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductsController {


    @Autowired
    private ProductsService productsService;

    @GetMapping(value = "/list")
    public ResponseEntity<Object> getInfoProducts() {
        List<Products> result = productsService.getInfoProducts();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(value="/create")
    public ResponseEntity<Object> saveProducts(@RequestBody ProductDTO productDTO){
        Products result = productsService.saveProducts(productDTO);
        return  ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/delete/{idProduct}")
    public ResponseEntity<Object> deleteProducts(@PathVariable Long idProduct){
        HashMap<String,Object> result = productsService.deleteProducts(idProduct);
        return ResponseEntity.ok().body(result);
    }


    @GetMapping(value = "/findProduct/{idProduct}")
    public ResponseEntity<Object> findProducts(@PathVariable Long idProduct) {
        Optional<Products> result = productsService.findProductsById(idProduct);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductDTO productDTO){
        Products result = productsService.updateProduct(productDTO);
        return ResponseEntity.ok().body(result);
    }
    }



