package update.version.versionupdater.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QueryResponse {

    @SerializedName("version")
    private Version version;

    @SerializedName("whats_new")
    private List<WhatsNew> whatsNew;


    @SerializedName("name")
    private String error;


    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public List<WhatsNew> getWhatsNew() {
        return whatsNew;
    }

    public void setWhatsNew(List<WhatsNew> whatsNew) {
        this.whatsNew = whatsNew;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
