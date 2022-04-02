package view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;;
import com.example.application.R;
import view.activities.LoginActivity;

public class Profile extends Fragment {

   private ImageView imageView;
   private TextView username;
   private TextView email;
   private Button logoutBtn;
   private SharedPreferences sharedPreferences;

    public Profile() {
        super(R.layout.fragment_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initListeners();
    }

    private void initView(View view) {
        sharedPreferences = this.getActivity().getSharedPreferences(this.getActivity().getPackageName(), Context.MODE_PRIVATE);
        String usernameMessage = sharedPreferences.getString(LoginActivity.CREDENTIAL_KEY1, null);
        String emailMessage = sharedPreferences.getString(LoginActivity.CREDENTIAL_KEY2, null);

        imageView = view.findViewById(R.id.logoutImage);
        username = view.findViewById(R.id.usernameProfile);
        email = view.findViewById(R.id.emailProfile);
        logoutBtn = view.findViewById(R.id.logoutBtn);

        username.setText(usernameMessage);
        email.setText(emailMessage);
    }

    private void initListeners() {
        logoutBtn.setOnClickListener(view -> {
            sharedPreferences.edit().clear().apply();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });
    }
}
