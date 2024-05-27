package com.example.smartrailway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AutoControl extends AppCompatActivity {

    public Button warnHouseKeepingBtn;
    public Button warnManagerBtn;
    public Button exitBtn;

    public EditText coachNoHouseKeeping;

    public EditText coachNoManager;

    public String phone;
    public String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_control);
        warnHouseKeepingBtn = findViewById(R.id.warnHouseKeepingBtn);
        warnManagerBtn = findViewById(R.id.warnManagerBtn);
        exitBtn = findViewById(R.id.exitBtn);
        coachNoHouseKeeping = findViewById(R.id.coachNoHouseKeeping);
        coachNoManager = findViewById(R.id.coachNoManager);

        warnManagerBtn.setOnClickListener(v->{
            phone = "7385677315";
            msg = "Manager , Please check Coach "+coachNoManager.getText().toString();
            coachNoManager.setText("");
            if(ContextCompat.checkSelfPermission(AutoControl.this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                //permission granted
                //create method
                sendMsg();
            }
            else {
                //permission is not granted
                // request for permission
                ActivityCompat.requestPermissions(AutoControl.this, new String[]{Manifest.permission.SEND_SMS}, 100);
            }
        });

        warnHouseKeepingBtn.setOnClickListener(v->{
            phone = "7385677315";
            msg = "HouseKeeping , Please check Coach "+coachNoHouseKeeping.getText().toString();
            coachNoHouseKeeping.setText("");
            if(ContextCompat.checkSelfPermission(AutoControl.this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                //permission granted
                //create method
                sendMsg();
            }
            else {
                //permission is not granted
                // request for permission
                ActivityCompat.requestPermissions(AutoControl.this, new String[]{Manifest.permission.SEND_SMS}, 100);
            }
        });

        exitBtn.setOnClickListener(v->{
            Intent intent = new Intent(AutoControl.this,ServiceActivity.class);
            startActivity(intent);
        });
    }
    @Override
    public  void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode ==100 && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendMsg();
        }else{
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }
    private void sendMsg() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, msg, null, null);
        Toast.makeText(this, "Warning sent successfully", Toast.LENGTH_SHORT).show();
    }
}