package de.jonahd345.xenfororesourcemanagerapi.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Represents an update in the Xenforo Resource Manager API.
 * This class contains the update's ID, resource ID, title, and message.
 */
@Data
public class Update {
    /**
     * The unique identifier of the update.
     */
    private int id;

    /**
     * The unique identifier of the resource associated with the update.
     */
    @SerializedName("resource_id")
    private int resourceId;

    /**
     * The title of the update.
     */
    private String title;

    /**
     * The message of the update.
     */
    private String message;
}
