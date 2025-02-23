package de.jonahd345.xenfororesourcemanagerapi.model;

import lombok.Data;

import java.util.List;

@Data
public class Resource {
    private int id;

    private String title;

    private String tag;

    private String currentVersion;

    private Category category;

    private String nativeMinecraftVersion;

    private List<String> supportedMinecraftVersions;

    private String iconLink;

    private Author author;

    private Premium premium;

    private Stats stats;

    private String externalDownloadUrl;

    private String description;
}
