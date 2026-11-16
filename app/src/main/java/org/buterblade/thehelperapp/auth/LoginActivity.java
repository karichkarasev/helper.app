package org.buterblade.thehelperapp.auth;

import static android.os.Build.VERSION_CODES_FULL.R;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.buterblade.thehelperapp.MainActivity;
import org.buterblade.thehelperapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText emailOutput;
    EditText passwordOutput;
    Button loginButton;
    Button registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(org.buterblade.thehelperapp.R.layout.activity_login);
        View decoreView = getWindow().getDecorView();

        decoreView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {

            @NonNull
            @Override
            public WindowInsets onApplyWindowInsets(@NonNull View view, @NonNull WindowInsets windowInsets) {
                int left = windowInsets.getSystemWindowInsetLeft();
                int top = windowInsets.getStableInsetTop();
                int right = windowInsets.getStableInsetRight();
                int bottom = windowInsets.getStableInsetBottom();

                view.setPadding(left, top, right, bottom);
                return windowInsets.consumeSystemWindowInsets();
            }
        });


        registerLink = findViewById(org.buterblade.thehelperapp.R.id.btn_register_link);
        emailOutput = findViewById(org.buterblade.thehelperapp.R.id.lg_email);
        passwordOutput = findViewById(org.buterblade.thehelperapp.R.id.lg_password);
        loginButton = findViewById(org.buterblade.thehelperapp.R.id.btn_login);

        loginButton.setOnClickListener(action -> loginUser());

        registerLink.setOnClickListener(action -> linkToRegister());
    }

    private void loginUser() {
        String email = emailOutput.getText().toString();
        String password = passwordOutput.getText().toString();

        if(email.isEmpty() || password.isEmpty()) {
            showMessage("Заполните все поля!");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showMessage("Некорректная почта");
            return;
        }
        if(email.equals("admin@email.com") && password.equals("admin")) {
            showMessage("Вы зашли на свой аккаунт");
            Intent intent = new Intent(
                    this,
                    MainActivity.class
            );
            startActivity(intent);
        } else showMessage("Некорректные данные!");
        emailOutput.setText(null);
        passwordOutput.setText(null);
    }

    private void linkToRegister() {
        Intent intent = new Intent(
                LoginActivity.this,
                RegisterActivity.class
        );
        startActivity(intent);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }
}
