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
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class XenforoResourceManagerAPI {
    private Logger logger;

    private HttpClientService httpClientService;

    private Gson gson;

    private HashMap<String, Type> cachedTypes;

    public XenforoResourceManagerAPI() {
        this.logger = Logger.getLogger(XenforoResourceManagerAPI.class.getName());
        this.httpClientService = new HttpClientService();
        this.gson = new Gson();
        this.cachedTypes = new HashMap<>();
    }

    // listResources
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

    public List<Resource> listResources() {
        return listResources(null, 1);
    }

    public List<Resource> listResources(Integer page) {
        return listResources(null, page);
    }

    public CompletableFuture<List<Resource>> listResourcesAsync(Integer category, Integer page) {
        return CompletableFuture.supplyAsync(() -> listResources(category, page));
    }

    public CompletableFuture<List<Resource>> listResourcesAsync() {
        return CompletableFuture.supplyAsync(() -> listResources(null, 1));
    }

    public CompletableFuture<List<Resource>> listResourcesAsync(Integer page) {
        return CompletableFuture.supplyAsync(() -> listResources(null, page));
    }


    // getResource
    public Resource getResource(int id) {
        StringBuilder url = new StringBuilder();

        url.append(Constants.API_URL).append("?action=getResource").append("&id=").append(id);
        return fetchData(url.toString(), "getResource", Resource.class);
    }

    public CompletableFuture<Resource> getResourceAsync(int id) {
        return CompletableFuture.supplyAsync(() -> getResource(id));
    }


    // getResourcesByAuthor
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

    public List<Resource> getResourcesByAuthor(int id) {
        return getResourcesByAuthor(id, 1);
    }

    public CompletableFuture<List<Resource>> getResourcesByAuthorAsync(int id, Integer page) {
        return CompletableFuture.supplyAsync(() -> getResourcesByAuthor(id, page));
    }

    public CompletableFuture<List<Resource>> getResourcesByAuthorAsync(int id) {
        return CompletableFuture.supplyAsync(() -> getResourcesByAuthor(id, 1));
    }


    // listResourceCategories
    public List<Category> listResourceCategories() {
        Type listType = new TypeToken<List<Category>>() {}.getType();
        StringBuilder url = new StringBuilder();

        url.append(Constants.API_URL).append("?action=listResourceCategories");
        return fetchData(url.toString(), "listResourceCategories", listType);
    }

    public CompletableFuture<List<Category>> listResourceCategoriesAsync(int id, Integer page) {
        return CompletableFuture.supplyAsync(this::listResourceCategories);
    }


    // getResourceUpdate
    public Update getResourceUpdate(int id) {
        StringBuilder url = new StringBuilder();

        url.append(Constants.API_URL).append("?action=getResourceUpdate").append("&id=").append(id);
        return fetchData(url.toString(), "getResourceUpdate", Update.class);
    }

    public CompletableFuture<Update> getResourceUpdateAsync(int id) {
        return CompletableFuture.supplyAsync(() -> getResourceUpdate(id));
    }


    // getResourceUpdates
    public List<Update> getResourceUpdates(int id, Integer page) {
        Type listType = new TypeToken<List<Update>>() {}.getType();
        StringBuilder url = new StringBuilder();

        url.append(Constants.API_URL).append("?action=getResourceUpdates").append("&id=").append(id);
        if (page == null || page == 0) {
            page = 1;
        }
        url.append("&page=").append(page);
        return fetchData(url.toString(), "getAuthor", listType);
    }

    public List<Update> getResourceUpdates(int id) {
        return getResourceUpdates(id, 1);
    }

    public CompletableFuture<List<Update>> getResourceUpdatesAsync(int id, Integer page) {
        return CompletableFuture.supplyAsync(() -> getResourceUpdates(id, page));
    }

    public CompletableFuture<List<Update>> getResourceUpdatesAsync(int id) {
        return CompletableFuture.supplyAsync(() -> getResourceUpdates(id, 1));
    }


    // getAuthor
    public Author getAuthor(int id) {
        StringBuilder url = new StringBuilder();

        url.append(Constants.API_URL).append("?action=getAuthor").append("&id=").append(id);
        return fetchData(url.toString(), "getAuthor", Author.class);
    }

    public CompletableFuture<Author> getAuthorAsync(int id) {
        return CompletableFuture.supplyAsync(() -> getAuthor(id));
    }


    // findAuthor
    public Author findAuthor(String name) {
        StringBuilder url = new StringBuilder();

        url.append(Constants.API_URL).append("?action=findAuthor").append("&name=").append(name);
        return fetchData(url.toString(), "findAuthor", Author.class);
    }

    public CompletableFuture<Author> findAuthorAsync(String name) {
        return CompletableFuture.supplyAsync(() -> findAuthor(name));
    }


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
