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
import com.idp.studentmanagement.objects.Faculty;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyMenuActivity extends AppCompatActivity {

    private EditText txtFacultyName;
    private JsonRequests jsonRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_menu);

       // txtFacultyID = findViewById(R.id.txt_id_faculty_add);
        txtFacultyName = findViewById(R.id.txt_name_faculty_add);

        RetrofitClient retrofit = RetrofitClient.getInstance();
        jsonRequests = retrofit.getJson();
    }

    public void viewFaculties(View view) {
        Constants.setViewIntent("FACULTIES");
        Intent viewIntent = new Intent(this, ViewActivity.class);
        startActivity(viewIntent);
    }

    public void addFaculty(View view) {
        String name = txtFacultyName.getText().toString();
        if (name.length() == 0) {
            Toast.makeText(FacultyMenuActivity.this, "Introduceti un nume de facultate!", Toast.LENGTH_SHORT).show();
            return;
        }
        final Faculty faculty = new Faculty(name);

        Call<Faculty> call = jsonRequests.postFaculty(Constants.getToken(), faculty);
        call.enqueue(new Callback<Faculty>() {
            @Override
            public void onResponse(Call<Faculty> call, Response<Faculty> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(FacultyMenuActivity.this, "Nume deja existent!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body() == null) {
                    Toast.makeText(FacultyMenuActivity.this, "Internal Error!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Util.getFacutlies();
                String content = "Facultate adaugata!\n";
                content += "ID: " + response.body().getId() + "\n";
                content += "Name: " + response.body().getName();
                Toast.makeText(FacultyMenuActivity.this, content, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Faculty> call, Throwable t) {

            }
        });
    }
}
