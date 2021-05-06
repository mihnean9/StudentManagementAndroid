package com.idp.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.idp.studentmanagement.api.JsonRequests;
import com.idp.studentmanagement.api.RetrofitClient;
import com.idp.studentmanagement.general.Util;
import com.idp.studentmanagement.objects.Faculty;
import com.idp.studentmanagement.objects.Grupa;
import com.idp.studentmanagement.general.Constants;
import com.idp.studentmanagement.objects.Login;
import com.idp.studentmanagement.objects.Student;
import com.idp.studentmanagement.objects.Token;
import com.idp.studentmanagement.objects.User;
import com.idp.studentmanagement.objects.UserType;
import com.idp.studentmanagement.objects.UserTypes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    private JsonRequests jsonRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Constants.getInstance();
        username = findViewById(R.id.txt_username);
        password = findViewById(R.id.txt_password);

        username.setText(Constants.getUser().getLogin());
        password.setText(Constants.getUser().getPassword());

        RetrofitClient retrofit = RetrofitClient.getInstance();
        jsonRequests = retrofit.getJson();
    }

    @SuppressLint("SetTextI18n")
    public void login(View view) {
        final String usr = username.getText().toString();
        final String pass = password.getText().toString();
        if (usr.length() == 0 || pass.length() == 0) {
            Toast.makeText(MainActivity.this, "Please enter both a username and a password!", Toast.LENGTH_SHORT).show();
            return;
        }
        /*
        Login login = new Login(usr, pass);

        Call<Token>  call = jsonRequests.postLogin(login);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (!response.isSuccessful()) {
                    String err = "Incorrect login info!";
                    Toast.makeText(MainActivity.this, err, Toast.LENGTH_SHORT).show();
                    return;
                }

                Constants.setToken(response.body().getJwt());

                Call<User> userCall = jsonRequests.getUser(usr, Constants.getToken());
                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User currentUser = response.body();

                        if (currentUser == null) {
                            Toast.makeText(MainActivity.this, "Internal error", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Constants.setUser(currentUser);

                        if (!currentUser.getUserType().getType().equalsIgnoreCase("STUDENT")) {
                            Util.getFacutlies();
                            Util.getGrupe();
                            Util.getSerii();
                            Util.getMajors();
                        }

                        Intent loginIntent;
                        loginIntent = new Intent(MainActivity.this, Constants.getLoginClass());
                        startActivity(loginIntent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                    }
                });

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
            }
        });
        */
        Constants.setUser(new User(
                "a",
                "b",
                new UserType(UserTypes.STUDENT.name()),
                "boss",
                "mondial",
                "",
                1
        ));
        Constants.setToken("");
        Intent loginIntent;
        loginIntent = new Intent(MainActivity.this, Constants.getLoginClass());
        startActivity(loginIntent);
    }
}
