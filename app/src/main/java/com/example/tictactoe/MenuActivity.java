package com.example.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClick(View view) throws IOException {
        if (view.getId() == R.id.playButton) {
                startActivity(new Intent(this, GameTypeActivity.class));
        }
        else if(view.getId() == R.id.gameHistoryButton){
            startActivity(new Intent(this, HistoryActivity.class));
        }
    }

}