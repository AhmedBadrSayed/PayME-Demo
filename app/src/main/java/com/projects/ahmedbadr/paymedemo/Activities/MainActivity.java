package com.projects.ahmedbadr.paymedemo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.ahmedbadr.paymedemo.API.ServiceBuilder;
import com.projects.ahmedbadr.paymedemo.API.ServiceInterfaces;
import com.projects.ahmedbadr.paymedemo.R;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private TextView newAccountTV;
    private static final Pattern NAME_PATTERN = Pattern
            .compile("[a-zA-Z]{1,250}");
    private static final Pattern PASWORD_PATTERN = Pattern
            .compile("[a-zA-Z0-9]{1,250}");
    private static final Pattern EMAIL_PATTERN = Pattern
            .compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.emailtv);

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

    public void PerformLogin(){

        //progressDialog = ProgressDialog.show(getActivity(),"", "Loading. Please wait...", true);
        ServiceBuilder builder = new ServiceBuilder();
        ServiceInterfaces.LogIn logIn = builder.User();
        Call<ServiceInterfaces> apiModelCall = logIn.login(email,password,"Gt557","qw","Android");
        apiModelCall.enqueue(new Callback<ServiceInterfaces>() {
            @Override
            public void onResponse(Call<ServiceInterfaces> call, Response<ServiceInterfaces> response) {
                startActivity(new Intent(getApplication(),Products.class));
                Log.v("Success",response.message());
            }

            @Override
            public void onFailure(Call<ServiceInterfaces> call, Throwable t) {
                Log.v("Retrieve Error", t.toString());
                try {
                    Toast.makeText(getApplication(), "SignUp First", Toast.LENGTH_SHORT).show();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        email = mEmailView.getText().toString();
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

        // Check for a valid email
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            PerformLogin();
        }
    }

    private boolean isUserNameValid(String userName) {
        return NAME_PATTERN.matcher(userName).matches();
    }

    private boolean isPasswordValid(String password) {
        return PASWORD_PATTERN.matcher(password).matches() && password.length() > 4;
    }

    private boolean isEmailValid(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
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
