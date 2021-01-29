package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.IOException;

public class GameTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_type);
    }

    public void onClick(View view) throws IOException {
        if (view.getId() == R.id.localButton) {
                startActivity(new Intent(this, NicknamesActivity.class));

        }
    }
}