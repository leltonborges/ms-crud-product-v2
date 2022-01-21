package org.ms.crud.config;

import org.modelmapper.ModelMapper;
import org.ms.crud.dto.ProductDTO;
import org.ms.crud.entities.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper instanceModelMapper(){
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(ProductDTO.class, Product.class);

        return mapper;
    }
}
