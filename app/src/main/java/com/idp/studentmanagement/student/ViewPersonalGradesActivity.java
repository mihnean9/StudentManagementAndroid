package com.idp.studentmanagement.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.idp.studentmanagement.R;
import com.idp.studentmanagement.api.JsonRequests;
import com.idp.studentmanagement.api.RetrofitClient;
import com.idp.studentmanagement.general.Constants;
import com.idp.studentmanagement.objects.Grade;
import com.idp.studentmanagement.objects.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPersonalGradesActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonRequests jsonRequests;
    private String token;
    private long sin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_personal_grades);

        textViewResult = findViewById(R.id.grades_text_view_result);
        token = Constants.getToken();
        RetrofitClient retrofit = RetrofitClient.getInstance();
        jsonRequests = retrofit.getJson();
        setSin();
    }

    private void setSin() {
        Call<Student> studentCall = jsonRequests.getStudent(Constants.getUser().getLogin(), token);
        studentCall.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                Student student = response.body();
                if (student == null) {
                    Toast.makeText(ViewPersonalGradesActivity.this, "Internal error", Toast.LENGTH_SHORT).show();
                    return;
                }
                getGrades(student.getSin());
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
            }
        });
    }

    private void getGrades(long sin) {
        Call<List<Grade>> call = jsonRequests.getGrades(sin, token);
        call.enqueue(new Callback<List<Grade>>() {
            @Override
            public void onResponse(Call<List<Grade>> call, Response<List<Grade>> response) {
                List<Grade> grades = response.body();
                if (grades == null) {
                    Toast.makeText(ViewPersonalGradesActivity.this, "NO RESULTS", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (Grade grade : grades) {
                    textViewResult.append(grade.displayGrade());
                }
            }

            @Override
            public void onFailure(Call<List<Grade>> call, Throwable t) {
            }
        });
    }
}
