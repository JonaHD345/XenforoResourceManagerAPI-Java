package de.jonahd345.xenfororesourcemanagerapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.jonahd345.xenfororesourcemanagerapi.model.Author;
import de.jonahd345.xenfororesourcemanagerapi.model.Category;
import de.jonahd345.xenfororesourcemanagerapi.model.Error;
import de.jonahd345.xenfororesourcemanagerapi.model.Resource;
import de.jonahd345.xenfororesourcemanagerapi.model.Update;
import de.jonahd345.xenfororesourcemanagerapi.service.HttpClientService;
import de.jonahd345.xenfororesourcemanagerapi.util.Constants;
import de.jonahd345.xenfororesourcemanagerapi.util.RequestResponse;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;

/**
 * The main class for interacting with the XenforoResourceManagerAPI from SpigotMC.
 * This class provides methods to list resources, fetch resource details, retrieve updates, and manage authors.
 * It provides asynchronous methods for non-blocking operations.
 */
public class XenforoResourceManagerAPI {
    private Logger logger;

    private HttpClientService httpClientService;

    private Gson gson;

    /**
     * Constructor to initialize the API client.
     * Sets up the logger, HTTP client service and Gson instance
     */
    public XenforoResourceManagerAPI() {
        this.logger = Logger.getLogger(XenforoResourceManagerAPI.class.getName());
        this.httpClientService = new HttpClientService();
        this.gson = new Gson();
    }

    /**
     * Retrieves a {@link List} of {@link Resource} in a category and on a pagination.
     *
     * @param category the category ID (optional)
     * @param page the page number (optional)
     * @return a {@link List} of {@link Resource}, which can be null if no {@link Resource} is found in this category or page, or if an error occurs
     */
    public List<Resource> listResources(Integer category, Integer page) {
        Type listType = new TypeToken<List<Resource>>() {}.getType();
        StringBuilder url = new StringBuilder();

        url.append(Constants.API_URL).append("?action=listResources");
        if (category != null) {
            url.append("&category=").append(category);
        }
        if (page == null || page == 0) {
            page = 1;
        }
        url.append("&page=").append(page);
        return fetchData(url.toString(), "listResources", listType);
    }

    /**
     * Retrieves a {@link List} of {@link Resource} with default pagination.
     *
     * @return a {@link List} of {@link Resource}, which can be null if an error occurs
     */
    public List<Resource> listResources() {
        return listResources(null, 1);
    }

    /**
     * Retrieves a {@link List} of {@link Resource} with specified pagination.
     *
     * @param page the page number
     * @return a {@link List} of {@link Resource}, which can be null if no {@link Resource} is found on this page, or if an error occurs
     */
    public List<Resource> listResources(Integer page) {
        return listResources(null, page);
    }

    /**
     * Asynchronously retrieves a {@link List} of {@link Resource} with category and pagination.
     *
     * @param category the category ID (optional)
     * @param page the page number (optional)
     * @return a CompletableFuture containing a {@link List} of {@link Resource}, which can be null if no {@link Resource} is found in this category or page, or if an error occurs
     */
    public CompletableFuture<List<Resource>> listResourcesAsync(Integer category, Integer page) {
        return listResourcesAsync(category, page, ForkJoinPool.commonPool());
    }

    /**
     * Asynchronously retrieves a {@link List} of {@link Resource} with category and pagination.
     *
     * @param category the category ID (optional)
     * @param page the page number (optional)
     * @param executorService the executor service to run the operation on
     * @return a CompletableFuture containing a {@link List} of {@link Resource}, which can be null if no {@link Resource} is found in this category or page, or if an error occurs
     */
    public CompletableFuture<List<Resource>> listResourcesAsync(Integer category, Integer page, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> listResources(category, page), executorService);
    }

    /**
     * Asynchronously retrieves a {@link List} of {@link Resource} with default pagination.
     *
     * @return a CompletableFuture containing a {@link List} of {@link Resource}, which can be null if an error occurs
     */
    public CompletableFuture<List<Resource>> listResourcesAsync() {
        return listResourcesAsync(ForkJoinPool.commonPool());
    }

    /**
     * Asynchronously retrieves a {@link List} of {@link Resource} with default pagination.
     *
     * @param executorService the executor service to run the operation on
     * @return a CompletableFuture containing a {@link List} of {@link Resource}, which can be null if an error occurs
     */
    public CompletableFuture<List<Resource>> listResourcesAsync(ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> listResources(null, 1), executorService);
    }

    /**
     * Asynchronously retrieves a {@link List} of {@link Resource} with specified pagination.
     *
     * @param page the page number
     * @return a CompletableFuture containing a {@link List} of {@link Resource}, which can be null if no {@link Resource} is found in this page, or if an error occurs
     */
    public CompletableFuture<List<Resource>> listResourcesAsync(Integer page) {
        return listResourcesAsync(page, ForkJoinPool.commonPool());
    }

    /**
     * Asynchronously retrieves a {@link List} of {@link Resource} with specified pagination.
     *
     * @param page the page number
     * @param executorService the executor service to run the operation on
     * @return a CompletableFuture containing a {@link List} of {@link Resource}, which can be null if no {@link Resource} is found in this page, or if an error occurs
     */
    public CompletableFuture<List<Resource>> listResourcesAsync(Integer page, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> listResources(null, page), executorService);
    }

    /**
     * Retrieves detailed information about a specific {@link Resource}.
     *
     * @param id the resource ID
     * @return the {@link Resource}, which can be null if no {@link Resource} is found or if an error occurs
     */
    public Resource getResource(int id) {
        StringBuilder url = new StringBuilder();

        url.append(Constants.API_URL).append("?action=getResource").append("&id=").append(id);
        return fetchData(url.toString(), "getResource", Resource.class);
    }

    /**
     * Asynchronously retrieves detailed information about a specific {@link Resource}.
     *
     * @param id the resource ID
     * @return a CompletableFuture containing the {@link Resource}, which can be null if no {@link Resource} is found or if an error occurs
     */
    public CompletableFuture<Resource> getResourceAsync(int id) {
        return getResourceAsync(id, ForkJoinPool.commonPool());
    }

    /**
     * Asynchronously retrieves detailed information about a specific {@link Resource}.
     *
     * @param id the resource ID
     * @param executorService the executor service to run the operation on
     * @return a CompletableFuture containing the {@link Resource}, which can be null if no {@link Resource} is found or if an error occurs
     */
    public CompletableFuture<Resource> getResourceAsync(int id, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> getResource(id), executorService);
    }

    /**
     * Retrieves a {@link List} of {@link Resource} created by a specific author with pagination.
     *
     * @param id the author ID
     * @param page the page number (optional)
     * @return a {@link List} of {@link Resource}, which can be null if the author has no {@link Resource}'s or if an error occurs
     */
    public List<Resource> getResourcesByAuthor(int id, Integer page) {
        Type listType = new TypeToken<List<Resource>>() {}.getType();
        StringBuilder url = new StringBuilder();

        url.append(Constants.API_URL).append("?action=getResourcesByAuthor").append("&id=").append(id);
        if (page == null || page == 0) {
            page = 1;
        }
        url.append("&page=").append(page);
        return fetchData(url.toString(), "getResourcesByAuthor", listType);
    }

    /**
     * Retrieves a {@link List} of {@link Resource} created by a specific author with default pagination.
     *
     * @param id the author ID
     * @return a {@link List} of {@link Resource}, which can be null if the author has no {@link Resource}'s or if an error occurs
     */
    public List<Resource> getResourcesByAuthor(int id) {
        return getResourcesByAuthor(id, 1);
    }

    /**
     * Asynchronously retrieves a {@link List} of {@link Resource} created by a specific author with pagination.
     *
     * @param id the author ID
     * @param page the page number (optional)
     * @return a CompletableFuture containing a {@link List} of {@link Resource}, which can be null if the author has no {@link Resource}'s or if an error occurs
     */
    public CompletableFuture<List<Resource>> getResourcesByAuthorAsync(int id, Integer page) {
        return getResourcesByAuthorAsync(id, page, ForkJoinPool.commonPool());
    }

    /**
     * Asynchronously retrieves a {@link List} of {@link Resource} created by a specific author with pagination.
     *
     * @param id the author ID
     * @param page the page number (optional)
     * @param executorService the executor service to run the operation on
     * @return a CompletableFuture containing a {@link List} of {@link Resource}, which can be null if the author has no {@link Resource}'s or if an error occurs
     */
    public CompletableFuture<List<Resource>> getResourcesByAuthorAsync(int id, Integer page, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> getResourcesByAuthor(id, page), executorService);
    }

    /**
     * Asynchronously retrieves a {@link List} of {@link Resource} created by a specific author with default pagination.
     *
     * @param id the author ID
     * @return a CompletableFuture containing a {@link List} of {@link Resource}, which can be null if the author has no {@link Resource}'s or if an error occurs
     */
    public CompletableFuture<List<Resource>> getResourcesByAuthorAsync(int id) {
        return getResourcesByAuthorAsync(id, ForkJoinPool.commonPool());
    }

    /**
     * Asynchronously retrieves a {@link List} of {@link Resource} created by a specific author with default pagination.
     *
     * @param id the author ID
     * @param executorService the executor service to run the operation on
     * @return a CompletableFuture containing a {@link List} of {@link Resource}, which can be null if the author has no {@link Resource}'s or if an error occurs
     */
    public CompletableFuture<List<Resource>> getResourcesByAuthorAsync(int id, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> getResourcesByAuthor(id, 1), executorService);
    }

    /**
     * Retrieves a {@link List} of all available resource {@link Category}.
     *
     * @return a {@link List} of {@link Category}, which can be null if an error occurs
     */
    public List<Category> listResourceCategories() {
        Type listType = new TypeToken<List<Category>>() {}.getType();
        StringBuilder url = new StringBuilder();

        url.append(Constants.API_URL).append("?action=listResourceCategories");
        return fetchData(url.toString(), "listResourceCategories", listType);
    }

    /**
     * Asynchronously retrieves a {@link List} of all available resource {@link Category}.
     *
     * @return a CompletableFuture containing a {@link List} of {@link Category}, which can be null if an error occurs
     */
    public CompletableFuture<List<Category>> listResourceCategoriesAsync() {
        return listResourceCategoriesAsync(ForkJoinPool.commonPool());
    }

    /**
     * Asynchronously retrieves a {@link List} of all available resource {@link Category}.
     *
     * @param executorService the executor service to run the operation on
     * @return a CompletableFuture containing a {@link List} of {@link Category}, which can be null if an error occurs
     */
    public CompletableFuture<List<Category>> listResourceCategoriesAsync(ExecutorService executorService) {
        return CompletableFuture.supplyAsync(this::listResourceCategories, executorService);
    }

    /**
     * Retrieves details of a specific resource {@link Update}.
     *
     * @param id the update ID
     * @return the {@link Update}, which can be null if no {@link Update} with the {@code id} is found or if an error occurs
     */
    public Update getResourceUpdate(int id) {
        StringBuilder url = new StringBuilder();

        url.append(Constants.API_URL).append("?action=getResourceUpdate").append("&id=").append(id);
        return fetchData(url.toString(), "getResourceUpdate", Update.class);
    }

    /**
     * Asynchronously retrieves details of a specific resource {@link Update}.
     *
     * @param id the update ID
     * @return a CompletableFuture containing the {@link Update}, which can be null if no {@link Update} with the {@code id} is found or if an error occurs
     */
    public CompletableFuture<Update> getResourceUpdateAsync(int id) {
        return getResourceUpdateAsync(id, ForkJoinPool.commonPool());
    }

    /**
     * Asynchronously retrieves details of a specific resource {@link Update}.
     *
     * @param id the update ID
     * @param executorService the executor service to run the operation on
     * @return a CompletableFuture containing the {@link Update}, which can be null if no {@link Update} with the {@code id} is found or if an error occurs
     */
    public CompletableFuture<Update> getResourceUpdateAsync(int id, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> getResourceUpdate(id), executorService);
    }

    /**
     * Retrieves a {@link List} of {@link Update} for a specific resource with pagination.
     *
     * @param id the resource ID
     * @param page the page number (optional)
     * @return a {@link List} of {@link Update}, which can be null if no resource with the {@code id} is found or on the page, or if an error occurs
     */
    public List<Update> getResourceUpdates(int id, Integer page) {
        Type listType = new TypeToken<List<Update>>() {}.getType();
        StringBuilder url = new StringBuilder();

        url.append(Constants.API_URL).append("?action=getResourceUpdates").append("&id=").append(id);
        if (page == null || page == 0) {
            page = 1;
        }
        url.append("&page=").append(page);
        return fetchData(url.toString(), "getResourceUpdates", listType);
    }

    /**
     * Retrieves a {@link List} of {@link Update} for a specific resource with default pagination.
     *
     * @param id the resource ID
     * @return a {@link List} of {@link Update}, which can be null if no resource with the {@code id} is found or if an error occurs
     */
    public List<Update> getResourceUpdates(int id) {
        return getResourceUpdates(id, 1);
    }

    /**
     * Asynchronously retrieves a {@link List} of {@link Update} for a specific resource with pagination.
     *
     * @param id the resource ID
     * @param page the page number (optional)
     * @return a CompletableFuture containing a {@link List} of {@link Update}, which can be null if no resource with the {@code id} is found or on the page, or if an error occurs
     */
    public CompletableFuture<List<Update>> getResourceUpdatesAsync(int id, Integer page) {
        return getResourceUpdatesAsync(id, page, ForkJoinPool.commonPool());
    }

    /**
     * Asynchronously retrieves a {@link List} of {@link Update} for a specific resource with pagination.
     *
     * @param id the resource ID
     * @param page the page number (optional)
     * @param executorService the executor service to run the operation on
     * @return a CompletableFuture containing a {@link List} of {@link Update}, which can be null if no resource with the {@code id} is found or on the page, or if an error occurs
     */
    public CompletableFuture<List<Update>> getResourceUpdatesAsync(int id, Integer page, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> getResourceUpdates(id, page), executorService);
    }

    /**
     * Asynchronously retrieves a {@link List} of {@link Update} for a specific resource with default pagination.
     *
     * @param id the resource ID
     * @return a CompletableFuture containing a {@link List} of {@link Update}, which can be null if no resource with the {@code id} is found or if an error occurs
     */
    public CompletableFuture<List<Update>> getResourceUpdatesAsync(int id) {
        return getResourceUpdatesAsync(id, ForkJoinPool.commonPool());
    }

    /**
     * Asynchronously retrieves a {@link List} of {@link Update} for a specific resource with default pagination.
     *
     * @param id the resource ID
     * @param executorService the executor service to run the operation on
     * @return a CompletableFuture containing a {@link List} of {@link Update}, which can be null if no resource with the {@code id} is found or if an error occurs
     */
    public CompletableFuture<List<Update>> getResourceUpdatesAsync(int id, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> getResourceUpdates(id, 1), executorService);
    }

    /**
     * Retrieves detailed information about a specific {@link Author}.
     *
     * @param id the author ID
     * @return the the {@link Author}, which can be null if no {@link Author} with the {@code id} is found or if an error occurs
     */
    public Author getAuthor(int id) {
        StringBuilder url = new StringBuilder();

        url.append(Constants.API_URL).append("?action=getAuthor").append("&id=").append(id);
        return fetchData(url.toString(), "getAuthor", Author.class);
    }

    /**
     * Asynchronously retrieves detailed information about a specific {@link Author}.
     *
     * @param id the author ID
     * @return a CompletableFuture containing the {@link Author}, which can be null if no {@link Author} with the {@code id} is found or if an error occurs
     */
    public CompletableFuture<Author> getAuthorAsync(int id) {
        return getAuthorAsync(id, ForkJoinPool.commonPool());
    }

    /**
     * Asynchronously retrieves detailed information about a specific {@link Author}.
     *
     * @param id the author ID
     * @param executorService the executor service to run the operation on
     * @return a CompletableFuture containing the {@link Author}, which can be null if no {@link Author} with the {@code id} is found or if an error occurs
     */
    public CompletableFuture<Author> getAuthorAsync(int id, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> getAuthor(id), executorService);
    }

    /**
     * Searches for an {@link Author} by name.
     *
     * @param name the author's name
     * @return the {@link Author}, which can be null if no {@link Author} with the {@code name} is found or if an error occurs
     */
    public Author findAuthor(String name) {
        StringBuilder url = new StringBuilder();

        url.append(Constants.API_URL).append("?action=findAuthor").append("&name=").append(name);
        return fetchData(url.toString(), "findAuthor", Author.class);
    }

    /**
     * Asynchronously searches for an {@link Author} by name.
     *
     * @param name the author's name
     * @return a CompletableFuture containing the {@link Author}, which can be null if no {@link Author} with the {@code name} is found or if an error occurs
     */
    public CompletableFuture<Author> findAuthorAsync(String name) {
        return CompletableFuture.supplyAsync(() -> findAuthor(name));
    }

    /**
     * Asynchronously searches for an {@link Author} by name.
     *
     * @param name the author's name
     * @param executorService the executor service to run the operation on
     * @return a CompletableFuture containing the {@link Author}, which can be null if no {@link Author} with the {@code name} is found or if an error occurs
     */
    public CompletableFuture<Author> findAuthorAsync(String name, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> findAuthor(name), executorService);
    }

    /**
     * Fetches data from the specified URL and parses it into the specified type.
     *
     * @param url the URL to fetch data from
     * @param endpointName the name of the endpoint
     * @param type the type to parse the data into
     * @param <T> the type of the data
     * @return the parsed data, which can be {@code null} if no data is found or if an error occurs
     */
    private <T> T fetchData(String url, String endpointName, Type type) {
        RequestResponse response;
        try {
            response = httpClientService.makeGetRequest(url);

            if (response == null) {
                throw new IllegalStateException("The HTTP request returned a null response.");
            }
            if (response.getCode() == 200) {
                return gson.fromJson(response.getResponse(), type);
            } else {
                try {
                    Error error = gson.fromJson(response.getResponse(), Error.class);

                    if (error != null) {
                        logger.severe("Error by " + endpointName + " at endpoint: " + url + "\nCode: " + error.getCode() + "\nError: " + error.getMessage());
                        return null;
                    }
                } catch (Exception ignored) {}
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Failed by " + endpointName + " at endpoint: " + url + "\nException: " + e, e);
        }
    }
}
