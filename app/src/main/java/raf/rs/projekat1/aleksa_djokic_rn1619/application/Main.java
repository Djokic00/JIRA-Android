package raf.rs.projekat1.aleksa_djokic_rn1619.application;

import android.app.Application;
import timber.log.Timber;

public class Main extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
