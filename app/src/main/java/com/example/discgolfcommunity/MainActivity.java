package com.example.discgolfcommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button scoreCardButton;
    private Button courseFinder;
    private Button statisticsButton;
    private Button helpButton;
    private TextView welcomeTextView;

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // WELCOME BAR
        userName = getUserName();
        welcomeTextView = (TextView) findViewById(R.id.welcome_text);
        welcomeTextView.setText("Welcome " + userName + "!");

        // SCORE CARD BUTTON
        scoreCardButton = (Button) findViewById(R.id.score_card_button);
        scoreCardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScorecardActivity.class);
                startActivity(intent);
            }
        });

        // COURSE FINDER BUTTON
        courseFinder = (Button) findViewById(R.id.course_finder_button);

        // STATISTICS BUTTON
        statisticsButton = (Button) findViewById(R.id.stat_button);

        // HELP BUTTON
        helpButton = (Button) findViewById(R.id.help_button);
    }

    // TODO - get the real username once login features are implemented

    /**
     * Returns the user's username
     *
     * @return the user's username
     */
    private String getUserName() {
        return "User";
    }

}