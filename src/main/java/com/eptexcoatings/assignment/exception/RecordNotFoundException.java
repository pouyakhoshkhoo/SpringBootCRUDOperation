package com.eptexcoatings.assignment.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * @author Pouya Khoshkhou
 * @since 07/07/2023
 */
@NoArgsConstructor
public class RecordNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6392274595703951290L;

    public RecordNotFoundException(String message) {
        super(message);
    }
}
