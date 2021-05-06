package com.idp.studentmanagement.general;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.idp.studentmanagement.api.JsonRequests;
import com.idp.studentmanagement.api.RetrofitClient;
import com.idp.studentmanagement.objects.Faculty;
import com.idp.studentmanagement.objects.Grupa;
import com.idp.studentmanagement.objects.Major;
import com.idp.studentmanagement.objects.Seria;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Util {
    private static RetrofitClient retrofit = RetrofitClient.getInstance();
    private static JsonRequests jsonRequests = retrofit.getJson();

    public static void getFacutlies() {
        Call<List<Faculty>> facultiesCall = jsonRequests.getFaculties(Constants.getToken());
        facultiesCall.enqueue(new Callback<List<Faculty>>() {
            @Override
            public void onResponse(Call<List<Faculty>> call, Response<List<Faculty>> response) {
                Constants.setFaculties(response.body());
            }

            @Override
            public void onFailure(Call<List<Faculty>> call, Throwable t) {
                Constants.setFaculties(null);
            }
        });
    }

    public static void getMajors() {
        Call<List<Major>> majorsCall = jsonRequests.getMajors(Constants.getToken());
        majorsCall.enqueue(new Callback<List<Major>>() {
            @Override
            public void onResponse(Call<List<Major>> call, Response<List<Major>> response) {
                Constants.setMajors(response.body());
            }

            @Override
            public void onFailure(Call<List<Major>> call, Throwable t) {
            }
        });
    }

    public static void getSerii() {
        Call<List<Seria>> seriiCall = jsonRequests.getSerii(Constants.getToken());
        seriiCall.enqueue(new Callback<List<Seria>>() {
            @Override
            public void onResponse(Call<List<Seria>> call, Response<List<Seria>> response) {
                Constants.setSerii(response.body());
            }

            @Override
            public void onFailure(Call<List<Seria>> call, Throwable t) {
            }
        });
    }

    public static void getGrupe() {
        Call<List<Grupa>> grupeCall = jsonRequests.getGrupe(Constants.getToken());
        grupeCall.enqueue(new Callback<List<Grupa>>() {
            @Override
            public void onResponse(Call<List<Grupa>> call, Response<List<Grupa>> response) {
                Constants.setGrupe(response.body());
            }

            @Override
            public void onFailure(Call<List<Grupa>> call, Throwable t) {
            }
        });
    }

    public static void setGrupaAdapter(final Context context, Spinner spnGrupe) {
        ArrayAdapter<Grupa> grupaAdapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, Constants.getGrupe());
        grupaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGrupe.setAdapter(grupaAdapter);
        spnGrupe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Grupa tmp = (Grupa) parent.getSelectedItem();
                if (tmp != null)
                    Toast.makeText(context, tmp.displayGrupaDetailed(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public static void setSeriaAdapter(final Context context, Spinner spnSerii) {
        ArrayAdapter<Seria> serieAdapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, Constants.getSerii());
        serieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSerii.setAdapter(serieAdapter);
    }

    public static void setMajorAdapter(final Context context, Spinner spnMajors) {
        ArrayAdapter<Major> majorAdapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, Constants.getMajors());
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMajors.setAdapter(majorAdapter);
        spnMajors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Major tmp = (Major) parent.getSelectedItem();
                if (tmp != null)
                    Toast.makeText(context, tmp.displayMajorDetailed(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public static void setFacultyAdapter(final Context context, Spinner spnFaculties) {
        ArrayAdapter<Faculty> facultyAdapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, Constants.getFaculties());
        facultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFaculties.setAdapter(facultyAdapter);
    }
}
