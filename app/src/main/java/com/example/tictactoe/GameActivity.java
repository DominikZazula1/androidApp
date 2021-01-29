package com.example.tictactoe;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class GameActivity extends AppCompatActivity {

    String nickname;
    String nickname2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        String filePath = getFilesDir().getAbsolutePath() + "/nick.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            nickname = br.readLine();
            nickname2= br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        ((TextView) findViewById(R.id.nickname)).setText(nickname);
        ((TextView) findViewById(R.id.nickname2)).setText(nickname2);
    }

    private int turnOfPlayerNumber = 0;
    private final String[][] board = new String[3][3];

    public void makeMove(View view) {
        int tag = Integer.parseInt(String.valueOf(view.getTag()));
        int id = view.getId();
        int row = tag / 3;
        int col = tag % 3;
        if (board[row][col] == null) {
            if (turnOfPlayerNumber % 2 == 0) {
                ((TextView) findViewById(id)).setText("X");
                board[row][col] = "X";
            } else {
                ((TextView) findViewById(id)).setText("O");
                board[row][col] = "O";
            }
            turnOfPlayerNumber++;
        }

        String st = checkWin();
        if (!st.equals("Game not finished")) {
            int[] idList = {
                    R.id.Button1,
                    R.id.Button2,
                    R.id.Button3,
                    R.id.Button4,
                    R.id.Button5,
                    R.id.Button6,
                    R.id.Button7,
                    R.id.Button8,
                    R.id.Button9
            };
            for (int temp : idList) {
                ((Button) findViewById(temp)).setClickable(false);
            }
            TextView t = (TextView) findViewById(R.id.winner);
            t.setVisibility(View.VISIBLE);
            t.setText(st);
        }
    }

    private boolean xwin = false, owin = false;

    //check winning conditions
    public String checkWin() {
        int x = 0, o = 0, xk = 0, ok = 0, xw = 0, ow = 0, xp1 = 0, op1 = 0, xp2 = 0, op2 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Objects.equals(board[i][j], "X")) {
                    if (i == j) {
                        xp1++;
                    }
                    if (i + j == 2) {
                        xp2++;
                    }
                    x++;
                    xk++;
                }
                if (Objects.equals(board[i][j], "O")) {
                    if (i == j) {
                        op1++;
                    }
                    if (i + j == 2) {
                        op2++;
                    }
                    o++;
                    ok++;
                }
                if (Objects.equals(board[j][i], "X")) {
                    xw++;
                }
                if (Objects.equals(board[j][i], "O")) {
                    ow++;
                }
            }
            if (xk == 3 || xw == 3 || xp1 == 3 || xp2 == 3) {
                xwin = true;
            }
            if (ok == 3 || ow == 3 || op1 == 3 || op2 == 3) {
                owin = true;
            }
            xk = ok = xw = ow = 0;
        }
        boolean a = false;
        if (HistoryActivity.historyLinesCount < 25)
            a = true;
        try (FileWriter fw = new FileWriter(getFilesDir().getAbsolutePath() + "/history.txt", a);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            if ((xwin && owin) || Math.abs(x - o) > 1) {
                return "Impossible";
            } else if (xwin) {
                out.println(nickname+" win | player2 lost");
                return "X wins";
            } else if (owin) {
                out.println(nickname+" lost | player2 win");
                return "O wins";
            } else if (x + o < 9) {
                return "Game not finished";
            } else {
                out.println(nickname+" draw | player2 draw");
                return "Draw";
            }
        } catch (IOException e) {
            if ((xwin && owin) || Math.abs(x - o) > 1) {
                return "Impossible";
            } else if (xwin) {
                return "X wins";
            } else if (owin) {
                return "O wins";
            } else if (x + o < 9) {
                return "Game not finished";
            } else {
                return "Draw";
            }
        }
    }

}