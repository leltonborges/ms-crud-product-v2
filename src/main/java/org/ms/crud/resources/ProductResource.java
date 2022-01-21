package org.ms.crud.resources;

import org.modelmapper.ModelMapper;
import org.ms.crud.dtos.ProductDTO;
import org.ms.crud.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/products", name = "products")
public class ProductResource {

    @Autowired
    private ProductService productService;
    @Autowired
    private PagedResourcesAssembler<ProductDTO> assembler;
    @Autowired
    private ModelMapper mapper;

    @RequestMapping(method = RequestMethod.GET,
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id){
        ProductDTO dtoReturn = productService.findById(id);
        dtoReturn.add(linkTo(methodOn(ProductResource.class).findById(id)).withSelfRel());
        return ResponseEntity
                .ok(dtoReturn);
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    @ResponseBody
    public ResponseEntity<PagedModel<EntityModel<ProductDTO>>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "12") Integer limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ){
        Sort.Direction orderDirection = direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, limit, orderDirection, "name");
        Page<ProductDTO> all = productService.findAll(pageable);
        all = all.
                map(p -> p
                        .add(linkTo(methodOn(ProductResource.class)
                                .findById(p.id()))
                                .withSelfRel()));
        PagedModel<EntityModel<ProductDTO>> pagedModel = assembler.toModel(all);
        return ResponseEntity.ok(pagedModel);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO){
        ProductDTO dto = productService.save(productDTO);
        dto.add(
                linkTo(
                    methodOn(ProductResource.class)
                        .findById(dto.getId())
                ).withSelfRel());

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO){
        ProductDTO dto = productService.save(productDTO);
        dto.add(
                linkTo(
                    methodOn(ProductResource.class)
                        .findById(dto.getId())
                ).withSelfRel());

        return ResponseEntity.ok(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
