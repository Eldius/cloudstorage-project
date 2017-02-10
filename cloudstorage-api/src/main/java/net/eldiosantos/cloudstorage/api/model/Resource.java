package net.eldiosantos.cloudstorage.api.model;

/**
 * Created by esjunior on 02/02/2017.
 */
public class Resource {
    private String id;
    private ResourceType type;
    private String pathDisplay;
    private String name;
    private String url;

    public String getId() {
        return id;
    }

    public Resource setId(String id) {
        this.id = id;
        return this;
    }

    public ResourceType getType() {
        return type;
    }

    public Resource setType(ResourceType type) {
        this.type = type;
        return this;
    }

    public String getPathDisplay() {
        return pathDisplay;
    }

    public Resource setPathDisplay(String pathDisplay) {
        this.pathDisplay = pathDisplay;
        return this;
    }

    public String getName() {
        return name;
    }

    public Resource setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Resource setUrl(String url) {
        this.url = url;
        return this;
    }

    public static enum ResourceType {
        FILE
        , FOLDER;
    }
}

