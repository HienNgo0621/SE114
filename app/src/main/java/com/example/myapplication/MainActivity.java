package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView rcvFriends, rcvPosts;
    private FriendAdapter friendAdapter;
    private PostAdapter postAdapter; // Dùng cho bài viết

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Chỉ load giao diện activity_main
        setContentView(R.layout.activity_main);

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

        // 3. Setup Posts (Bài viết cũ của bạn)
        rcvPosts = findViewById(R.id.rcv_Posts);
        rcvPosts.setLayoutManager(new LinearLayoutManager(this));

        // TODO: Bạn thêm code khởi tạo danh sách Post và setAdapter cho rcvPosts ở đây giống như project cũ
    }
}