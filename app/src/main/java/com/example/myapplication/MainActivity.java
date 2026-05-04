package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPass, edtCfPass;
    private Button btnCreate;
    private ImageButton btnBack;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView rcvFriends, rcvPosts;
    private FriendAdapter friendAdapter;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 1. Setup Toolbar & Drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 2. Setup Friend Suggestions (Horizontal)
        rcvFriends = findViewById(R.id.rcvFriendSuggestions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvFriends.setLayoutManager(layoutManager);

        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Nguyễn Văn A", R.drawable.ic_launcher_background));
        friends.add(new Friend("Trần Thị B", R.drawable.ic_launcher_background));
        friends.add(new Friend("Lê Văn C", R.drawable.ic_launcher_background));

        friendAdapter = new FriendAdapter(friends);
        rcvFriends.setAdapter(friendAdapter);

        rcvPosts = findViewById(R.id.rcv_Posts);
        rcvPosts.setLayoutManager(new LinearLayoutManager(this));

        initViews();
        setupListeners();
    }

    private void initViews() {
        edtName = findViewById(R.id.edit_name_rg);
        edtEmail = findViewById(R.id.edit_email_rg);
        edtPass = findViewById(R.id.edit_password_rg);
        edtCfPass = findViewById(R.id.edit_cfpassword);
        btnCreate = findViewById(R.id.btn_create);
        btnBack = findViewById(R.id.imageButton);
    }

    private void setupListeners() {
        // Nút quay lại màn hình Login
        btnBack.setOnClickListener(v -> finish());

        btnCreate.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String pass = edtPass.getText().toString().trim();
            String cfpass = edtCfPass.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || cfpass.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass.equals(cfpass)) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Gửi kết quả về Activity_Login
            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", name);
            resultIntent.putExtra("email", email);
            resultIntent.putExtra("password", pass);
            setResult(RESULT_OK, resultIntent);

            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
