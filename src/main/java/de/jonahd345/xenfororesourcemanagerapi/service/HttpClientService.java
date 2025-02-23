package de.jonahd345.xenfororesourcemanagerapi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A service class for making HTTP GET requests.
 * This class handles the creation of HTTP connections, sending GET requests, and reading the responses.
 */
public class HttpClientService {
    /**
     * Makes an HTTP GET request to the specified URL and returns the response as a string.
     *
     * @param url the URL to send the GET request to
     * @return the response from the GET request as a string
     * @throws IOException if an I/O exception occurs
     */
    public String makeGetRequest(String url) throws IOException {
        URL urlObject = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");

        try {
            int responseCode = connection.getResponseCode();
            InputStreamReader inputStreamReader;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStreamReader = new InputStreamReader(connection.getInputStream());
            } else {
                inputStreamReader = new InputStreamReader(connection.getErrorStream());
            }

            try (BufferedReader in = new BufferedReader(inputStreamReader)) {
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            }
        } finally {
            connection.disconnect();
        }
    }
}
