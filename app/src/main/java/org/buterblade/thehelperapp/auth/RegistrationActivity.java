package org.buterblade.thehelperapp.auth;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.buterblade.thehelperapp.MainActivity;
import org.buterblade.thehelperapp.R;

public class RegistrationActivity extends AppCompatActivity {

    EditText nameOutput;
    EditText emailOutput;
    EditText passwordOutput;

    Button registerButton;
    Button loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_registration);

        nameOutput = findViewById(R.id.rg_name);
        emailOutput = findViewById(R.id.rg_email);
        passwordOutput = findViewById(R.id.rg_password);

        registerButton = findViewById(R.id.btn_register);
        loginLink = findViewById(R.id.btn_login_link);
        loginLink.setOnClickListener(action -> {
            Intent intent = new Intent(
                    RegistrationActivity.this,
                    LoginActivity.class
            );
            startActivity(intent);
        });
        registerButton.setOnClickListener(v -> registerUser());
    }
    private void registerUser() {

        String name = nameOutput.getText().toString().trim();
        String email = emailOutput.getText().toString().trim();
        String password = passwordOutput.getText().toString();

        if (name.isEmpty() || email.isEmpty()
                || password.isEmpty()) {
            showMessage("Заполните все поля");
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS
                .matcher(email).matches()) {
            showMessage("Некорректная почта");
            return;
        }
        if (password.length() < 8) {
            showMessage("Пароль должен содержать минимум 8 символов");
            return;
        }
        showMessage("Регистрация успешна!");
        Intent intent = new Intent(
                RegistrationActivity.this,
                MainActivity.class
        );
        startActivity(intent);
    }
    private void showMessage(String message) {
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }

}
