package de.jonahd345.xenfororesourcemanagerapi.model;

import lombok.Data;

/**
 * Represents an error response from the Xenforo Resource Manager API.
 * This class contains the error code and the error message.
 */
@Data
public class Error {
    /**
     * The error code returned by the API.
     */
    private int code;

    /**
     * The error message returned by the API.
     */
    private String message;
}
