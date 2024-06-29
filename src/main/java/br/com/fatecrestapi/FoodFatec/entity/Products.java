package br.com.fatecrestapi.FoodFatec.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_produto")
    private Long idProduct;

    @Column(name="nome_produto",unique = true,nullable = false,length = 300)
    @NotBlank(message = "Esse campo é obrigatorio!")
    @Length(min = 2,max = 300,message = "O  nome  do produto deve ter no minimo 2 e no maximo 300 caracteres!")
    private String nameProduct;

    @Column(name = "decrição_produto",nullable = false,length = 300)
    @NotBlank(message = "A descrição  é obrigatorio!")
    @Length(min = 2,max = 3000,message = "A descrição do produto ter no minimo 2 e no maximo 3000 caracteres")
    private String descriptionProduct;

    @Column(name = "sku_produto",nullable = false,length = 10)
    @NotBlank(message = "A sku do produto é obrigatorio!")
    @Length(min = 2,max =10 ,message = "A sku do do produto ter no minimo 2 e no maximo 3000 caracteres")
    private String skuProduct;

    @Column(name = "ean_produto",nullable = false,length = 15)
    @NotBlank(message = "A EAN do produto é obrigatorio!")
    @Length(min = 2,max = 15)
    private String eanProduct;

    @Column(name = "cost_price_product", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "O cot price do produto é obrigatorio!")
    private BigDecimal costPriceProduct;

    @Column(name = "amount_product", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "O amount  do produto é obrigatorio!")
    private BigDecimal amountProduct;

    @Column(name = "pub_lished_product", nullable = false)
    private Boolean pubLishedProduct;

    @Column(name = "create_product", nullable = false,updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreateProduct;

  @ManyToOne
  @JoinColumn(name = "id_category")
  private Category category;
    @PrePersist
    private void prePersist(){
        this.setPubLishedProduct(false);
        this.setDateCreateProduct(LocalDate.now());
    }



}
