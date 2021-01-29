package com.example.tictactoe;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    public static int historyLinesCount;
    private ListView list ;
    private ArrayAdapter<String> adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        String filePath = getFilesDir().getAbsolutePath() +"/history.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            historyLinesCount=0;
            list = (ListView) findViewById(R.id.historyList);
            ArrayList<String> hisL = new ArrayList<>();
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                hisL.add(line);
                historyLinesCount++;
            }
            adapter = new ArrayAdapter(this, R.layout.list_item, hisL);
            list.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    }

