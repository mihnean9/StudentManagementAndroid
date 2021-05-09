package com.idp.studentmanagement.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.idp.studentmanagement.R;
import com.idp.studentmanagement.api.JsonRequests;
import com.idp.studentmanagement.api.RetrofitClient;
import com.idp.studentmanagement.general.Constants;
import com.idp.studentmanagement.general.Util;
import com.idp.studentmanagement.objects.Grupa;
import com.idp.studentmanagement.objects.Major;
import com.idp.studentmanagement.objects.Seria;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GrupaMenuActivity extends AppCompatActivity {

    private EditText txtGroupNr;
    private JsonRequests jsonRequests;
    private Spinner spnMajors;
    private Spinner spnSerii;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupa_menu);

        txtGroupNr = findViewById(R.id.txt_name_faculty_add);

        RetrofitClient retrofit = RetrofitClient.getInstance();
        jsonRequests = retrofit.getJson();

        spnMajors = findViewById(R.id.spn_grupa_menu_major);
        spnSerii = findViewById(R.id.spn_grupa_menu_serie);

        ArrayAdapter<Seria> serieAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Constants.getSerii());
        serieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSerii.setAdapter(serieAdapter);

        ArrayAdapter<Major> majorAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Constants.getMajors());
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMajors.setAdapter(majorAdapter);
        spnMajors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Major tmp = (Major) parent.getSelectedItem();
                if (tmp != null)
                    Toast.makeText(GrupaMenuActivity.this, tmp.displayMajorDetailed(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void viewGrupe(View view) {
        Constants.setViewIntent("GRUPE");
        Intent viewIntent = new Intent(this, ViewActivity.class);
        startActivity(viewIntent);
    }

    public void addGrupa(View view) {
        String number = txtGroupNr.getText().toString();
        Major major = (Major) spnMajors.getSelectedItem();
        Seria seria = (Seria) spnSerii.getSelectedItem();

        if (number.length() == 0 || major == null || seria == null) {
            Toast.makeText(GrupaMenuActivity.this, "Completati campurile!", Toast.LENGTH_SHORT).show();
            return;
        }
        final Grupa grupa = new Grupa(seria, Integer.parseInt(number), major);

        Call<Grupa> call = jsonRequests.postGrupa(Constants.getToken(), grupa);
        call.enqueue(new Callback<Grupa>() {
            @Override
            public void onResponse(Call<Grupa> call, Response<Grupa> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(GrupaMenuActivity.this, "Nume deja existent!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body() == null) {
                    Toast.makeText(GrupaMenuActivity.this, "Internal Error!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Util.getGrupe();
                String content = "Grupa adaugata!\n";
                content += "ID: " + response.body().getId() + "\n";
                content += response.body().displayGrupaDetailed();
                Toast.makeText(GrupaMenuActivity.this, content, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Grupa> call, Throwable t) {

            }
        });
    }
}
