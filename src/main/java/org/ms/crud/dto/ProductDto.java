package org.ms.crud.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode
public record ProductDto(Long id, String name, Integer estoque, BigDecimal price) implements Serializable {
    private static final long serialVersionUID = -2572502890187627888L;

}
