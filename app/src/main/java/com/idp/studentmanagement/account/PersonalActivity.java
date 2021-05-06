package com.idp.studentmanagement.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.idp.studentmanagement.R;
import com.idp.studentmanagement.general.Constants;

public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ConstraintLayout layout = findViewById(R.id.layout_personal);
        layout.setBackgroundResource(Constants.getBackground());
    }
    public void logout(View view) {
        Constants.logout(this);
    }

    public void viewUserData(View view) {
        Intent viewUserIntent = new Intent(this, ViewPersonalDataActivity.class);
        startActivity(viewUserIntent);
    }

    public void editUserData(View view) {
        Intent editUserIntent = new Intent(this, EditPersonalDataActivity.class);
        startActivity(editUserIntent);
    }
}
