
package net.eldiosantos.cloudstorage.dropbox.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DownloadFileRequest {

    @SerializedName("path")
    @Expose
    private String path;

    public DownloadFileRequest() {
    }

    public DownloadFileRequest(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public DownloadFileRequest setPath(String path) {
        this.path = path;
        return this;
    }

}
