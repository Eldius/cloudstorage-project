
package net.eldiosantos.cloudstorage.dropbox.pojo;

import java.util.HashMap;
import java.util.Map;

public class SharingInfo {

    private Boolean readOnly;
    private String parentSharedFolderId;
    private String modifiedBy;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getParentSharedFolderId() {
        return parentSharedFolderId;
    }

    public void setParentSharedFolderId(String parentSharedFolderId) {
        this.parentSharedFolderId = parentSharedFolderId;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
