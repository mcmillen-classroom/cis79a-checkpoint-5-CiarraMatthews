package admin.quizapp;

import android.content.Context;

public class MultipleChoiceQuestion extends Question {

    private int mOptionsResIds;
    private int mAnswer;

    public int getmOptionsResIds() {
        return mOptionsResIds;
    }

    public MultipleChoiceQuestion(int mTextResId, int hmTextResId, int mOptionsResIds, int ans) {
        super(mTextResId, hmTextResId);
        mOptionsResIds = mOptionsResIds;
        mAnswer = ans;
    }

    @Override
    public boolean checkAnswer(int ans) {
        return mAnswer == ans;
    }

    @Override
    public boolean isMultipleChoice() {
        return super.isMultipleChoice();
    }

    @Override
    public String getAnswerText(Context ctx) {
        String[] options = ctx.getResources().getStringArray(mOptionsResIds);
        return options[mAnswer];
    }
}
