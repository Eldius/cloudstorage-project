
package net.eldiosantos.cloudstorage.dropbox.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entry {

    @SerializedName(".tag")
    @Expose
    private String tag;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("path_lower")
    @Expose
    private String pathLower;
    @SerializedName("path_display")
    @Expose
    private String pathDisplay;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("client_modified")
    @Expose
    private String clientModified;
    @SerializedName("server_modified")
    @Expose
    private String serverModified;
    @SerializedName("rev")
    @Expose
    private String rev;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("has_explicit_shared_members")
    @Expose
    private Boolean hasExplicitSharedMembers;
    @SerializedName("content_hash")
    @Expose
    private String contentHash;
    @SerializedName("media_info")
    @Expose
    private MediaInfo mediaInfo;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathLower() {
        return pathLower;
    }

    public void setPathLower(String pathLower) {
        this.pathLower = pathLower;
    }

    public String getPathDisplay() {
        return pathDisplay;
    }

    public void setPathDisplay(String pathDisplay) {
        this.pathDisplay = pathDisplay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientModified() {
        return clientModified;
    }

    public void setClientModified(String clientModified) {
        this.clientModified = clientModified;
    }

    public String getServerModified() {
        return serverModified;
    }

    public void setServerModified(String serverModified) {
        this.serverModified = serverModified;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getHasExplicitSharedMembers() {
        return hasExplicitSharedMembers;
    }

    public void setHasExplicitSharedMembers(Boolean hasExplicitSharedMembers) {
        this.hasExplicitSharedMembers = hasExplicitSharedMembers;
    }

    public String getContentHash() {
        return contentHash;
    }

    public void setContentHash(String contentHash) {
        this.contentHash = contentHash;
    }

    public MediaInfo getMediaInfo() {
        return mediaInfo;
    }

    public void setMediaInfo(MediaInfo mediaInfo) {
        this.mediaInfo = mediaInfo;
    }

}
