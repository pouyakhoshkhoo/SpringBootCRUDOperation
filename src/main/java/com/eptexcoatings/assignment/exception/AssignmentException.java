package com.eptexcoatings.assignment.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@NoArgsConstructor
public class AssignmentException extends Exception {

    @Serial
    private static final long serialVersionUID = 7124453171194248443L;

    public AssignmentException(String message) {
        super(message);
    }

    public AssignmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
