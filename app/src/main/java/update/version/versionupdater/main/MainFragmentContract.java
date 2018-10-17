package update.version.versionupdater.main;

import java.util.List;

import update.version.versionupdater.api.model.Version;
import update.version.versionupdater.api.model.WhatsNew;
import update.version.versionupdater.base.BasePresenter;
import update.version.versionupdater.base.BaseView;

public interface MainFragmentContract {

    interface MainView extends BaseView{
        void showWhatsNew(List<WhatsNew> whatsNewList);
        void showVersion(Version version);
        void onMessageEdit(Version version);
        void onWhatsNewEdit(WhatsNew whatsNew);
        void showAddInterface();
    }

    interface MainPresenter extends BasePresenter{
        void handleAddClick();
        void handleWhatsNewClick(WhatsNew whatsNew);
        void handleMessageEditClick(Version message);
        void updateMessage(Version version);
        void updateWhatsNew(WhatsNew whatsNew);
    }

}
;