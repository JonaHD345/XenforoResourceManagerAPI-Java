package de.jonahd345.xenfororesourcemanagerapi.model;

import lombok.Data;

/**
 * Represents statistics in the Xenforo Resource Manager API.
 * This class contains the number of downloads, updates, reviews, and the rating.
 */
@Data
public class Stats {
    /**
     * The number of downloads.
     */
    private int downloads;

    /**
     * The number of updates.
     */
    private int updates;

    /**
     * The reviews of the resource.
     */
    private Reviews reviews;

    /**
     * The rating of the resource.
     */
    private int rating;
}
