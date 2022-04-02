package application;

import android.app.Application;
import timber.log.Timber;

public class Main extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
