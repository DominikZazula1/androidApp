package com.example.tictactoe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NicknameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View view) {
        if (view.getId() == R.id.nicknameButton) {
            String nick = ((EditText) findViewById(R.id.nickname)).getText().toString();
            try {
                String filePath = getFilesDir().getAbsolutePath() + "/nick.txt";
                Files.write(Paths.get(filePath), nick.getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(this, GameTypeActivity.class));
        }
    }
}