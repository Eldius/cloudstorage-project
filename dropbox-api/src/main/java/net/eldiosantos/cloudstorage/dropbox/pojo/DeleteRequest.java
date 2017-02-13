
package net.eldiosantos.cloudstorage.dropbox.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteRequest {

    @SerializedName("path")
    @Expose
    private String path;

    public String getPath() {
        return path;
    }

    public DeleteRequest setPath(String path) {
        this.path = path;
        return this;
    }

}
