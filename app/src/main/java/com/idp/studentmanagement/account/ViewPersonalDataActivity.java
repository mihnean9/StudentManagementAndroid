package com.idp.studentmanagement.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.idp.studentmanagement.R;
import com.idp.studentmanagement.api.JsonRequests;
import com.idp.studentmanagement.api.RetrofitClient;
import com.idp.studentmanagement.general.Constants;
import com.idp.studentmanagement.objects.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPersonalDataActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonRequests jsonRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_personal_data);

        ConstraintLayout layout = findViewById(R.id.layout_view_personal_data);
        layout.setBackgroundResource(Constants.getBackground());

        textViewResult = findViewById(R.id.txtv_personal_data);
        textViewResult.setMovementMethod(new ScrollingMovementMethod());

        RetrofitClient retrofit = RetrofitClient.getInstance();
        jsonRequests = retrofit.getJson();
        textViewResult.setText(Constants.getUser().displayUser());
    }


    public void getUserData(View view) {
        String user_id = Constants.getUser().getLogin();
        Call<User> userCall = jsonRequests.getUser(user_id, Constants.getToken());
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User currentUser = response.body();
                if (currentUser != null) {
                    textViewResult.setText(currentUser.displayUser());
                    Constants.setUser(currentUser);
                }
                else {
                    Toast.makeText(ViewPersonalDataActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
