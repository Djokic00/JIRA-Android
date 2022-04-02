package view.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.splashscreen.SplashScreen;
import com.example.application.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> {
            checkLogin();
            return false;
        });
        setContentView(R.layout.activity_splash_screen);
    }

    public void checkLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        String message = sharedPreferences.getString(LoginActivity.CREDENTIAL_KEY1, null);
        Intent intent;
        if (message == null) {
            intent = new Intent(this, LoginActivity.class);
        }
        else {
            intent = new Intent(this, BottomNavigationActivity.class);
        }
        startActivity(intent);
    }
}