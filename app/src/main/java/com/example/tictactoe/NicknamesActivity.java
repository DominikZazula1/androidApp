package com.example.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NicknamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nicknames);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View view) {
        if (view.getId() == R.id.nicknameButton) {
            String nick = ((EditText) findViewById(R.id.nickname)).getText().toString();
            String nick2 = ((EditText) findViewById(R.id.nickname2)).getText().toString();
            try {
                FileWriter fw = new FileWriter(getFilesDir().getAbsolutePath() + "/nick.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw);
                out.println(nick);
                out.println(nick2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(this, GameActivity.class));
        }
    }
}