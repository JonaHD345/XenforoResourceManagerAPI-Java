package de.jonahd345.xenfororesourcemanagerapi.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Represents an author in the Xenforo Resource Manager API.
 * This class contains the author's ID, username, resource count, identities, and avatar URL.
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

    /**
     * The number of resources created by the author.
     */
    @SerializedName("resource_count")
    private int resourceCount;

    /**
     * The social media and communication platform identifiers of the author.
     */
    private Identities identities;

    /**
     * The URL of the author's avatar image.
     */
    private String avatar;
}
