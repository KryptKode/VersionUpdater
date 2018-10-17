package update.version.versionupdater.add;

import android.text.TextUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import update.version.versionupdater.R;
import update.version.versionupdater.api.Api;
import update.version.versionupdater.api.ServiceGenerator;
import update.version.versionupdater.api.model.Version;
import update.version.versionupdater.api.model.WhatsNew;

public class AddFragmentPresenter implements AddFragmentContract.AddPresenter {
    public static final int MAX_WHATS_NEW = 4;
    private AddFragmentContract.AddView view;
    private Api api;
    private Disposable disposable;

    public AddFragmentPresenter(AddFragmentContract.AddView view) {
        this.view = view;
        api = ServiceGenerator.createService(Api.class);
    }

    @Override
    public void handleIncrementVersionClick(int newVersion) {
        view.incrementVersion(newVersion + 1);
    }

    @Override
    public void handleAddMessageClick(String message) {
        view.showAddMessageDialog();
    }

    @Override
    public void handleAddWhatsNewClick(int count) {
        if(count <4){
            view.showAddWhatsNewDialog();
        }else{
            view.notifyUser(R.string.max_whats_new_error);
        }
    }

    @Override
    public void handleSaveClick(Version version, List<WhatsNew> whatsNewList) {
        if(isOnline()){
            view.showProgress();
            disposable = Observable.zip(api.addVersion(version), api.addWhatsNew(whatsNewList), (version1, successResponse) -> successResponse.isSuccess(), false)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aBoolean -> {
                        view.hideProgress();
                        if(aBoolean){
                            view.notifyUser(R.string.update_success);
                            view.onSuccess();
                        }else{
                            view.notifyUser(R.string.error_occurred);
                            Timber.e("Failed with error: %s", aBoolean);
                        }
                    }, throwable -> {
                        view.hideProgress();
                        view.notifyUser(R.string.error_occurred);
                        Timber.e(throwable);
                    });

        }

    }

    private boolean isOnline(){
        if (view.isOffline()){
            view.showNoInternetMessage();
            view.hideProgress();
            return false;
        }
        return true;
    }

    @Override
    public void handleExit() {
        view.showExitConfirm();
    }

    @Override
    public void onAddMessage(Version message, boolean isEdit) {
        if (isEdit){
            view.onEditMessage(message);
        }else{
            view.onAddMessage(message);
        }
    }

    @Override
    public void onAddWhatsNew(WhatsNew whatsNew, boolean isEdit) {
        if (isEdit){
            view.onEditWhatsNew(whatsNew);
        }else{
            view.addWhatsNew(whatsNew);
        }
    }



    @Override
    public void start() {

    }

    @Override
    public void stop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
