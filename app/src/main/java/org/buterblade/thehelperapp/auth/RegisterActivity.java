package org.buterblade.thehelperapp.auth;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.buterblade.thehelperapp.MainActivity;
import org.buterblade.thehelperapp.R;
import org.buterblade.thehelperapp.model.UserEntity;
import org.buterblade.thehelperapp.retrofit.RetrofitService;
import org.buterblade.thehelperapp.retrofit.UserEntityApi;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText nameOutput;
    EditText emailOutput;
    EditText passwordOutput;
    Button registerButton;
    Button loginLink;
    RetrofitService retrofitService = new RetrofitService();
    UserEntityApi userEntityApi = retrofitService.getRetrofit().create(UserEntityApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

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

        nameOutput = findViewById(R.id.rg_name);
        emailOutput = findViewById(R.id.rg_email);
        passwordOutput = findViewById(R.id.rg_password);

        registerButton = findViewById(R.id.btn_register);
        loginLink = findViewById(R.id.btn_login_link);
        loginLink.setOnClickListener(action -> {
            Intent intent = new Intent(
                    RegisterActivity.this,
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

        UserEntity user = new UserEntity();
        user.setFullName(name);
        user.setEmail(email);
        user.setPassword(password);

        userEntityApi.save(user)
                        .enqueue(new Callback<UserEntity>() {
                            @Override
                            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                                if(response.isSuccessful()) {
                                    showMessage("Успешная регистрация");
                                    Intent intent = new Intent(
                                            RegisterActivity.this,
                                            MainActivity.class
                                    );
                                    startActivity(intent);
                                } else if (response.code() == 409) {
                                    try {
                                        String errorBody = response.errorBody().string();
                                        JSONObject jsonObject = new JSONObject(errorBody);
                                        showMessage(jsonObject.getString("message"));
                                    } catch (Exception e) {}
                                    emailOutput.setText(null);
                                }
                            }
                            @Override
                            public void onFailure(Call<UserEntity> call, Throwable t) {showMessage("Ошибка соединения");}
                        });
    }
    private void showMessage(String message) {
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }

}
