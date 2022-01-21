package org.ms.crud.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -1975063318396455230L;

    private Date timeStamp;
    private String message;
    private String details;
}
