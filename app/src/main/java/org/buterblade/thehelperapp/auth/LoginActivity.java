package org.buterblade.thehelperapp.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.buterblade.thehelperapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;
    Button registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ui_login);

        registerLink = findViewById(R.id.btn_register_link);
        email = findViewById(R.id.lg_email);
        password = findViewById(R.id.lg_password);
        login = findViewById(R.id.btn_login);

        registerLink.setOnClickListener(action -> {
            Intent intent = new Intent(
                    LoginActivity.this,
                    RegistrationActivity.class
            );
            startActivity(intent);
        });
    }
}
