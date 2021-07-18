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

    private TextView parTotalView;
    private TextView distanceTotalView;
    private TextView scoreTotalView;

    private ScoreCardEditText[] pars = new ScoreCardEditText[18];
    private ScoreCardEditText[] distances = new ScoreCardEditText[18];
    private ScoreCardEditText[] scores = new ScoreCardEditText[18];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Create the app and set the view to the main activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Grab all the total Text Views
        parTotalView = findViewById(R.id.par_total);
        distanceTotalView = findViewById(R.id.distance_total);
        scoreTotalView = findViewById(R.id.score_total);

        // Get table layout object
        TableLayout tableLayout = findViewById(R.id.scoreCardTable);

        // For the number of holes
        for (int i = 0; i < NUM_HOLES; i++) {
            // Initialize a new table row
            TableRow tableRow = new TableRow(this);
//            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
//            layoutParams.width = TableRow.LayoutParams.MATCH_PARENT;
//            tableRow.setLayoutParams(layoutParams);
            // For the number of columns
            for (int j = 1; j <= NUM_COLUMNS; j++) {
                // If it is the first column, then place a text view with the hole number
                if (j == 1) {
                    TextView textView = new TextView(this);
                    textView.setLayoutParams(new TableRow.LayoutParams(j));
                    textView.setText((i + 1) + "");
                    textView.setGravity(Gravity.CENTER);
//                    Utils.setAllPadding(textView, 10);
                    tableRow.addView(textView);
                }
                // Otherwise, add a number input
                else {
                    ScoreCardEditText editText = new ScoreCardEditText(this, j);

                    // Initialize editText parameters and add it to the appropriate array
                    editText.setLayoutParams(new TableRow.LayoutParams(j));
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
//                    Utils.setAllPadding(editText, 50);
                    appendEditText(editText, i);
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            int[] totals = getTotals();
                            parTotalView.setText(totals[0] + "");
                            distanceTotalView.setText(totals[1] + "");
                            scoreTotalView.setText(totals[2] + "");
                        }
                    });

                    tableRow.addView(editText);
                }
            }
            tableLayout.addView(tableRow);
        }
    }

    /**
     * Appends the edit text to the associated column at the given index
     *
     * @param editText the EditText object to be appended
     * @param index    the index of the array to append it to
     */
    private void appendEditText(ScoreCardEditText editText, int index) {
        // Depending on the column, get the correct array to append to
        switch (editText.getColumn()) {
            // If the column is par score
            case 2:
                pars[index] = editText;
            case 3:
                distances[index] = editText;
            case 4:
                scores[index] = editText;
        }
    }

    /**
     * Sums the values at each entry of the score card and returns an integer array
     * of the form [total par, total distance, total score].
     *
     * @return an integer array of the form [total par, total distance, total score].
     */
    private int[] getTotals() {
        int[] totals = new int[3];
        for (int i = 0; i < NUM_HOLES; i++) {
            totals[0] += Utils.getIntFromEditText(pars[i]);
            totals[1] += Utils.getIntFromEditText(distances[i]);
            totals[2] += Utils.getIntFromEditText(scores[i]);
        }
        return totals;
    }


}