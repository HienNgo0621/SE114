package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Activity_Information extends AppCompatActivity {

    private TextView tvWelcomeName;
    private EditText edtName, edtEmail, edtAddress, edtAvtURL, edtDescription;
    private Button btnSave, btnLogout;
    private ImageView imgAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        initViews();
        setupData();
        setupListeners();
    }

    private void initViews() {
        tvWelcomeName = findViewById(R.id.textView_mainname_inf);
        edtName = findViewById(R.id.edit_name_inf);
        edtEmail = findViewById(R.id.edit_email_inf);
        edtAddress = findViewById(R.id.edit_addr_inf);
        edtAvtURL = findViewById(R.id.edit_avtURL);
        edtDescription = findViewById(R.id.editText_descript);
        btnSave = findViewById(R.id.btn_save);
        btnLogout = findViewById(R.id.btn_logout);
        imgAvatar = findViewById(R.id.imageView_avt_inf);
    }

    private void setupData() {
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");

        if (name != null && !name.isEmpty()) {
            edtName.setText(name);
            tvWelcomeName.setText(name + "!");
        }
        
        if (email != null && !email.isEmpty()) {
            edtEmail.setText(email);
        }
    }

    private void setupListeners() {
        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String avatarUrl = edtAvtURL.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Name cannot be empty!", Toast.LENGTH_SHORT).show();
            } else {
                tvWelcomeName.setText(name + "!");
            }

            // Hiển thị ảnh từ URL sử dụng Glide
            if (!avatarUrl.isEmpty()) {
                Glide.with(this)
                        .load(avatarUrl)
                        .placeholder(R.drawable.baseline_image_24)
                        .error(R.drawable.baseline_image_24)
                        .circleCrop()
                        .into(imgAvatar);
                Toast.makeText(this, "Updating avatar...", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(this, "Information saved!", Toast.LENGTH_SHORT).show();
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_Information.this, Activity_Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
