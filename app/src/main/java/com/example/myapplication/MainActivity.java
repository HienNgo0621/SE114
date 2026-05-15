package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ListView lvPosts;
    private RecyclerView rcvFriends;
    private FriendAdapter friendAdapter;
    private PostAdapter postAdapter;
    private List<Post> postList;


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
        friends.add(new Friend("Nguyễn Văn A", ""));
        friends.add(new Friend("Trần Thị B", ""));
        friends.add(new Friend("Lê Văn C", ""));

        friendAdapter = new FriendAdapter(friends);
        rcvFriends.setAdapter(friendAdapter);

        // 3. Setup Posts (Bài viết cũ của bạn)
        lvPosts = findViewById(R.id.lv_myposts);

        // Khởi tạo danh sách bài viết
        List<Post> postList = new ArrayList<>();
        postList.add(new Post("Nguyễn Văn A", "10 phút trước", "Hôm nay trời đẹp quá!", ""));
        postAdapter = new PostAdapter(this, postList, null);
        lvPosts.setAdapter(postAdapter);

        // Khởi tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://blackntt.net:8111/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

// Gọi API lấy danh sách
        service.getAll().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Employee> employees = response.body();
                    // TODO: Tạo một EmployeeAdapter (tương tự FriendAdapter) và truyền list employees này vào
                    // Ví dụ: employeeAdapter = new EmployeeAdapter(employees);
                    // rcvFriends.setAdapter(employeeAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lỗi mạng: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_profile) {
            // Chuyển sang Activity_Information (Profile)
            Intent intent = new Intent(MainActivity.this, Activity_Information.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.menu_sort_date) {
            sortPostsByDate();
            return true;
        }
        else if (id == R.id.menu_sort_author) {
            sortPostsByAuthor();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortPostsByDate() {
        if (postList != null && !postList.isEmpty()) {
            Collections.sort(postList, (p1, p2) -> {
                if (p1.getDate() == null || p2.getDate() == null) return 0;
                return p2.getDate().compareTo(p1.getDate()); // Mới nhất lên đầu
            });
            postAdapter.setPostList(postList);
            Toast.makeText(this, "Sorted by date", Toast.LENGTH_SHORT).show();
        }
    }

    private void sortPostsByAuthor() {
        if (postList != null && !postList.isEmpty()) {
            Collections.sort(postList, (p1, p2) -> {
                if (p1.getAuthorName() == null || p2.getAuthorName() == null) return 0;
                return p1.getAuthorName().compareToIgnoreCase(p2.getAuthorName());
            });
            postAdapter.setPostList(postList);
            Toast.makeText(this, "Sorted by author", Toast.LENGTH_SHORT).show();
        }
    }
}