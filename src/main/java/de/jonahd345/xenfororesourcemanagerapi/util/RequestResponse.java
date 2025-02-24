package de.jonahd345.xenfororesourcemanagerapi.util;

import lombok.Data;

/**
 * A utility class representing a request response.
 * This class is used to encapsulate the response code and the response message.
 */
@Data
public class RequestResponse {
    /**
     * The HTTP response code.
     */
    private int code;

    /**
     * The response message as a string.
     */
    private String response;
}
