package org.buterblade.thehelperapp;

import android.graphics.Insets;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.buterblade.thehelperapp.databinding.ActivityMainBinding;
import org.buterblade.thehelperapp.navigation.HomeFragment;
import org.buterblade.thehelperapp.navigation.ProfileFragment;
import org.buterblade.thehelperapp.navigation.RequestFragment;
import org.jetbrains.annotations.NonBlocking;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

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
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.frame_home) {
                replaceFragment(new HomeFragment());
                return true;
            }
            if(item.getItemId() == R.id.frame_request) {
                replaceFragment(new RequestFragment());
                return true;
            }
            if(item.getItemId() == R.id.frame_profile) {
                replaceFragment(new ProfileFragment());
                return true;
            }



            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}