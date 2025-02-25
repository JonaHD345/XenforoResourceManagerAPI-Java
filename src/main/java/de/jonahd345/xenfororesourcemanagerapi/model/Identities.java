package de.jonahd345.xenfororesourcemanagerapi.model;

import lombok.Data;

/**
 * Represents the identities of a user in the Xenforo Resource Manager API.
 * This class contains various social media and communication platform identifiers.
 * Used in the {@link Author} class.
 * <br>
 * The identifiers are optional and may be null.
 */
@Data
public class Identities {
    /**
     * The Discord identifier of the user.
     */
    private String discord;

    /**
     * The GitHub identifier of the user.
     */
    private String github;

    /**
     * The YouTube identifier of the user.
     */
    private String youtube;

    /**
     * The AIM identifier of the user.
     */
    private String aim;

    /**
     * The MSN identifier of the user.
     */
    private String msn;

    /**
     * The ICQ identifier of the user.
     */
    private String icq;

    /**
     * The Yahoo identifier of the user.
     */
    private String yahoo;

    /**
     * The Skype identifier of the user.
     */
    private String skype;

    /**
     * The GTalk identifier of the user.
     */
    private String gtalk;

    /**
     * The Facebook identifier of the user.
     */
    private String facebook;

    /**
     * The Twitter identifier of the user.
     */
    private String twitter;
}
