package admin.quizapp;

import android.content.Context;

import admin.quizapp.Question;

public class TrueFalseQuestion extends Question {

    private boolean mAnswer;

    public TrueFalseQuestion(int mTextResId, boolean ans, int hmTextResId) {
        super(mTextResId, hmTextResId);
        mAnswer = ans;

    }

    @Override
    public boolean checkAnswer(boolean ans)
    {
        return mAnswer == ans;
    }

    @Override
    public boolean isTrueFalse() {
        return true;
    }

    @Override
    public String getAnswerText(Context ctx) {
        return "" + mAnswer;
    }
}
