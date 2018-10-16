package update.version.versionupdater.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WhatsNew implements Parcelable {

    @SerializedName("id")
    private int id;
    @SerializedName("version_id")
    private int versionId;

    @SerializedName("whats_new_message")
    private String whatsNewMessage;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public String getWhatsNewMessage() {
        return whatsNewMessage;
    }

    public void setWhatsNewMessage(String whatsNewMessage) {
        this.whatsNewMessage = whatsNewMessage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.versionId);
        dest.writeString(this.whatsNewMessage);
    }

    public WhatsNew() {
    }

    protected WhatsNew(Parcel in) {
        this.id = in.readInt();
        this.versionId = in.readInt();
        this.whatsNewMessage = in.readString();
    }

    public static final Parcelable.Creator<WhatsNew> CREATOR = new Parcelable.Creator<WhatsNew>() {
        @Override
        public WhatsNew createFromParcel(Parcel source) {
            return new WhatsNew(source);
        }

        @Override
        public WhatsNew[] newArray(int size) {
            return new WhatsNew[size];
        }
    };
}
