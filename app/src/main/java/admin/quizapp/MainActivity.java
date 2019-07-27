package admin.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_CHEAT = 0;
    private TextView manotherTextView;
    private TextView mTextView;

    private LinearLayout mFillTheBlankContainer;
    private LinearLayout mTrueFalseContainer;
    private LinearLayout mMultipleChoiceContainer;


    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private Button mHintButton;
    private EditText mEditText;
    private Button mCheckButton;
    private Button a_button;
    private Button b_button;
    private Button c_button;
    private Button d_button;
    private Button cheat_button;

    private Question[] mQuestions;
    private int mIndex;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPreviousButton = (ImageButton) findViewById(R.id.previous_question);
        mHintButton = (Button) findViewById(R.id.hint_button);
        a_button = (Button) findViewById(R.id.a_button);
        b_button = (Button) findViewById(R.id.b_button);
        c_button = (Button) findViewById(R.id.c_button);
        d_button = (Button) findViewById(R.id.d_button);
        cheat_button = (Button) findViewById(R.id.cheat_button);

        mTrueFalseContainer = (LinearLayout) findViewById(R.id.true_false_container);
        mFillTheBlankContainer = (LinearLayout) findViewById(R.id.fill_the_blank_container);
        mMultipleChoiceContainer = (LinearLayout) findViewById(R.id.multiple_choice_container);

        mEditText = (EditText) findViewById(R.id.edit_text);
        mCheckButton = (Button) findViewById(R.id.check_button);

        mTrueButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mPreviousButton.setOnClickListener(this);
        mHintButton.setOnClickListener(this);
        mCheckButton.setOnClickListener(this);
        cheat_button.setOnClickListener(this);

        a_button.setOnClickListener(this);
        b_button.setOnClickListener(this);
        c_button.setOnClickListener(this);
        d_button.setOnClickListener(this);

        manotherTextView = (TextView) findViewById(R.id.text_view);
        mTextView = (TextView) findViewById(R.id.text_view);

        mQuestions = new Question[7];
        mIndex = 0;
        //Initialize each slot in the array.
        mQuestions[0] = new TrueFalseQuestion(R.string.question_1, true, R.string.hint_1);
        mQuestions[1] = new TrueFalseQuestion(R.string.question_2,true, R.string.hint_2);
        mQuestions[2] = new TrueFalseQuestion(R.string.question_3,false, R.string.hint_3);
        mQuestions[3] = new TrueFalseQuestion(R.string.question_4,false, R.string.hint_4);
        mQuestions[4] = new TrueFalseQuestion(R.string.question_5,true, R.string.hint_5);
        String[] q6Answers = getResources().getStringArray(R.array.question_6_answers);
        mQuestions[5] = new FillTheBlank(R.string.question_6, R.string.hint_6, q6Answers);
        mQuestions[6] = new MultipleChoiceQuestion(R.string.question_7, R.string.hint_7, R.array.question_7_answers, 3);
        //Set up view
        setUpQuestion();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData)
    {
        if(resultCode != RESULT_OK)
        {
            return;
        }
        //if (requestCode == REQUEST_CODE_CHEAT && resultData != null)
        //{
        //    mCheated = CheatActivity.didCheat(resultData);
        //}
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.true_button) {
            checkAnswer(true);
        }
        else if (view.getId() == R.id.false_button) {
            checkAnswer(false);
        }
        else if (view.getId() == R.id.next_button) {
            mIndex++;
            if (mIndex >= mQuestions.length) {
                mIndex = 0;
                //mTextView.setText(mQuestions[0].getmTextResId());
            }
            setUpQuestion();
        }
        else if (view.getId() == R.id.check_button)
        {
            checkAnswer(mEditText.getText().toString());
        }
        else if (view.getId() == R.id.previous_question) {
            mIndex--;
            if (mIndex < 0) {
                mIndex = 6; //Fix this later
            }
            mTextView.setText(mQuestions[mIndex].getmTextResId());
        }
        else if (view.getId() == R.id.hint_button) {
            Toast myToast = Toast.makeText(this,(mQuestions[mIndex].getHmTextResId()), Toast.LENGTH_SHORT);
            myToast.show();
            //mTextView.setText(mQuestions[mIndex].getHmTextResId());
        }
        else if (view.getId() == R.id.cheat_button) {
            Intent cheatIntent = CheatActivity.newIntent(this, mQuestions[mIndex]);
            startActivityForResult(cheatIntent, REQUEST_CODE_CHEAT);
        }
    }

    public void setUpQuestion()
    {
        mTextView.setText(mQuestions[mIndex].getmTextResId());

        if(mQuestions[mIndex].isTrueFalse())
        {
            mTrueFalseContainer.setVisibility(View.VISIBLE);
            mFillTheBlankContainer.setVisibility(View.GONE);
            mMultipleChoiceContainer.setVisibility(View.GONE);
        }
        else if (mQuestions[mIndex].isFillBlankQuestion())
        {
            mTrueFalseContainer.setVisibility(View.GONE);
            mFillTheBlankContainer.setVisibility(View.VISIBLE);
            mMultipleChoiceContainer.setVisibility(View.GONE);
        }
        else if (mQuestions[mIndex].isMultipleChoice())
        {
            //Put them btttons
            mTrueFalseContainer.setVisibility(View.GONE);
            mFillTheBlankContainer.setVisibility(View.GONE);
            mMultipleChoiceContainer.setVisibility(View.VISIBLE);
            int optionsResId = ((MultipleChoiceQuestion) mQuestions[mIndex]).getmOptionsResIds();
            String[] options = getResources().getStringArray(optionsResId);
        }
    }
    public boolean checkAnswer (boolean userInput)
    {
        if (mCheated)
        {
            Toast.makeText(this, R.string.shame_the_cheaters, Toast.LENGTH_LONG).show();
            return false;
        }
        else if (mQuestions[mIndex].checkAnswer(userInput))
        {
            manotherTextView.setText("Score:" + score);
            score++;
            Toast myToast = Toast.makeText(this, "You are correct! ", Toast.LENGTH_SHORT);
            myToast.show();
            return true;
        }
        else
        {
            manotherTextView.setText("Score:" + score);
            score--;
            Toast myToast = Toast.makeText(this, "You are incorrect... ", Toast.LENGTH_SHORT);
            myToast.show();
            return false;
        }
    }

    public boolean checkAnswer(String userInput)
    {
        if (mQuestions[mIndex].checkAnswer(userInput))
        {
            mCheckButton.setBackgroundColor(Color.GREEN);
            manotherTextView.setText("Score:" + score);
            score++;
            Toast myToast = Toast.makeText(this, "You are correct! ", Toast.LENGTH_SHORT);
            myToast.show();
            //mCheckButton.setBackgroundColor(Color.YELLOW);
            return true;
        }
        else
        {
            mCheckButton.setBackgroundColor(Color.RED);
            manotherTextView.setText("Score:" + score);
            score--;
            Toast myToast = Toast.makeText(this, "You are incorrect... ", Toast.LENGTH_SHORT);
            myToast.show();
            //mCheckButton.setBackgroundColor(Color.YELLOW);
            return false;
        }
    }

    public boolean checkAnswer (int userInput)
    {
        if (mQuestions[mIndex].checkAnswer(userInput))
        {
            mCheckButton.setBackgroundColor(Color.GREEN);
            manotherTextView.setText("Score:" + score);
            score++;
            Toast myToast = Toast.makeText(this, "You are correct! ", Toast.LENGTH_SHORT);
            myToast.show();
            //mCheckButton.setBackgroundColor(Color.YELLOW);
            return true;
        }
        else
        {
            mCheckButton.setBackgroundColor(Color.RED);
            manotherTextView.setText("Score:" + score);
            score--;
            Toast myToast = Toast.makeText(this, "You are incorrect... ", Toast.LENGTH_SHORT);
            myToast.show();
            //mCheckButton.setBackgroundColor(Color.YELLOW);
            return false;
        }
    }
}
