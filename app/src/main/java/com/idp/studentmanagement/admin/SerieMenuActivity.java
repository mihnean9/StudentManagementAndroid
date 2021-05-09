package com.idp.studentmanagement.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.idp.studentmanagement.R;
import com.idp.studentmanagement.api.JsonRequests;
import com.idp.studentmanagement.api.RetrofitClient;
import com.idp.studentmanagement.general.Constants;
import com.idp.studentmanagement.general.Util;
import com.idp.studentmanagement.objects.Seria;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SerieMenuActivity extends AppCompatActivity {

    private EditText txtSerieName;
    private JsonRequests jsonRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_menu);

        txtSerieName = findViewById(R.id.txt_name_serie_add);

        RetrofitClient retrofit = RetrofitClient.getInstance();
        jsonRequests = retrofit.getJson();
    }

    public void viewSerii(View view) {
        Constants.setViewIntent("SERII");
        Intent viewIntent = new Intent(this, ViewActivity.class);
        startActivity(viewIntent);
    }

    public void addSerie(View view) {
        String name = txtSerieName.getText().toString();
        if (name.length() == 0) {
            Toast.makeText(SerieMenuActivity.this, "Introduceti un nume de facultate!", Toast.LENGTH_SHORT).show();
            return;
        }
        final Seria seria = new Seria(name);

        Call<Seria> call = jsonRequests.postSerie(Constants.getToken(), seria);
        call.enqueue(new Callback<Seria>() {
            @Override
            public void onResponse(Call<Seria> call, Response<Seria> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SerieMenuActivity.this, "Nume deja existent!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body() == null) {
                    Toast.makeText(SerieMenuActivity.this, "Internal Error!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Util.getSerii();
                String content = "Serie adaugata!\n";
                content += "ID: " + response.body().getId() + "\n";
                content += "Name: " + response.body().getName();
                Toast.makeText(SerieMenuActivity.this, content, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Seria> call, Throwable t) {

            }
        });
    }
}
