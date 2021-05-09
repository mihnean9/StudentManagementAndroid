package com.idp.studentmanagement.secretary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.idp.studentmanagement.R;
import com.idp.studentmanagement.api.JsonRequests;
import com.idp.studentmanagement.api.RetrofitClient;
import com.idp.studentmanagement.general.Constants;
import com.idp.studentmanagement.general.Util;
import com.idp.studentmanagement.objects.Grupa;
import com.idp.studentmanagement.objects.Student;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditStudentActivity extends AppCompatActivity {

    private EditText txtNewPass;
    private TextView txtvCurrGrupa;
    private TextView txtvGrupaDetails;
    private Spinner spnGrupa;
    private JsonRequests jsonRequests;
    private Student student;
    private Student newStudent;
    private boolean commitedPass;
    private boolean commitedGrupa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        ConstraintLayout layout = findViewById(R.id.layout_edit_student);
        layout.setBackgroundResource(Constants.getBackground());

        Util.getGrupe();

        RetrofitClient retrofit = RetrofitClient.getInstance();
        jsonRequests = retrofit.getJson();

        student = Constants.getCurrentStudent();

        txtNewPass = findViewById(R.id.txt_edit_stud_new_pass);
        txtvCurrGrupa = findViewById(R.id.txtv_edit_stud_curr_grupa);
        if (student.getGrupa() != null)
            txtvCurrGrupa.append("Grupa curenta: " + student.getGrupa().toString());
        else
            txtvCurrGrupa.append("Studentul nu este inscris in nicio grupa");
        txtvGrupaDetails = findViewById(R.id.txtv_edit_stud_grupa_details);

        spnGrupa = findViewById(R.id.spn_edit_stud_grupa);
        ArrayAdapter<Grupa> grupaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Constants.getGrupe());
        grupaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGrupa.setAdapter(grupaAdapter);
        spnGrupa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Grupa tmp = (Grupa) parent.getSelectedItem();
                txtvGrupaDetails.setText(tmp.displayGrupaDetailed());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        commitedPass = false;
        commitedGrupa = false;
        newStudent = new Student(student);
    }

    public void commitPasswordChange(View view) {
        String pass = txtNewPass.getText().toString();
        if (pass.length() == 0) {
            commitedPass = false;
            Toast.makeText(EditStudentActivity.this, "Nu ati introdus nicio parola!", Toast.LENGTH_SHORT).show();
            return;
        }
        newStudent.getUser().setPassword(pass);
        commitedPass = true;
        Toast.makeText(EditStudentActivity.this, "Changes commited!", Toast.LENGTH_SHORT).show();
    }

    public void commitGrupaChange(View view) {
        Grupa grupa = (Grupa) spnGrupa.getSelectedItem();
        if (grupa.equals(student.getGrupa())) {
            commitedGrupa = false;
            Toast.makeText(EditStudentActivity.this, "Alegeti o grupa diferita!", Toast.LENGTH_SHORT).show();
            return;
        }
        newStudent.setGrupa(grupa);
        commitedGrupa = true;
        Toast.makeText(EditStudentActivity.this, "Changes commited!", Toast.LENGTH_SHORT).show();
    }

    public void putStudent(View view) {
        if (!commitedPass && !commitedGrupa) {
            Toast.makeText(EditStudentActivity.this, "Nu ati salvat nicio schimbare!", Toast.LENGTH_SHORT).show();
        }

        Call<Student> call = jsonRequests.putStudent(student.getUser().getId(),
                Constants.getToken(),
                newStudent);

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                Student responseStudent = response.body();
                if (responseStudent == null) {
                    Toast.makeText(EditStudentActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(EditStudentActivity.this, "Schimbarile au fost salvate!", Toast.LENGTH_SHORT).show();
                Constants.setCurrentStudent(responseStudent);
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
            }
        });

    }


}
