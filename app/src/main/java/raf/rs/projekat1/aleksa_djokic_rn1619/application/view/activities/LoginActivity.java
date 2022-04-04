package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.application.R;

public class LoginActivity extends AppCompatActivity {
    public static final String CREDENTIAL_KEY1 = "credentialKey1";
    public static final String CREDENTIAL_KEY2 = "credentialKey2";
    private ImageView imageView;
    private Button loginBtn;
    private EditText username;
    private EditText email;
    private EditText password;
    private TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListeners();
    }

    private void initView() {
        imageView = findViewById(R.id.imageView);
        header = findViewById(R.id.header);
        username = findViewById(R.id.usernameEditText);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        loginBtn = findViewById(R.id.loginBtn);
    }

    private void initListeners() {
        loginBtn.setOnClickListener(view -> {
            if (username.getText().toString().isEmpty()) {
                Toast.makeText(this, "Username cannot be empty!", Toast.LENGTH_SHORT).show();
            }
            else if (!checkEmail(email)) {
                Toast.makeText(this, "Email is not correct", Toast.LENGTH_SHORT).show();
            }
            else if (password.getText().toString().length() < 5) {
                Toast.makeText(this, "Password must have at least 5 characters", Toast.LENGTH_SHORT).show();
            }
            else if (username.getText().toString().startsWith("admin") && password.getText().toString().equals("admin123")) {
                saveCredentials();
                Toast.makeText(this, "Admin has successfully logged in", Toast.LENGTH_SHORT).show();
            }
            else if (password.getText().toString().equals("aleksa123")) {
                saveCredentials();
                Toast.makeText(this, "User has successfully logged in", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(this, "Username or password are not correct!", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean checkEmail(EditText email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();
    }

    private void saveCredentials() {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        sharedPreferences
                .edit()
                .putString(CREDENTIAL_KEY1, username.getText().toString())
                .putString(CREDENTIAL_KEY2, email.getText().toString())
                .apply();
        Intent intent = new Intent(this, BottomNavigationActivity.class);
        startActivity(intent);
    }

}