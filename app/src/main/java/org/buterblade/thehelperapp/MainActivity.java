package org.buterblade.thehelperapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        showMainMenu();
    }
    private void showMainMenu() {
        setContentView(R.layout.ui_client_main_menu);
        applyWindowInsets();
        setupNavigation(R.id.nav_main);
    }
    private void showRequests() {
        setContentView(R.layout.ui_cient_requests);
        applyWindowInsets();
        setupNavigation(R.id.nav_requests);
    }
    private void showProfile() {
        setContentView(R.layout.ui_client_settings);
        applyWindowInsets();
        setupNavigation(R.id.nav_profile);
        setupProfileActions();
    }
    private void setupNavigation(int activeId) {
        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(activeId);
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == activeId) return true;
            if (id == R.id.nav_main) showMainMenu();
            else if (id == R.id.nav_requests) showRequests();
            else if (id == R.id.nav_profile) showProfile();
            return true;
        });
        if (activeId == R.id.nav_main) {
            findViewById(R.id.btn_create_request).setOnClickListener(v -> showRequests());
        }
    }

    private void setupProfileActions() {
        setupActionRow(R.id.item_history, R.drawable.ic_clipboard, R.string.action_history);
        setupActionRow(R.id.item_personal_data, R.drawable.ic_shield, R.string.action_personal_data);
        setupActionRow(R.id.item_notifications, R.drawable.ic_bell, R.string.action_notifications);
        setupActionRow(R.id.item_support, R.drawable.ic_info, R.string.action_support);
        setupActionRow(R.id.item_settings, R.drawable.ic_settings, R.string.action_settings);
    }

    private void setupActionRow(int layoutId, int iconRes, int textRes) {
        android.view.View layout = findViewById(layoutId);
        ((ImageView) layout.findViewById(R.id.iv_action_icon)).setImageResource(iconRes);
        ((TextView) layout.findViewById(R.id.tv_action_name)).setText(textRes);
    }

    private void applyWindowInsets() {
        android.view.View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
                return insets;
            });
        }
    }
}