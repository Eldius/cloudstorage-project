
package net.eldiosantos.cloudstorage.dropbox.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListFoldersRequest {

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("recursive")
    @Expose
    private Boolean recursive;
    @SerializedName("include_media_info")
    @Expose
    private Boolean includeMediaInfo;
    @SerializedName("include_deleted")
    @Expose
    private Boolean includeDeleted;
    @SerializedName("include_has_explicit_shared_members")
    @Expose
    private Boolean includeHasExplicitSharedMembers;

    public String getPath() {
        return path;
    }

    public ListFoldersRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public Boolean getRecursive() {
        return recursive;
    }

    public ListFoldersRequest setRecursive(Boolean recursive) {
        this.recursive = recursive;
        return this;
    }

    public Boolean getIncludeMediaInfo() {
        return includeMediaInfo;
    }

    public ListFoldersRequest setIncludeMediaInfo(Boolean includeMediaInfo) {
        this.includeMediaInfo = includeMediaInfo;
        return this;
    }

    public Boolean getIncludeDeleted() {
        return includeDeleted;
    }

    public ListFoldersRequest setIncludeDeleted(Boolean includeDeleted) {
        this.includeDeleted = includeDeleted;
        return this;
    }

    public Boolean getIncludeHasExplicitSharedMembers() {
        return includeHasExplicitSharedMembers;
    }

    public ListFoldersRequest setIncludeHasExplicitSharedMembers(Boolean includeHasExplicitSharedMembers) {
        this.includeHasExplicitSharedMembers = includeHasExplicitSharedMembers;
        return this;
    }

}
