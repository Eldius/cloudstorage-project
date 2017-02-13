
package net.eldiosantos.cloudstorage.dropbox.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateFolderRequest {

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("autorename")
    @Expose
    private Boolean autorename;

    public String getPath() {
        return path;
    }

    public CreateFolderRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public Boolean getAutorename() {
        return autorename;
    }

    public CreateFolderRequest setAutorename(Boolean autorename) {
        this.autorename = autorename;
        return this;
    }

}
