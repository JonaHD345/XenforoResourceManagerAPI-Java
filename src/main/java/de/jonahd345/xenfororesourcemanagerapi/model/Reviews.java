package de.jonahd345.xenfororesourcemanagerapi.model;

import lombok.Data;

/**
 * Represents reviews in the Xenforo Resource Manager API.
 * This class contains the unique and total number of reviews.
 */
@Data
public class Reviews {
    /**
     * The unique number of reviews.
     */
    private int unique;

    /**
     * The total number of reviews.
     */
    private int total;
}
