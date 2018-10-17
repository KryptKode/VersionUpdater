package update.version.versionupdater.add;

import java.util.List;

import update.version.versionupdater.api.model.Version;
import update.version.versionupdater.api.model.WhatsNew;
import update.version.versionupdater.base.BasePresenter;
import update.version.versionupdater.base.BaseView;

public interface AddFragmentContract {
    interface AddView extends BaseView{
        void showAddMessageDialog();
        void showAddWhatsNewDialog();
        void incrementVersion(int count);
        void addWhatsNew(WhatsNew whatsNew);
        void onEditWhatsNew(WhatsNew whatsNew);
        void showExitConfirm();
        void onAddMessage(Version version);
        void onEditMessage(Version version);

        void onSuccess();
    }


    interface AddPresenter extends BasePresenter{
        void handleIncrementVersionClick(int newVersion);
        void handleAddMessageClick(String message);
        void handleAddWhatsNewClick(int count);

        void handleSaveClick(Version version, List<WhatsNew> whatsNewList);

        void handleExit();

        void onAddMessage(Version message, boolean isEdit);
        void onAddWhatsNew(WhatsNew whatsNew, boolean isEdit);





    }
}
