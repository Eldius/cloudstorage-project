
package net.eldiosantos.cloudstorage.dropbox.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadFileRequest {

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("autorename")
    @Expose
    private Boolean autorename;
    @SerializedName("mute")
    @Expose
    private Boolean mute;

    public String getPath() {
        return path;
    }

    public UploadFileRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public String getMode() {
        return mode;
    }

    public UploadFileRequest setMode(String mode) {
        this.mode = mode;
        return this;
    }

    public Boolean getAutorename() {
        return autorename;
    }

    public UploadFileRequest setAutorename(Boolean autorename) {
        this.autorename = autorename;
        return this;
    }

    public Boolean getMute() {
        return mute;
    }

    public UploadFileRequest setMute(Boolean mute) {
        this.mute = mute;
        return this;
    }

}
