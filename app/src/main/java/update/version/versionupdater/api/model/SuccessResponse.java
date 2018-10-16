package update.version.versionupdater.api.model;

import com.google.gson.annotations.SerializedName;

public class SuccessResponse {
    @SerializedName("success")
    private boolean success;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
