package org.ms.crud.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class StandarError implements Serializable {
    @Serial
    private static final long serialVersionUID = 864177068607075682L;

    private Long timestamp;
    private HttpStatus status;
    private String error;
    private String message;
    private String path;

}
