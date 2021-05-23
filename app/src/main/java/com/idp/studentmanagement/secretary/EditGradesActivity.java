package com.idp.studentmanagement.secretary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.idp.studentmanagement.R;
import com.idp.studentmanagement.api.JsonRequests;
import com.idp.studentmanagement.general.Constants;
import com.idp.studentmanagement.objects.Clasa;
import com.idp.studentmanagement.objects.Grade;
import com.idp.studentmanagement.objects.Student;
import com.idp.studentmanagement.student.ViewPersonalGradesActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditGradesActivity extends AppCompatActivity implements ClassesRecyclerViewAdapter.OnClassClickListener {

    Button btnSubmit;
    EditText txtGrade;
    RecyclerView rvClasses;
    Clasa clasa;
    List<Clasa> clase;
    private JsonRequests jsonRequests;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grade);
        ConstraintLayout layout = findViewById(R.id.layout_edit_student);
        layout.setBackgroundResource(Constants.getBackground());
        token = Constants.getToken();
        getClasses();

        txtGrade = findViewById(R.id.editTextGrade);
        rvClasses = findViewById(R.id.classesRecyclerView);

        ClassesRecyclerViewAdapter adapter = new ClassesRecyclerViewAdapter(clase, this);
        rvClasses.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void getClasses() {
        Call<List<Clasa>> classesCall = jsonRequests.getClasses(token);
        classesCall.enqueue(new Callback<List<Clasa>>() {
            @Override
            public void onResponse(Call<List<Clasa>> call, Response<List<Clasa>> response) {
                clase = response.body();
                if (clase == null) {
                    Toast.makeText(EditGradesActivity.this, "Internal error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Clasa>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onClassClick(int position) {
        clasa = clase.get(position);
    }

    public void submitGrade(View view) {
        Student student = Constants.getCurrentStudent();
        Grade grade = new Grade(clasa, student, Integer.parseInt(txtGrade.getText().toString()));

        Call<Grade> gradeCall = jsonRequests.postGrade(token, grade);
        gradeCall.enqueue(new Callback<Grade>() {
            @Override
            public void onResponse(Call<Grade> call, Response<Grade> response) {
                Toast.makeText(EditGradesActivity.this, "Grade Submited!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Grade> call, Throwable t) {
                Toast.makeText(EditGradesActivity.this, "Server Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
