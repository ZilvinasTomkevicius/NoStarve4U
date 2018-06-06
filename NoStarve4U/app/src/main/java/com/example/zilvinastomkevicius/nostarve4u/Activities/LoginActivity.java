package com.example.zilvinastomkevicius.nostarve4u.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zilvinastomkevicius.nostarve4u.Entities.Product;
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

import java.net.HttpURLConnection;
import java.net.URL;

import maes.tech.intentanim.CustomIntent;

public class LoginActivity extends AppCompatActivity {

    /*
        ASYNC TASK FOR CHECKING CONNECTION TO THE SERVER
     */
    class CheckConnectionTask extends AsyncTask<String, Void, Integer> {

        protected Integer doInBackground(String... uri) {

            String url = uri[0];

            try {

                URL myUrl = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection)myUrl.openConnection();

                int code = urlConnection.getResponseCode();

                return code;
            }
            catch (Exception e) {

                return 0;
            }
        }

        protected void onPostExecute(Integer result) {

            if(result == 200) {

            }

            else {

             //   ProgressBar progressBar = findViewById(R.id.loadingBar);
              //  progressBar.setVisibility(View.INVISIBLE);

                Context context = getApplicationContext();
                CharSequence text = "No connection to the server.";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    class UserInfoTask extends AsyncTask<String, Void, ResponseEntity<User>> {

        protected ResponseEntity<User> doInBackground(String... uri) {

            final String url = uri[0];

            RestTemplate restTemplate = new RestTemplate();
            try {

                User user = SharingObjects.TempUser;

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                HttpHeaders httpHeaders = new HttpHeaders();

                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<Object> httpEntity = new HttpEntity<Object>(user, httpHeaders);

                ResponseEntity<User> userInfo = restTemplate.exchange(url, HttpMethod.POST, httpEntity, User.class);

                return userInfo;

            } catch (Exception ex) {

                String message = ex.getMessage();

                return null;
            }
        }

        protected void onPostExecute(ResponseEntity<User> userInfo) {

            User user = userInfo.getBody();

            if(user.Email == null) {

                ProgressBar progressBarLogin = (ProgressBar) findViewById(R.id.loadingBarLogin);
                progressBarLogin.setVisibility(View.INVISIBLE);

                TextView textViewWrongData = (TextView) findViewById(R.id.WrongData);
                textViewWrongData.setVisibility(View.VISIBLE);
            }

            else {

                TextView textViewWrongData = (TextView) findViewById(R.id.WrongData);
                textViewWrongData.setVisibility(View.INVISIBLE);

                ProgressBar progressBarLogin = (ProgressBar) findViewById(R.id.loadingBarLogin);
                progressBarLogin.setVisibility(View.INVISIBLE);

                startActivity(new Intent(LoginActivity.this, ProductListActivity.class));
                CustomIntent.customType(LoginActivity.this, "fadein-to-fadeout");

            SharingObjects.isLoggedOn = true;
            }
        }
    }

    public void LogOn(View view) {

        EditText editTextEmail = (EditText) findViewById(R.id.EmailLogin);
        String EmailLogin = editTextEmail.getText().toString();

        EditText editTextPassword = (EditText) findViewById(R.id.PasswordLogin);
        String PasswordLogin = editTextPassword.getText().toString();

        if(EmailLogin.length() == 0) {

            Context context = getApplicationContext();
            CharSequence text = "Enter Email";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else if(PasswordLogin.length() == 0) {

            Context context = getApplicationContext();
            CharSequence text = "Enter Password";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else {

            SharingObjects.TempUser.Email = EmailLogin;
            SharingObjects.TempUser.Password = PasswordLogin;

            ProgressBar progressBarLogin = (ProgressBar) findViewById(R.id.loadingBarLogin);
            progressBarLogin.setVisibility(View.VISIBLE);

            final String userUri = "https://otherpurplemouse9.conveyor.cloud/api/user/LoggingOn";

            new LoginActivity.UserInfoTask().execute(userUri);
        }

    }

    public void Continue(View view) {

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        CustomIntent.customType(LoginActivity.this, "fadein-to-fadeout");
        finish();
    }

    public  void IntentToSignUp(View view) {

        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        CustomIntent.customType(LoginActivity.this, "fadein-to-fadeout");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textViewWrongData = (TextView) findViewById(R.id.WrongData);
        textViewWrongData.setVisibility(View.INVISIBLE);

        ProgressBar progressBarLogin = (ProgressBar) findViewById(R.id.loadingBarLogin);
        progressBarLogin.setVisibility(View.INVISIBLE);

        final String userUri = "https://otherpurplemouse9.conveyor.cloud/api/user/LoggingOn";

        new LoginActivity.CheckConnectionTask().execute(userUri);
    }
}
