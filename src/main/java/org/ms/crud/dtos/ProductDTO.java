package org.ms.crud.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;
import org.ms.crud.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public final class ProductDTO extends RepresentationModel<ProductDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = -2572502890187627888L;

    @Autowired
    private static ModelMapper mapper;

    private final Long id;
    private final @NotBlank String name;
    private final @NotBlank @Min(1) Integer stock;
    private final @Min(1) BigDecimal price;

    public Long id() {
        return id;
    }

    public @NotBlank String name() {
        return name;
    }

    public @NotBlank @Min(1) Integer stock() {
        return stock;
    }

    public @Min(1) BigDecimal price() {
        return price;
    }


    public static ProductDTO fromProductDTO(Product product){
        return mapper.map(product, ProductDTO.class);
    }
}
