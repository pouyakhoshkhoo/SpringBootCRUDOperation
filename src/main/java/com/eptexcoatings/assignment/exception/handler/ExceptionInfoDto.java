package com.eptexcoatings.assignment.exception.handler;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@Data
public class ExceptionInfoDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 7612086822582968045L;

    private String exceptionName;
    private String message;
    @Setter(AccessLevel.NONE)
    private Map<String, Object> errorParam;

    public void addParam(String key, Object value) {
        if (errorParam == null) {
            errorParam = new HashMap<>();
        }
        errorParam.put(key, value);
    }
}
