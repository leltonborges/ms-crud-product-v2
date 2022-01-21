package org.ms.crud.services;

import org.modelmapper.ModelMapper;
import org.ms.crud.dto.ProductDTO;
import org.ms.crud.entities.Product;
import org.ms.crud.repositories.ProductRepository;
import org.ms.crud.services.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ModelMapper mapper;
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }


    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        return productRepository.saveAll(entities);
    }

    public ProductDTO save(ProductDTO entity) {
        Product product = productRepository.save(fromProduct(entity));
        return fromProductDTO(product);
    }

    public ProductDTO findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        ProductDTO productDTOReturn = fromProductDTO(product
                .orElseThrow(() -> new ProductNotFoundException("Not found id: " + id)));
        return productDTOReturn;
    }

    public List<Product> findAll(Sort sort) {
        return productRepository.findAll(sort);
    }

    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(this::fromProductDTO);
    }

    public <S extends Product> boolean exists(Example<S> example) {
        return productRepository.exists(example);
    }

    protected Product fromProduct(ProductDTO productDto){
        return mapper.map(productDto, Product.class);
    }

    protected ProductDTO fromProductDTO(Product product){
        return  mapper.map(product, ProductDTO.class);
    }
}
