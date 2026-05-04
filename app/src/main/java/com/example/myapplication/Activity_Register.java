package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Register extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPass, edtCfPass;
    private Button btnCreate;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
