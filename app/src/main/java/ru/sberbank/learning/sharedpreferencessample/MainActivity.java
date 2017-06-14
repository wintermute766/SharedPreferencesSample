package ru.sberbank.learning.sharedpreferencessample;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private static final String PLAIN_TEXT = "plain_text";
    private static final String NUMBER_TEXT = "number_text";
    private static final String FLOAT_TEXT = "float_text";
    private static final String CHECKBOX_FLAG = "checkbox_flag";

    private EditText mPlaintEditText;
    private EditText mNumberEditText;
    private EditText mFloatEditText;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        setContentView(R.layout.activity_main);
        mPlaintEditText = (EditText) findViewById(R.id.plain_edit_text);
        mNumberEditText = (EditText) findViewById(R.id.number_edit_text);
        mFloatEditText = (EditText) findViewById(R.id.float_edit_text);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox);

        readValuesFromPrefs();
    }

    private void readValuesFromPrefs() {
        mPlaintEditText.setText(mPreferences.getString(PLAIN_TEXT, null));
        mNumberEditText.setText(
                String.valueOf(mPreferences.getInt(NUMBER_TEXT, 0)));
        mFloatEditText.setText(
                String.valueOf(mPreferences.getFloat(FLOAT_TEXT, 0)));
        mCheckBox.setChecked(mPreferences.getBoolean(CHECKBOX_FLAG, false));
    }

    private void writeValuesToPrefs() {
        String plainText = mPlaintEditText.getText().toString();

        int number = 0;
        String numberText = mNumberEditText.getText().toString();
        if (!TextUtils.isEmpty(numberText)) {
            number = Integer.parseInt(numberText);
        }

        float fnumber = 0f;
        String floatText = mFloatEditText.getText().toString();
        if (!TextUtils.isEmpty(floatText)) {
            fnumber = Float.parseFloat(floatText);
        }

        boolean flag = mCheckBox.isChecked();

        mPreferences.edit()
                .putString(PLAIN_TEXT, plainText)
                .putInt(NUMBER_TEXT, number)
                .putFloat(FLOAT_TEXT, fnumber)
                .putBoolean(CHECKBOX_FLAG, flag)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        writeValuesToPrefs();
    }

}
