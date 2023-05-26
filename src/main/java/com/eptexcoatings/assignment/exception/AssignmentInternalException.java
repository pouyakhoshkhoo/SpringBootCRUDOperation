package com.eptexcoatings.assignment.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@NoArgsConstructor
public class AssignmentInternalException extends AssignmentException {

    @Serial
    private static final long serialVersionUID = -7413773320964184317L;

    public AssignmentInternalException(String message, Exception e) {
        super(message, e);
    }
}
