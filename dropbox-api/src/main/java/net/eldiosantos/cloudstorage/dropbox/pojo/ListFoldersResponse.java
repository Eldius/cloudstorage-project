
package net.eldiosantos.cloudstorage.dropbox.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListFoldersResponse {

    @SerializedName("entries")
    @Expose
    private List<Entry> entries = null;
    @SerializedName("cursor")
    @Expose
    private String cursor;
    @SerializedName("has_more")
    @Expose
    private Boolean hasMore;

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

}
