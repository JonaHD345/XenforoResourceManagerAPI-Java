package de.jonahd345.xenfororesourcemanagerapi.model;

import lombok.Data;

/**
 * Represents an author in the Xenforo Resource Manager API.
 * This class contains the author's ID and username.
 */
@Data
public class Author {
    /**
     * The unique identifier of the author.
     */
    private int id;

    /**
     * The username of the author.
     */
    private String username;
}
