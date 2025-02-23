package de.jonahd345.xenfororesourcemanagerapi.model;

import lombok.Data;

/**
 * Represents a category in the Xenforo Resource Manager API.
 * This class contains the category's ID and title.
 */
@Data
public class Category {
    /**
     * The unique identifier of the category.
     */
    private int id;

    /**
     * The title of the category.
     */
    private String title;
}
