package admin.quizapp;

import android.content.Context;

import admin.quizapp.R;

public class Question
{
    private int mTextResId;
    private boolean mAnswer;
    private String wmAnswer;

    private String mHint;
    private int hmTextResId;

    public Question(int mTextResId, int hmTextResId) {
        this.mTextResId = mTextResId;
        this.mAnswer = mAnswer;
        this.hmTextResId = hmTextResId;
    }
    public Question(int mTextResId, String wmAnswer) {
        this.mTextResId = mTextResId;
        this.wmAnswer = wmAnswer;
    }

    public int getmTextResId() {
        return mTextResId;
    }

    public void setmTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public String getText(Context ctx)
    {
        return ctx.getString(mTextResId);
    }

    public String getAnswerText(Context ctx) {
        return "";
    }

    public int getHmTextResId() {
        return hmTextResId;
    }

    public void setHmTextResId(int mTextResId) {
        hmTextResId = mTextResId;
    }

    public String getmHint() {
        return mHint;
    }
    public void setmHint(String mHint) {
        this.mHint = mHint;
    }

    public boolean checkAnswer(boolean boolResponse)
    {
        return false;
    }
    public boolean checkAnswer(int userAnswer)
    {
        return false;
    }
    // stub method
    // only applies to fill the blank question
    public boolean checkAnswer(String userAnswer)
    {
        return false;
    }

    public boolean isTrueFalse() {return false;}
    public boolean isMultipleChoice() {return false;}
    public boolean isFillBlankQuestion(){return false;}
}
