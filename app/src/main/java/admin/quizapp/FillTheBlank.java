package admin.quizapp;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FillTheBlank extends Question {
    private String[] mFillAnswers;

    public FillTheBlank(int mTextResId, int hmTextResId, String[] fillAnswers) {
        super(mTextResId, hmTextResId);
        mFillAnswers = fillAnswers;
    }

    @Override
    public boolean checkAnswer(String userAnswer)
    {
        for(String ans : mFillAnswers) {
            if(ans.equalsIgnoreCase(userAnswer))
            {
                //turn button green
                return true;
            }
        }
        //turn button red
        //mCheckButton.setBackgroundColor(Color.RED);
        return false;
    }

    @Override
    public boolean isFillBlankQuestion() {
        return true;
    }

    @Override
    public String getAnswerText(Context ctx) {
        return Arrays.toString(mFillAnswers);
    }
}
