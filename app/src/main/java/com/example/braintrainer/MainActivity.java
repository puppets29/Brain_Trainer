package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Variable
    private Button playBtn, againBtn, btn1, btn2, btn3, btn4;
    private TextView answText, scoreText, quesText, timerText;
    private ConstraintLayout main;
    private int locAnswers;
    private int score = 0;
    private int numQuest = 0;
    private final ArrayList<Integer> listAnswers;
    {
        listAnswers = new ArrayList<>();
    }

    public void GenerateQuestion() {
        Random rand = new Random();

        //Membuat soal
        int firstNum = rand.nextInt(51);
        int seconNum = rand.nextInt(51);

        quesText.setText(firstNum + " + " + seconNum);

        //Membuat 4 random angka dan jawabannya
        locAnswers = rand.nextInt(4);

        listAnswers.clear();

        for (int i=0; i<4; i++) {

            if (i == locAnswers) {

                listAnswers.add(firstNum + seconNum);

            } else {

                int notAnswers = rand.nextInt(101);

                while (notAnswers == firstNum + seconNum) {

                    notAnswers = rand.nextInt(101);

                }

                listAnswers.add(notAnswers);
            }
        }

        //Mengganti text jawaban
        btn1.setText(Integer.toString(listAnswers.get(0)));
        btn2.setText(Integer.toString(listAnswers.get(1)));
        btn3.setText(Integer.toString(listAnswers.get(2)));
        btn4.setText(Integer.toString(listAnswers.get(3)));
    }

    //Fungsi klik
    public void ClickAnswer(View view) {
        try {
            if (view.getTag().toString().equals(Integer.toString(locAnswers))) {

                score++;
                answText.setText("Correct!");

            } else {

                answText.setText("Wrong!");

            }

            numQuest++;
            scoreText.setText(score + "/" + numQuest);
            GenerateQuestion();
        } catch (Exception e) {
            Log.e("error", String.valueOf(e));
        }
    }

    //Untuk button start
    public void StartGame(View view) {
        playBtn.setVisibility(View.INVISIBLE);
        main.setVisibility(RelativeLayout.VISIBLE);

        Restart(findViewById(R.id.btnAgain));
    }

    public void Restart(View view) {
        score = 0;
        numQuest = 0;

        timerText.setText("30s");
        scoreText.setText("0/0");
        answText.setText("");
        againBtn.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerText.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                againBtn.setVisibility(View.VISIBLE);
                timerText.setText("0s");
                answText.setText("Your Score " + score + "/" + numQuest);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisialisasi variable
        playBtn = (Button) findViewById(R.id.btnPlay);
        againBtn = (Button) findViewById(R.id.btnAgain);
        main = (ConstraintLayout) findViewById(R.id.main);
        answText = (TextView) findViewById(R.id.answText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        quesText = (TextView) findViewById(R.id.quesText);
        timerText = (TextView) findViewById(R.id.timerText);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        GenerateQuestion();
    }
}