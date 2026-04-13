package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Activity_Post extends AppCompatActivity {

    private EditText edtInput;
    private Button btnPost;
    private ListView lvPosts;
    private PostAdapter adapter;
    private static List<Post> postList = new ArrayList<>(); // Sử dụng static để lưu trong bộ nhớ (memory)

    private String currentUserEmail;
    private String currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // Nhận thông tin người dùng từ Login
        currentUserName = getIntent().getStringExtra("name");
        currentUserEmail = getIntent().getStringExtra("email");

        initViews();
        setupListView();
        setupListeners();

        // Thêm một số bài đăng mẫu nếu danh sách trống
        if (postList.isEmpty()) {
            postList.add(new Post("Author", "14/04/2026", "Thanks for your support!", ""));
            postList.add(new Post("Author", "14/04/2026", "Hello! This is test content.", ""));
        }
        adapter.notifyDataSetChanged();
    }

    private void initViews() {
        edtInput = findViewById(R.id.edt_post_input);
        btnPost = findViewById(R.id.btn_post_submit);
        lvPosts = findViewById(R.id.lv_posts);
    }

    private void setupListView() {
        adapter = new PostAdapter(this, postList, post -> {
            // Khi click vào avatar hoặc tên: chuyển sang trang cá nhân
            Intent intent = new Intent(Activity_Post.this, Activity_Information.class);
            intent.putExtra("name", post.getAuthorName());
            // Vì đây là demo, chúng ta giả định email của Alice hoặc người đăng là email hiện tại hoặc để trống
            intent.putExtra("email", currentUserEmail); 
            startActivity(intent);
        });
        lvPosts.setAdapter(adapter);
    }

    private void setupListeners() {
        btnPost.setOnClickListener(v -> {
            String content = edtInput.getText().toString().trim();
            if (content.isEmpty()) {
                Toast.makeText(this, "Please write something!", Toast.LENGTH_SHORT).show();
                return;
            }

            String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
            
            // Tạo post mới với thông tin người dùng hiện tại
            Post newPost = new Post(currentUserName, date, content, "");
            postList.add(0, newPost); // Thêm vào đầu danh sách
            adapter.notifyDataSetChanged();
            
            edtInput.setText("");
            Toast.makeText(this, "Posted successfully!", Toast.LENGTH_SHORT).show();
        });
    }
}
