package com.example.discgolfcommunity;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;

/**
 * Contains various random static helper methods used throughout the application
 */
public class Utils {

    private static final String SIGNED_INTEGER_REGEX = "-?[1-9][0-9]*|0";

    /**
     * Extracts an integer from the given Edit Text object if it contains one
     *
     * @param editText an Edit Text object
     * @return the extracted integer
     */
    public static int getIntFromEditText(EditText editText) {
        Editable ett = editText.getText();
        if (ett == null || !isNumeric(ett.toString())) {
            return 0;
        } else {
            return Integer.parseInt(ett.toString());
        }
    }

    /**
     * Returns true if the string is numeric or false otherwise
     *
     * @param str a String object
     * @return true if the string is numeric or false otherwise
     */
    public static boolean isNumeric(String str) {
        return str != null && str.matches(SIGNED_INTEGER_REGEX);
    }

    /**
     * Sets the given views padding to padding on all sides
     *
     * @param view    the given view
     * @param padding the padding integer for all sides
     */
    public static void setAllPadding(View view, int padding) {
        view.setPadding(padding, padding, padding, padding);
    }

}
