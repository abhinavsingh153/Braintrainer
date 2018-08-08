package com.abhinavsingh153.project.braintrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;

    TextView countDownTimer;

    TextView resultTextView;

    TextView scoreTextView;

    Boolean buttontapped = true;

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    Button playAgainButton;

    ArrayList<Integer> answers = new ArrayList<Integer>();

    int score = 0;

    int locationCorrectAnswer;

    int numberOfQuestions = 0;



    public void start (View view)
    {

        RelativeLayout relativeLayout2 = findViewById(R.id.relativeLayout2);

        goButton.setVisibility(View.INVISIBLE);

        relativeLayout2.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));

    }

    public void chooseAnswer (View view)
    {



        if (view.getTag().toString().equals(Integer.toString(locationCorrectAnswer)))
        {
            resultTextView.setText("Correct!");

            score++;

        }

        else{

            resultTextView.setText("Incorrect");




        }

        numberOfQuestions++;


        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        generateNewQuestion();




        Log.i("Tag" , (String) view.getTag());
    }

    public void generateNewQuestion ()
    {
        Random random = new Random();

        //genertaing random numbers for addition.
        int a = random.nextInt(21);
        int b = random.nextInt(21);

        answers.clear();

        TextView sumTextView = findViewById(R.id.sumTextView);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationCorrectAnswer = random.nextInt(4);

        int incorrectAnswer;

        for (int i=0; i<4; i++)
        {
            if (i == locationCorrectAnswer)
            {
                answers.add(a+b);
            }

            else
            {
                incorrectAnswer = random.nextInt(41);

                // In case if the random generated is equal to the correct answer then this while loop will generate another random number

                while (incorrectAnswer == a+b)
                {
                    incorrectAnswer = random.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }



        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    public void playAgain (View view)
    {


        playAgainButton.setVisibility(View.INVISIBLE);

        score = 0;

        numberOfQuestions = 0;

        countDownTimer.setText("30 s");

        generateNewQuestion();

        resultTextView.setText("");

        new CountDownTimer( 30100, 1000)
        {


            @Override
            public void onTick(long millisUntilFinished) {

                countDownTimer.setText(String.valueOf(millisUntilFinished/1000)+ "s");

                Log.i("Seconds left", String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);

                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.clapping);

                mediaPlayer.start();

                countDownTimer.setText("0 s");
                resultTextView.setText("Your score is "+ Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            }
        }.start();


        generateNewQuestion();


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        scoreTextView = findViewById(R.id.scoreTextView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        playAgainButton = findViewById(R.id.playAgainButton);

       // generateNewQuestion();


        countDownTimer = findViewById(R.id.countDownTimer);

        goButton= findViewById(R.id.goButton);

      // playAgain(findViewById(R.id.playAgainButton));
    }
}
