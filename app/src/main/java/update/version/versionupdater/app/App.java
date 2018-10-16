package update.version.versionupdater.app;


import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;
import update.version.versionupdater.BuildConfig;


public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }


        initializeLeakCanary();

    }



    private void initializeLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }


}
