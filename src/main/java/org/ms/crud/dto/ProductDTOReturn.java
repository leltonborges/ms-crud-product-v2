package org.ms.crud.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public record ProductDTOReturn(Long id, String name, BigDecimal price) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
