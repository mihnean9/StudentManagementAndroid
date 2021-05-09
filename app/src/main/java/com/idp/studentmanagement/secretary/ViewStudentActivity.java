package com.idp.studentmanagement.secretary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.idp.studentmanagement.R;
import com.idp.studentmanagement.api.JsonRequests;
import com.idp.studentmanagement.api.RetrofitClient;
import com.idp.studentmanagement.general.Constants;
import com.idp.studentmanagement.objects.Student;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewStudentActivity extends AppCompatActivity {

    private EditText txtUsername;
    private TextView txtvResult;
    private Button btnEdit;
    private JsonRequests jsonRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        ConstraintLayout layout = findViewById(R.id.layout_view_student);
        layout.setBackgroundResource(Constants.getBackground());

        txtUsername = findViewById(R.id.txt_view_student_username);
        txtvResult = findViewById(R.id.txtv_view_student_result);
        btnEdit = findViewById(R.id.btn_view_student_edit);

        btnEdit.setVisibility(View.GONE);

        RetrofitClient retrofit = RetrofitClient.getInstance();
        jsonRequests = retrofit.getJson();
    }


    public void viewStudent(View view) {
        Student currentStudent = Constants.getCurrentStudent();
        String student_id = txtUsername.getText().toString();

        if (currentStudent != null && (currentStudent.getUser().getLogin().equals(student_id) || student_id.length() == 0)) {
            txtvResult.setText(currentStudent.displayStudent());
            btnEdit.setVisibility(View.VISIBLE);
            return;
        }
        if (currentStudent == null && student_id.length() == 0) {
            Toast.makeText(ViewStudentActivity.this, "Nu este salvat un student curent", Toast.LENGTH_SHORT).show();
            btnEdit.setVisibility(View.GONE);
            return;
        }

        Call<Student> call = jsonRequests.getStudent(student_id, Constants.getToken());
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewStudentActivity.this, "Utilizatorul nu exista!", Toast.LENGTH_SHORT).show();
                    btnEdit.setVisibility(View.GONE);
                    return;
                }

                Student student = response.body();
                if (student == null) {
                    Toast.makeText(ViewStudentActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                    btnEdit.setVisibility(View.GONE);
                    return;
                }
                Constants.setCurrentStudent(student);
                txtvResult.setText(student.displayStudent());
                btnEdit.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

            }
        });
    }

    public void editStudent(View view) {
        Intent intent = new Intent(this, EditStudentActivity.class);
        startActivity(intent);
    }
}
