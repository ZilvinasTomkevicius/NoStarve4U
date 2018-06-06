package com.example.zilvinastomkevicius.nostarve4u.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zilvinastomkevicius.nostarve4u.Entities.SharingObjects;
import com.example.zilvinastomkevicius.nostarve4u.Entities.User;
import com.example.zilvinastomkevicius.nostarve4u.R;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import maes.tech.intentanim.CustomIntent;

public class SignUpActivity extends AppCompatActivity {

    class SignUpAsyncTask extends AsyncTask<String, Void, Boolean> {

        protected Boolean doInBackground(String... uri) {

            String url = uri[0];

            RestTemplate restTemplate = new RestTemplate();

            try {

                User user = SharingObjects.TempUser;

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                HttpHeaders httpHeaders = new HttpHeaders();

                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<Object> httpEntity = new HttpEntity<Object>(user, httpHeaders);

                restTemplate.exchange(url, HttpMethod.POST, httpEntity, Integer.TYPE);

                return true;
            }

            catch (Exception ex) {

                String message = ex.getMessage();

                return false;
            }
        }

        protected void onPostExecute(Boolean result) {

            if(result) {

                ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadingBarSignUp);
                progressBar.setVisibility(View.INVISIBLE);

                new CountDownTimer(2000, 1000) {

                    public void onTick(long milisec) {

                        Context context = getApplicationContext();
                        CharSequence text = "Registered successfully!";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }

                    public void onFinish() {
                        finish();

                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        CustomIntent.customType(SignUpActivity.this, "fadein-to-fadeout");
                    }
                }.start();
            }

            else {

                ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadingBarSignUp);
                progressBar.setVisibility(View.INVISIBLE);

                TextView textView = (TextView) findViewById(R.id.WrongPassword);
                textView.setVisibility(View.VISIBLE);

                textView.setText("There is already an user registered with this email");
            }
        }
    }

    public void SignUp(View view) {

        EditText editTextEmail = (EditText) findViewById(R.id.EmailsignUp);
        String email = editTextEmail.getText().toString();

        EditText editTextPassword = (EditText) findViewById(R.id.PasswordsignUp);
        String password = editTextPassword.getText().toString();

        EditText editTextPasswordRep = (EditText) findViewById(R.id.PasswordsignUpRep);
        String passwordRep = editTextPasswordRep.getText().toString();

        TextView textView = (TextView) findViewById(R.id.WrongPassword);

        if(email.length() == 0) {

            Context context = getApplicationContext();
            CharSequence text = "Enter email";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else if(password.length() == 0) {

            Context context = getApplicationContext();
            CharSequence text = "Enter password";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else if(passwordRep.length() == 0) {

            Context context = getApplicationContext();
            CharSequence text = "Confirm password";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else if(password.length() < 8) {

            textView.setText("Password is too short");
            textView.setVisibility(View.VISIBLE);
        }

        else if(!password.equals(passwordRep)) {

            textView.setText("Passwords don't match");
            textView.setVisibility(View.VISIBLE);
        }

        else {

            ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadingBarSignUp);
            progressBar.setVisibility(View.VISIBLE);

            textView.setVisibility(View.INVISIBLE);

            SharingObjects.TempUser.Email = email;
            SharingObjects.TempUser.Password = password;

            String uri = "https://otherpurplemouse9.conveyor.cloud/api/user/Add";

            new SignUpActivity.SignUpAsyncTask().execute(uri);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView textView = (TextView) findViewById(R.id.WrongPassword);
        textView.setVisibility(View.INVISIBLE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadingBarSignUp);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
