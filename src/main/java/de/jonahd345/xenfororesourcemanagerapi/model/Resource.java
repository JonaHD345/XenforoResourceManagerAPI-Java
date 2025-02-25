package de.jonahd345.xenfororesourcemanagerapi.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Represents a resource in the Xenforo Resource Manager API.
 * This class contains various details about the resource such as its ID, title, tag, current version, category,
 * native Minecraft version, supported Minecraft versions, icon link, author, premium status, stats, external download URL, and description.
 */
@Data
public class Resource {
    /**
     * The unique identifier of the resource.
     */
    private int id;

    /**
     * The title of the resource.
     */
    private String title;

    /**
     * The tag of the resource.
     */
    private String tag;

    /**
     * The current version of the resource.
     */
    @SerializedName("current_version")
    private String currentVersion;

    /**
     * The category of the resource.
     */
    private Category category;

    /**
     * The native Minecraft version of the resource.
     */
    @SerializedName("native_minecraft_version")
    private String nativeMinecraftVersion;

    /**
     * The list of supported Minecraft versions for the resource.
     */
    @SerializedName("supported_minecraft_versions")
    private List<String> supportedMinecraftVersions;

    /**
     * The link to the icon of the resource.
     */
    @SerializedName("icon_link")
    private String iconLink;

    /**
     * The author of the resource.
     */
    private Author author;

    /**
     * The premium status of the resource.
     */
    private Premium premium;

    /**
     * The statistics of the resource.
     */
    private Stats stats;

    /**
     * The external download URL of the resource.
     */
    @SerializedName("external_download_url")
    private String externalDownloadUrl;

    /**
     * The description of the resource.
     */
    private String description;
}
