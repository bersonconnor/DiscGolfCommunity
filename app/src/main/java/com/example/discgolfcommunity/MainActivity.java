package com.example.discgolfcommunity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final int NUM_HOLES = 18;
    private final int NUM_COLUMNS = 4;

    private final TextView parTotalView = findViewById(R.id.par_total);
    private final TextView distanceTotalView = findViewById(R.id.distance_total);
    private final TextView scoreTotalView = findViewById(R.id.score_total);
    private int totalPar = 0;
    private int totalDistance = 0;
    private int totalScore = 0;

    private final ScoreCardEditText[] pars = new ScoreCardEditText[18];
    private final ScoreCardEditText[] distances = new ScoreCardEditText[18];
    private final ScoreCardEditText[] scores = new ScoreCardEditText[18];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get table layout object
        TableLayout tableLayout = findViewById(R.id.scoreCardTable);

        // For the number of holes
        for (int i = 1; i <= NUM_HOLES; i++) {
            // Initialize a new table row
            TableRow tableRow = new TableRow(this);
            // For the number of columns
            for (int j = 1; j <= NUM_COLUMNS; j++) {
                // If it is the first column, then place a text view with the hole number
                if (j == 1) {
                    TextView textView = new TextView(this);
                    textView.setLayoutParams(new TableRow.LayoutParams(j));
                    textView.setText(i + "");
                    textView.setGravity(Gravity.CENTER);
                    tableRow.addView(textView);
                }
                // Otherwise, add a number input
                else {
                    ScoreCardEditText editText = new ScoreCardEditText(this, j);

                    // Initialize editText parameters and add it to the appropriate array
                    editText.setLayoutParams(new TableRow.LayoutParams(j));
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    //appendEditText(editText, i, j);
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            subFromTotal(editText);
                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            addAndUpdateTotal(editText);
                        }
                    });

                    tableRow.addView(editText);
                }
            }
            tableLayout.addView(tableRow);
        }
    }

    /**
     * To be called before the text change to subtract from the total
     *
     * @param editText the entry that was modified
     */
    private void subFromTotal(ScoreCardEditText editText) {
        if (editText.getText() != null) {
            int value = Integer.parseInt(editText.getText().toString());
            switch (editText.getColumn()) {
                case 2:
                    totalPar -= value;
                case 3:
                    totalDistance -= value;
                case 4:
                    totalScore -= value;
            }
        }
    }

    /**
     * To be called after the text change to add to the total and update the field displaying the total
     *
     * @param editText the entry that was modified
     */
    private void addAndUpdateTotal(ScoreCardEditText editText) {
        if (editText.getText() != null) {
            int value = Integer.parseInt(editText.getText().toString());
            switch (editText.getColumn()) {
                case 2:
                    totalPar += value;
                    parTotalView.setText(totalPar);
                case 3:
                    totalDistance += value;
                    distanceTotalView.setText(totalDistance);
                case 4:
                    totalScore += value;
                    scoreTotalView.setText(totalScore);
            }
        }
    }

    /**
     * Appends the edit text to the associated column at the given index
     *
     * @param editText the EditText object to be appended
     * @param index    the index of the array to append it to
     * @param column   the number of the column
     */
    private void appendEditText(ScoreCardEditText editText, int index, int column) {
        // Depending on the column, get the correct array to append to
        switch (column) {
            // If the column is par score
            case 2:
                pars[index] = editText;
            case 3:
                distances[index] = editText;
            case 4:
                scores[index] = editText;
        }
    }
}