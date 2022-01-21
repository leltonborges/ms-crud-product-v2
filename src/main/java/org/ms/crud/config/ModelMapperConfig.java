package org.ms.crud.config;

import org.modelmapper.ModelMapper;
import org.ms.crud.dto.ProductDTOReturn;
import org.ms.crud.dto.ProductDto;
import org.ms.crud.entities.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper instanceModelMapper(){
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(ProductDto.class, Product.class);
        mapper.createTypeMap(Product.class, ProductDTOReturn.class);

        return mapper;
    }
}
