
package net.eldiosantos.cloudstorage.dropbox.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyGroup {

    @SerializedName("template_id")
    @Expose
    private String templateId;
    @SerializedName("fields")
    @Expose
    private List<Field> fields = null;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

}
