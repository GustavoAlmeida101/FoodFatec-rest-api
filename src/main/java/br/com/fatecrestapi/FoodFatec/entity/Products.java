package br.com.fatecrestapi.FoodFatec.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_produto")
    private Long idProduct;

    @Column(name="nome_produto")
    @NotBlank(message = "Esse campo é obrigatorio!")
    @Length(min = 2,max = 300,message = "O primeiro nome deve ter no minimo 2 e no maximo 300 caracteres!")
    private String nameProduct;

    @Column(name = "decrição_produto")
    @Length(min = 2,max = 300,message = "O Ultimo nome deve ter no minimo 2 e no maximo 300 caracteres")
    private String descriptionProduct;

    @Column(name = "sku_produto")
    @Length(min = 10,max = 50)
    private String skuProduct;

    @Column(name = "sku_produto")
    @Length(min = 2,max = 50)
    private String eanProduct;

    @Column(name = "cost_price_product", nullable = false, precision = 10, scale = 2)
    private BigDecimal costPriceProduct;

    @Column(name = "amount_product", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountProduct;

    @Column(name = "pub_lished_product", nullable = false)
    private Boolean pubLishedProduct;

    @Column(name = "create_product", nullable = false,updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreateProduct;



}
