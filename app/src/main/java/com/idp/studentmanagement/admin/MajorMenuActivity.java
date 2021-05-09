package com.idp.studentmanagement.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.idp.studentmanagement.R;
import com.idp.studentmanagement.api.JsonRequests;
import com.idp.studentmanagement.api.RetrofitClient;
import com.idp.studentmanagement.general.Constants;
import com.idp.studentmanagement.general.Util;
import com.idp.studentmanagement.objects.Faculty;
import com.idp.studentmanagement.objects.Major;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MajorMenuActivity extends AppCompatActivity {

    private EditText txtMajorName;
    private JsonRequests jsonRequests;
    private Spinner spnFaculties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_menu);

        txtMajorName = findViewById(R.id.txt_name_faculty_add);

        RetrofitClient retrofit = RetrofitClient.getInstance();
        jsonRequests = retrofit.getJson();

        spnFaculties = findViewById(R.id.spn_major_menu_faculty);
        ArrayAdapter<Faculty> facultyAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Constants.getFaculties());
        facultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFaculties.setAdapter(facultyAdapter);
    }

    public void viewMajors(View view) {
        Constants.setViewIntent("MAJORS");
        Intent viewIntent = new Intent(this, ViewActivity.class);
        startActivity(viewIntent);
    }

    public void addMajor(View view) {
        String name = txtMajorName.getText().toString();
        Faculty faculty = (Faculty) spnFaculties.getSelectedItem();
        if (name.length() == 0 || faculty == null) {
            Toast.makeText(MajorMenuActivity.this, "Completati campurile!", Toast.LENGTH_SHORT).show();
            return;
        }
        final Major major = new Major(name, faculty);

        Call<Major> call = jsonRequests.postMajor(Constants.getToken(), major);
        call.enqueue(new Callback<Major>() {
            @Override
            public void onResponse(Call<Major> call, Response<Major> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MajorMenuActivity.this, "Nume deja existent!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body() == null) {
                    Toast.makeText(MajorMenuActivity.this, "Internal Error!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Util.getMajors();
                String content = "Specializare adaugata!\n";
                content += "ID: " + response.body().getId() + "\n";
                content += response.body().displayMajorDetailed();
                Toast.makeText(MajorMenuActivity.this, content, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Major> call, Throwable t) {

            }
        });
    }
}
