
package net.eldiosantos.cloudstorage.dropbox.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaInfo {

    @SerializedName(".tag")
    @Expose
    private String tag;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
