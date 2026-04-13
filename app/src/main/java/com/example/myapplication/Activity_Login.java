package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Login extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnSignIn;
    private TextView tvRegister, tvForgotPass;
    private String registeredName = "";
    private String registeredEmail = "";
    private String registeredPassword = "";

    private final ActivityResultLauncher<Intent> registerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    registeredName = result.getData().getStringExtra("name");
                    registeredEmail = result.getData().getStringExtra("email");
                    registeredPassword = result.getData().getStringExtra("password");
                    
                    // Tự động điền lại Email và Password sau khi đăng ký thành công
                    edtEmail.setText(registeredEmail);
                    edtPassword.setText(registeredPassword);
                    
                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        setupListeners();
    }

    private void initViews() {
        edtEmail = findViewById(R.id.edit_email_login);
        edtPassword = findViewById(R.id.edit_password_login);
        btnSignIn = findViewById(R.id.btn_signin);
        tvRegister = findViewById(R.id.textView_register_login);
        tvForgotPass = findViewById(R.id.textView_fgpass);
    }

    private void setupListeners() {
        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_Login.this, MainActivity.class);
            registerLauncher.launch(intent);
        });

        btnSignIn.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Chuyển hướng đến trang Activity_Post
            Intent intent = new Intent(Activity_Login.this, Activity_Post.class);

            if (email.equals(registeredEmail)) {
                intent.putExtra("name", registeredName);
            } else {
                String nameFromEmail = email.contains("@") ? email.split("@")[0] : email;
                intent.putExtra("name", nameFromEmail);
            }
            
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
        });

        tvForgotPass.setOnClickListener(v -> 
            Toast.makeText(this, "This function is under development", Toast.LENGTH_SHORT).show()
        );
    }
}
