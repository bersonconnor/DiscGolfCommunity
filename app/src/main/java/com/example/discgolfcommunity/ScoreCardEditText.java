package com.example.discgolfcommunity;

import android.content.Context;
import android.util.AttributeSet;

public class ScoreCardEditText extends androidx.appcompat.widget.AppCompatEditText {
    private int column;

    public ScoreCardEditText(Context context, int column) {
        super(context);
        this.column = column;
    }

    public ScoreCardEditText(Context context, AttributeSet attrs, int column) {
        super(context, attrs);
        this.column = column;
    }

    public ScoreCardEditText(Context context, AttributeSet attrs, int defStyleAttr, int column) {
        super(context, attrs, defStyleAttr);
        this.column = column;
    }

    public int getColumn() {
        return column;
    }
}
