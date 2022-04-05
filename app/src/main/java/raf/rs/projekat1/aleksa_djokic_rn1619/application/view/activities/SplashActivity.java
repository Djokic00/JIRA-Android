package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.splashscreen.SplashScreen;
import com.example.application.R;

import static raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.LoginActivity.PACKAGE_NAME;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLogin();
    }

    public void checkLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        String message = sharedPreferences.getString(LoginActivity.CREDENTIAL_KEY1, null);
        Intent intent;
        if (message == null) {
            intent = new Intent(this, LoginActivity.class);
        }
        else {
            intent = new Intent(this, BottomNavigationActivity.class);
        }
        startActivity(intent);
        finish();
    }
}