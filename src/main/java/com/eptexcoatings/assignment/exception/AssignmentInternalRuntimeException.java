package com.eptexcoatings.assignment.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@NoArgsConstructor
public class AssignmentInternalRuntimeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2870219807596793808L;

    public AssignmentInternalRuntimeException(String message, Exception e) {
        super(message, e);
    }
}
