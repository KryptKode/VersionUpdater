package update.version.versionupdater.main;

import android.text.TextUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import update.version.versionupdater.R;
import update.version.versionupdater.api.Api;
import update.version.versionupdater.api.ServiceGenerator;
import update.version.versionupdater.api.model.Version;
import update.version.versionupdater.api.model.WhatsNew;

public class MainFragmentPresenter implements MainFragmentContract.MainPresenter {

    private MainFragmentContract.MainView view;
    private Api api;
    private Disposable disposable;

    public MainFragmentPresenter(MainFragmentContract.MainView view) {
        this.view = view;
        api = ServiceGenerator.createService(Api.class);
    }

    @Override
    public void handleAddClick() {
        view.showAddInterface();
    }

    @Override
    public void handleWhatsNewClick(WhatsNew whatsNew) {
        view.onWhatsNewEdit(whatsNew);
    }

    @Override
    public void handleMessageEditClick(Version message) {
        view.onMessageEdit(message);
    }

    @Override
    public void updateMessage(Version version) {
        if(isOnline()){
            view.showProgress();
            disposable = api.updateVersion(version)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(version1  -> {
                        view.hideProgress();
                        if (version1 != null) {
                            if(!TextUtils.isEmpty(version1.getMessage())){

                                view.showVersion(version1);
                            }else{
                                view.notifyUser(R.string.error_occurred);
                                Timber.e("Failed with error: %s", version1.getMessage());
                            }
                        }else{
                            view.notifyUser(R.string.no_data_error);
                        }
                    }, throwable -> {
                        view.hideProgress();
                        view.notifyUser(R.string.error_occurred);
                        Timber.e(throwable);
                    });
        }
    }

    @Override
    public void updateWhatsNew(WhatsNew whatsNew) {
        if(isOnline()){
            view.showProgress();
            disposable = api.updateWhatsNew(whatsNew)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(whatsNewList -> {
                        view.hideProgress();
                        if (whatsNewList != null) {
                            view.showWhatsNew(whatsNewList);
                        }else{
                            view.notifyUser(R.string.no_data_error);
                        }
                    }, throwable -> {
                        view.hideProgress();
                        view.notifyUser(R.string.error_occurred);
                        Timber.e(throwable);
                    });
        }
    }

    @Override
    public void start() {
        if(isOnline()){
            getData();

        }
    }

    private void getData() {
        view.showProgress();
        disposable = api.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(queryResponse -> {
                    view.hideProgress();
                    if (queryResponse != null) {
                        if(TextUtils.isEmpty(queryResponse.getError())){
                            view.showVersion(queryResponse.getVersion());
                            if (queryResponse.getWhatsNew() != null) {
                                view.showWhatsNew(queryResponse.getWhatsNew());
                            }
                        }else{
                            view.notifyUser(R.string.error_occurred);
                            Timber.e("Failed with error: %s", queryResponse.getError());
                        }
                    }else{
                        view.notifyUser(R.string.no_data_error);
                    }
                }, throwable -> {
                    view.hideProgress();
                    view.notifyUser(R.string.error_occurred);
                    Timber.e(throwable);
                });
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
    public void stop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
