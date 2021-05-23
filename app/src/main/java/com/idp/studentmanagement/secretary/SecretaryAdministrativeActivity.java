package com.idp.studentmanagement.secretary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.idp.studentmanagement.R;
import com.idp.studentmanagement.general.Constants;

public class SecretaryAdministrativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secretary_administrative);
        ConstraintLayout layout = findViewById(R.id.layout_secretary);
        layout.setBackgroundResource(Constants.getBackground());
    }

    public void logout(View view) {
        Constants.logout(this);
    }

    public void viewStudent(View view) {
        Intent intent = new Intent(this, ViewStudentActivity.class);
        startActivity(intent);
    }
}
