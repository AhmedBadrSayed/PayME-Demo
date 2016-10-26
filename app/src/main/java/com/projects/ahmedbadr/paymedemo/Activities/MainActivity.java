package com.projects.ahmedbadr.paymedemo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projects.ahmedbadr.paymedemo.R;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoCompleteTextView mUserNameView;
    private EditText mPasswordView;
    private TextView newAccountTV;
    private static final Pattern NAME_PATTERN = Pattern
            .compile("[a-zA-Z]{1,250}");
    private static final Pattern PASWORD_PATTERN = Pattern
            .compile("[a-zA-Z0-9]{1,250}");

    String userName,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserNameView = (AutoCompleteTextView) findViewById(R.id.userNametv);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button LogInButton = (Button) findViewById(R.id.log_in_button);
        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        newAccountTV = (TextView) findViewById(R.id.tv_newAccount);
        newAccountTV.setOnClickListener(this);
    }

    private void attemptLogin() {

        // Reset errors.
        mUserNameView.setError(null);
        mPasswordView.setError(null);

        userName = mUserNameView.getText().toString();
        password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid userName address.
        if (TextUtils.isEmpty(userName)) {
            mUserNameView.setError(getString(R.string.error_field_required));
            focusView = mUserNameView;
            cancel = true;
        } else if (!isUserNameValid(userName)) {
            mUserNameView.setError(getString(R.string.error_invalid_username));
            focusView = mUserNameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startActivity(new Intent(this,Products.class));
        }
    }

    private boolean isUserNameValid(String userName) {
        return NAME_PATTERN.matcher(userName).matches();
    }

    private boolean isPasswordValid(String password) {
        return PASWORD_PATTERN.matcher(password).matches() && password.length() > 4;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_newAccount:
                startActivity(new Intent(this,SignUp.class));
                break;
        }
    }


}
