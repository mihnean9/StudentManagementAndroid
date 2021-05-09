package com.idp.studentmanagement.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import com.idp.studentmanagement.R;
import com.idp.studentmanagement.api.JsonRequests;
import com.idp.studentmanagement.api.RetrofitClient;
import com.idp.studentmanagement.general.Constants;
import com.idp.studentmanagement.general.Util;
import com.idp.studentmanagement.objects.Faculty;
import com.idp.studentmanagement.objects.Grupa;
import com.idp.studentmanagement.objects.Major;
import com.idp.studentmanagement.objects.Seria;
import com.idp.studentmanagement.objects.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonRequests jsonRequests;
    private String token;

    private Spinner spnGrupe;
    private Spinner spnSerii;
    private Spinner spnMajors;
    private Spinner spnFaculties;

    private Button btnEditGrupa;
    private Button btnEditSerie;
    private Button btnEditMajor;
    private Button btnEditFaculty;

    private Button btnCancelEditGrupa;
    private Button btnCancelEditSerie;
    private Button btnCancelEditMajor;
    private Button btnCancelEditFaculty;

    private TextView txtvGrupa;
    private TextView txtvSerie;
    private TextView txtvMajor;
    private TextView txtvFaculty;

    private EditText txtGrupa;
    private EditText txtSerie;
    private EditText txtMajor;
    private EditText txtFaculty;

    private Button btnSubmit;
    private Button btnUpdate;

    private Grupa grupa;
    private Seria serie;
    private Major major;
    private Faculty faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        textViewResult = findViewById(R.id.text_view_result);
        textViewResult.setMovementMethod(new ScrollingMovementMethod());

        spnGrupe = findViewById(R.id.spn_view_grupa);
        spnSerii = findViewById(R.id.spn_view_serie);
        spnMajors = findViewById(R.id.spn_view_major);
        spnFaculties = findViewById(R.id.spn_view_faculty);

        Util.setGrupaAdapter(this, spnGrupe);
        Util.setSeriaAdapter(this, spnSerii);
        Util.setMajorAdapter(this, spnMajors);
        Util.setFacultyAdapter(this, spnFaculties);

        txtvGrupa = findViewById(R.id.txtv_grupa);
        txtvSerie = findViewById(R.id.txtv_serie);
        txtvMajor = findViewById(R.id.txtv_major);
        txtvFaculty = findViewById(R.id.txtv_faculty);
        txtGrupa = findViewById(R.id.txt_edit_grupa);
        txtSerie = findViewById(R.id.txt_edit_serie);
        txtMajor = findViewById(R.id.txt_edit_major);
        txtFaculty = findViewById(R.id.txt_edit_faculty);

        btnEditGrupa = findViewById(R.id.btn_edit_grupa);
        btnCancelEditGrupa = findViewById(R.id.btn_cancel_edit_grupa);
        btnEditSerie = findViewById(R.id.btn_edit_serie);
        btnCancelEditSerie = findViewById(R.id.btn_cancel_edit_serie);
        btnEditMajor = findViewById(R.id.btn_edit_major);
        btnCancelEditMajor = findViewById(R.id.btn_cancel_edit_major);
        btnEditFaculty = findViewById(R.id.btn_edit_faculty);
        btnCancelEditFaculty = findViewById(R.id.btn_cancel_edit_faculty);

        btnSubmit = findViewById(R.id.btn_submit);
        btnUpdate = findViewById(R.id.btn_update);

        RetrofitClient retrofit = RetrofitClient.getInstance();
        jsonRequests = retrofit.getJson();
        token = Constants.getToken();

        makeAllInvisible();
        callRightFunction();
    }

    private void callRightFunction() {
        switch(Constants.getViewIntent()) {
            case "USERS":
                textViewResult.setVisibility(View.VISIBLE);
                getUsers();
                break;

            case "FACULTIES":
                getFaculties();
                break;

            case "MAJORS":
                getMajors();
                break;

            case "GRUPE":
                getGrupe();
                break;

            case "SERII":
                getSerii();
                break;
        }
    }

    private void makeAllInvisible() {
        btnEditGrupa.setVisibility(View.GONE);
        btnEditSerie.setVisibility(View.GONE);
        btnEditMajor.setVisibility(View.GONE);
        btnEditFaculty.setVisibility(View.GONE);

        btnCancelEditGrupa.setVisibility(View.GONE);
        btnCancelEditSerie.setVisibility(View.GONE);
        btnCancelEditMajor.setVisibility(View.GONE);
        btnCancelEditFaculty.setVisibility(View.GONE);

        textViewResult.setVisibility(View.GONE);
        txtvGrupa.setVisibility(View.GONE);
        txtvSerie.setVisibility(View.GONE);
        txtvMajor.setVisibility(View.GONE);
        txtvFaculty.setVisibility(View.GONE);

        txtGrupa.setVisibility(View.GONE);
        txtSerie.setVisibility(View.GONE);
        txtMajor.setVisibility(View.GONE);
        txtFaculty.setVisibility(View.GONE);

        spnGrupe.setVisibility(View.GONE);
        spnSerii.setVisibility(View.GONE);
        spnMajors.setVisibility(View.GONE);
        spnFaculties.setVisibility(View.GONE);

        btnSubmit.setVisibility(View.GONE);
        //btnUpdate.setVisibility(View.GONE);
    }

    private void getMajors() {
        Util.getMajors();
        if (Constants.getMajors() == null) {
            Toast.makeText(ViewActivity.this, "NO RESULTS", Toast.LENGTH_SHORT).show();
            return;
        }

        spnMajors.setVisibility(View.VISIBLE);
        btnEditMajor.setVisibility(View.VISIBLE);
    }

    private void getSerii() {
        Util.getSerii();
        if (Constants.getSerii() == null) {
            Toast.makeText(ViewActivity.this, "NO RESULTS", Toast.LENGTH_SHORT).show();
            return;
        }

        spnSerii.setVisibility(View.VISIBLE);
        btnEditSerie.setVisibility(View.VISIBLE);
    }

    private void getGrupe() {
        Util.getGrupe();
        if (Constants.getGrupe() == null) {
            Toast.makeText(ViewActivity.this, "NO RESULTS", Toast.LENGTH_SHORT).show();
            return;
        }

        spnGrupe.setVisibility(View.VISIBLE);
        btnEditGrupa.setVisibility(View.VISIBLE);
    }

    private void getUsers() {
        Call<List<User>> call = jsonRequests.getUsers(token);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<User> users = response.body();
                if (users == null) {
                    Toast.makeText(ViewActivity.this, "NO RESULTS", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (User user : users) {
                    textViewResult.append(user.displayUser());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
    }

    private void getFaculties() {
        Util.getFacutlies();
        if (Constants.getFaculties() == null) {
            Toast.makeText(ViewActivity.this, "NO RESULTS", Toast.LENGTH_SHORT).show();
            return;
        }

        spnFaculties.setVisibility(View.VISIBLE);
        btnEditFaculty.setVisibility(View.VISIBLE);
    }

    public void openEditGrupa(View view) {
        grupa = (Grupa) spnGrupe.getSelectedItem();

        spnGrupe.setVisibility(View.GONE);
        btnEditGrupa.setVisibility(View.GONE);

        spnSerii.setVisibility(View.VISIBLE);
        spnMajors.setVisibility(View.VISIBLE);

        txtGrupa.setVisibility(View.VISIBLE);
        txtvGrupa.setVisibility(View.VISIBLE);
        txtvGrupa.setText(grupa.displayGrupaDetailed());

        btnCancelEditGrupa.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.VISIBLE);
        btnUpdate.setVisibility(View.GONE);
    }

    public void openEditSerie(View view) {
        serie = (Seria) spnSerii.getSelectedItem();

        spnSerii.setVisibility(View.GONE);
        btnEditSerie.setVisibility(View.GONE);

        txtSerie.setVisibility(View.VISIBLE);
        txtvSerie.setVisibility(View.VISIBLE);
        txtvSerie.setText(serie.toString());

        btnCancelEditSerie.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.VISIBLE);
        btnUpdate.setVisibility(View.GONE);
    }

    public void openEditMajor(View view) {
        major = (Major) spnMajors.getSelectedItem();

        spnMajors.setVisibility(View.GONE);
        btnEditMajor.setVisibility(View.GONE);

        spnFaculties.setVisibility(View.VISIBLE);

        txtMajor.setVisibility(View.VISIBLE);
        txtvMajor.setVisibility(View.VISIBLE);
        txtvMajor.setText(major.displayMajorDetailed());

        btnCancelEditMajor.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.VISIBLE);
        btnUpdate.setVisibility(View.GONE);
    }

    public void openEditFaculty(View view) {
        faculty = (Faculty) spnFaculties.getSelectedItem();

        spnFaculties.setVisibility(View.GONE);
        btnEditFaculty.setVisibility(View.GONE);

        txtFaculty.setVisibility(View.VISIBLE);
        txtvFaculty.setVisibility(View.VISIBLE);
        txtvFaculty.setText(faculty.toString());

        btnCancelEditFaculty.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.VISIBLE);
        btnUpdate.setVisibility(View.GONE);
    }

    public void closeEditGrupa(View view) {
        spnGrupe.setVisibility(View.VISIBLE);
        btnEditGrupa.setVisibility(View.VISIBLE);

        txtvGrupa.setVisibility(View.GONE);
        txtGrupa.setVisibility(View.GONE);
        btnCancelEditGrupa.setVisibility(View.GONE);
        btnSubmit.setVisibility(View.GONE);

        spnSerii.setVisibility(View.GONE);
        spnMajors.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.VISIBLE);
    }

    public void closeEditSerie(View view) {
        spnSerii.setVisibility(View.VISIBLE);
        btnEditSerie.setVisibility(View.VISIBLE);

        txtvSerie.setVisibility(View.GONE);
        txtSerie.setVisibility(View.GONE);
        btnCancelEditSerie.setVisibility(View.GONE);
        btnSubmit.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.VISIBLE);
    }

    public void closeEditMajor(View view) {
        spnMajors.setVisibility(View.VISIBLE);
        btnEditMajor.setVisibility(View.VISIBLE);

        txtvMajor.setVisibility(View.GONE);
        txtMajor.setVisibility(View.GONE);
        btnCancelEditMajor.setVisibility(View.GONE);
        btnSubmit.setVisibility(View.GONE);

        spnFaculties.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.VISIBLE);
    }

    public void closeEditFaculty(View view) {
        spnFaculties.setVisibility(View.VISIBLE);
        btnEditFaculty.setVisibility(View.VISIBLE);

        txtvFaculty.setVisibility(View.GONE);
        txtFaculty.setVisibility(View.GONE);
        btnCancelEditFaculty.setVisibility(View.GONE);
        btnSubmit.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.VISIBLE);
    }

    public void submit(View view) {
        switch(Constants.getViewIntent()) {
            case "FACULTIES":
                putFaculty();
                break;

            case "MAJORS":
                putMajor();
                break;

            case "GRUPE":
                putGrupa();
                break;

            case "SERII":
                putSerie();
                break;
        }
    }

    private void putFaculty() {
        String name = txtFaculty.getText().toString();
        if (name.length() == 0) {
            Toast.makeText(ViewActivity.this, "Introduceti un nume de facultate!", Toast.LENGTH_SHORT).show();
            return;
        }
        faculty.setName(name);
        Call<Faculty> call = jsonRequests.putFaculty(faculty.getId(), Constants.getToken(), faculty);
        call.enqueue(new Callback<Faculty>() {
            @Override
            public void onResponse(Call<Faculty> call, Response<Faculty> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewActivity.this, "Nume deja existent!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body() == null) {
                    Toast.makeText(ViewActivity.this, "Internal Error!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String content = "Modificari salvate!\n";
                content += "ID: " + response.body().getId() + "\n";
                content += "Name: " + response.body().getName();
                Toast.makeText(ViewActivity.this, content, Toast.LENGTH_LONG).show();

                Util.getFacutlies();
            }

            @Override
            public void onFailure(Call<Faculty> call, Throwable t) {

            }
        });
    }

    private void putMajor() {
        String name = txtMajor.getText().toString();
        if (name.length() != 0) {
            major.setName(name);
        }

        Faculty major_faculty = (Faculty) spnFaculties.getSelectedItem();
        major.setFaculty(major_faculty);

        Call<Major> call = jsonRequests.putMajor(major.getId(), Constants.getToken(), major);
        call.enqueue(new Callback<Major>() {
            @Override
            public void onResponse(Call<Major> call, Response<Major> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewActivity.this, "Nume deja existent!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body() == null) {
                    Toast.makeText(ViewActivity.this, "Internal Error!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String content = "Modificari salvate!\n";
                content += "ID: " + response.body().getId() + "\n";
                content += response.body().displayMajorDetailed();
                Toast.makeText(ViewActivity.this, content, Toast.LENGTH_LONG).show();

                Util.getMajors();
            }

            @Override
            public void onFailure(Call<Major> call, Throwable t) {

            }
        });
    }

    private void putGrupa() {
        String number = txtGrupa.getText().toString();
        if (number.length() != 0) {
            grupa.setNumber(Integer.parseInt(number));
        }

        Major grupa_major = (Major) spnMajors.getSelectedItem();
        grupa.setMajor(grupa_major);

        Seria grupa_seria = (Seria) spnSerii.getSelectedItem();
        grupa.setSeria(grupa_seria);

        Call<Grupa> call = jsonRequests.putGrupa(grupa.getId(), Constants.getToken(), grupa);
        call.enqueue(new Callback<Grupa>() {
            @Override
            public void onResponse(Call<Grupa> call, Response<Grupa> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewActivity.this, "Nume deja existent!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body() == null) {
                    Toast.makeText(ViewActivity.this, "Internal Error!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String content = "Modificari salvate!\n";
                content += "ID: " + response.body().getId() + "\n";
                content += response.body().displayGrupaDetailed();
                Toast.makeText(ViewActivity.this, content, Toast.LENGTH_LONG).show();

                Util.getGrupe();
            }

            @Override
            public void onFailure(Call<Grupa> call, Throwable t) {

            }
        });
    }

    private void putSerie() {
        String name = txtSerie.getText().toString();
        if (name.length() == 0) {
            Toast.makeText(ViewActivity.this, "Introduceti un nume de serie!", Toast.LENGTH_SHORT).show();
            return;
        }
        serie.setName(name);
        Call<Seria> call = jsonRequests.putSerie(serie.getId(), Constants.getToken(), serie);
        call.enqueue(new Callback<Seria>() {
            @Override
            public void onResponse(Call<Seria> call, Response<Seria> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewActivity.this, "Nume deja existent!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body() == null) {
                    Toast.makeText(ViewActivity.this, "Internal Error!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String content = "Modificari salvate!\n";
                content += "ID: " + response.body().getId() + "\n";
                content += "Name: " + response.body().getName();
                Toast.makeText(ViewActivity.this, content, Toast.LENGTH_LONG).show();

                Util.getSerii();
            }

            @Override
            public void onFailure(Call<Seria> call, Throwable t) {

            }
        });
    }

    public void updateInfo(View view) {
        callRightFunction();
    }
}
