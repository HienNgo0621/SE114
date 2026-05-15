package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
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
    private ListView lvPosts;
    private RecyclerView rcvFriends;
    private FriendAdapter friendAdapter;
    private PostAdapter postAdapter;
    private List<Post> postList;
    private EditText edtPostContent;
    private Button btnPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtPostContent = findViewById(R.id.edt_post_content);
        btnPost = findViewById(R.id.btn_post);
        btnPost.setOnClickListener(v -> {
            String content = edtPostContent.getText().toString().trim();
            if (!content.isEmpty()) {
                postList.add(0, new Post("Tôi (Bạn)", "Vừa xong", content, ""));
                postAdapter.setPostList(postList);
                edtPostContent.setText("");
                Toast.makeText(this, "Đăng bài thành công!", Toast.LENGTH_SHORT).show();
            }
        });

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

        lvPosts = findViewById(R.id.lv_myposts);
        postList = new ArrayList<>();

        postList.add(new Post("Nguyễn Văn A", "10 phút trước", "Hôm nay trời đẹp quá!", ""));
        postList.add(new Post("Trần Thị B", "2024-05-14 09:30", "Hôm nay trời đẹp quá.", ""));

        postAdapter = new PostAdapter(this, postList, null);
        lvPosts.setAdapter(postAdapter);

        loadPostsFromApi();
    }

    private void loadPostsFromApi() {
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
                    postList.clear();

                    for (Employee emp : employees) {
                        postList.add(new Post(emp.getName(), "2024-05-15", "Lương: " + emp.getSalary(), ""));
                    }
                    postAdapter.setPostList(postList);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lỗi gọi API. Đang hiển thị dữ liệu mẫu.", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(MainActivity.this, Activity_Information.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.menu_sort_date) {
            Collections.sort(postList, (p1, p2) -> p2.getDate().compareToIgnoreCase(p1.getDate()));
            postAdapter.setPostList(postList);
            Toast.makeText(this, "Đã sắp xếp theo ngày", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.menu_sort_author) {
            Collections.sort(postList, (p1, p2) -> p1.getAuthorName().compareToIgnoreCase(p2.getAuthorName()));
            postAdapter.setPostList(postList);
            Toast.makeText(this, "Đã sắp xếp theo tên tác giả", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}