package update.version.versionupdater.base;

public interface BaseView<T> {
    void notifyUser(int string);
    boolean isOffline();
    void showProgress();
    void hideProgress();
    void showNoInternetMessage();
}
