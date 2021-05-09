package com.idp.studentmanagement.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.idp.studentmanagement.R;
import com.idp.studentmanagement.general.Constants;
import com.idp.studentmanagement.secretary.SecretaryAdministrativeActivity;

public class AdminAdministrativeActivity extends AppCompatActivity {

    private Button viewBtn;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_administrative);
    }

    public void viewUsers(View view) {
        Constants.setViewIntent("USERS");
        Intent viewUsersIntent = new Intent(this, ViewActivity.class);
        startActivity(viewUsersIntent);
    }

    public void addUser(View view) {
        Intent addUserIntent = new Intent(this, AddUserActivity.class);
        startActivity(addUserIntent);
    }

    public void openSecretaryOptions(View view) {
        Intent intent = new Intent(this, SecretaryAdministrativeActivity.class);
        startActivity(intent);
    }

    public void openGrupeMenu(View view) {
        Intent intent = new Intent(this, GrupaMenuActivity.class);
        startActivity(intent);
    }

    public void openSeriiMenu(View view) {
        Intent intent = new Intent(this, SerieMenuActivity.class);
        startActivity(intent);
    }

    public void openMajorsMenu(View view) {
        Intent intent = new Intent(this, MajorMenuActivity.class);
        startActivity(intent);
    }

    public void openFacultiesMenu(View view) {
        Intent intent = new Intent(this, FacultyMenuActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        Constants.logout(this);
    }
}