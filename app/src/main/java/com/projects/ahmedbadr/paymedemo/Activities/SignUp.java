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
import com.projects.ahmedbadr.paymedemo.Activities.Products;
import com.projects.ahmedbadr.paymedemo.R;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    private AutoCompleteTextView mFirstNameView, mLastNameView, mEmailView, mMobileView;
    private EditText mPasswordView;
    private Button btnSingnUp;
    String firstName ,lastName, Email, Mobile, password;
    private static final Pattern NAME_PATTERN = Pattern
            .compile("[a-zA-Z]{1,250}");
    private static final Pattern PASWORD_PATTERN = Pattern
            .compile("[a-zA-Z0-9]{1,250}");
    private static final Pattern PHONE_PATTERN = Pattern
            .compile("[0-9]{1,250}");
    private static final Pattern EMAIL_PATTERN = Pattern
            .compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setViews();
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

        btnSingnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    public void setViews(){
        mFirstNameView = (AutoCompleteTextView) findViewById(R.id.fName);
        mLastNameView = (AutoCompleteTextView) findViewById(R.id.lName);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mMobileView = (AutoCompleteTextView) findViewById(R.id.mobile);
        mPasswordView = (EditText) findViewById(R.id.etPassword);
        btnSingnUp = (Button) findViewById(R.id.btnSignUp);
    }

    public void PerformSignUp(){

        //progressDialog = ProgressDialog.show(getActivity(),"", "Loading. Please wait...", true);
        ServiceBuilder builder = new ServiceBuilder();
        ServiceInterfaces.SignUp signUp = builder.NewUser();
        Call<ServiceInterfaces> apiModelCall = signUp.SignUp("Normal",Email,password,firstName,lastName,Mobile,"qw","Gt557","Android");
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
                    Toast.makeText(getApplication(), "Connection Failed", Toast.LENGTH_SHORT).show();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void attemptLogin() {

        // Reset errors.
        mFirstNameView.setError(null);
        mLastNameView.setError(null);
        mEmailView.setError(null);
        mMobileView.setError(null);
        mPasswordView.setError(null);

        firstName = mFirstNameView.getText().toString();
        lastName = mLastNameView.getText().toString();
        Email = mEmailView.getText().toString();
        Mobile = mMobileView.getText().toString();
        password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid firstName
        if (TextUtils.isEmpty(firstName)) {
            mFirstNameView.setError(getString(R.string.error_field_required));
            focusView = mFirstNameView;
            cancel = true;
        }else if (!isUserNameValid(firstName)) {
            mFirstNameView.setError(getString(R.string.error_invalid_username));
            focusView = mFirstNameView;
            cancel = true;
        }

        // Check for a valid lastName
        if (TextUtils.isEmpty(lastName)) {
            mLastNameView.setError(getString(R.string.error_field_required));
            focusView = mLastNameView;
            cancel = true;
        }else if (!isUserNameValid(lastName)) {
            mLastNameView.setError(getString(R.string.error_invalid_username));
            focusView = mLastNameView;
            cancel = true;
        }

        // Check for a valid email
        if (TextUtils.isEmpty(Email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }else if (!isEmailValid(Email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        // Check for a valid mobile.
        if (TextUtils.isEmpty(Mobile)) {
            mMobileView.setError(getString(R.string.error_field_required));
            focusView = mMobileView;
            cancel = true;
        }else if (!isMobileValid(Mobile)) {
            mMobileView.setError(getString(R.string.invalid_mobile));
            focusView = mMobileView;
            cancel = true;
        }

        // Check for a valid password.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            PerformSignUp();
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

    private boolean isMobileValid(String mobile) {
        return PHONE_PATTERN.matcher(mobile).matches();
    }
}
