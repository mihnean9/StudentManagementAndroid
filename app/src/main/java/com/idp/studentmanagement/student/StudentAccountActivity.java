package com.idp.studentmanagement.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.idp.studentmanagement.account.EditPersonalDataActivity;
import com.idp.studentmanagement.R;
import com.idp.studentmanagement.account.ViewPersonalDataActivity;
import com.idp.studentmanagement.general.Constants;

public class StudentAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_account);
        TextView greetingTxtv = findViewById(R.id.txtv_stud_welcome);
        String message = "Welcome, " + Constants.getUser().getFirstName()+ "!";
        greetingTxtv.setText(message);
    }

    public void viewStudentData(View view) {
        Intent viewIntent = new Intent(this, ViewPersonalDataActivity.class);
        startActivity(viewIntent);
    }

    public void editStudentData(View view) {
        Intent editUserIntent = new Intent(this, EditPersonalDataActivity.class);
        startActivity(editUserIntent);
    }

    public void viewGrades(View view) {
        Intent viewIntent = new Intent(this, ViewPersonalGradesActivity.class);
        startActivity(viewIntent);
    }

    public void logout(View view) {
        Constants.logout(this);
    }
}
