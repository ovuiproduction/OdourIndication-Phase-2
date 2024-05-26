package com.example.smartrailway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public EditText usernametext;
    public EditText passwordtext;
    public Button logbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernametext = findViewById(R.id.username);
        passwordtext = findViewById(R.id.password);
        logbtn = findViewById(R.id.logbtn);

        logbtn.setOnClickListener(v -> {
            String username = usernametext.getText().toString();
            String password = passwordtext.getText().toString();

            if (username.equals("admin") && password.equals("admin")) {
                Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(intent);
            }
        });
    }
}
