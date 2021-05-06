package com.idp.studentmanagement.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.idp.studentmanagement.R;
import com.idp.studentmanagement.general.Constants;


public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        TextView txtvGreeting = findViewById(R.id.txtv_welcome);
        String message = "Welcome, " + Constants.getUser().getFirstName() + "!";
        txtvGreeting.setText(message);
        ConstraintLayout layout = findViewById(R.id.layout_account);
        layout.setBackgroundResource(Constants.getBackground());
    }

    public void openPersonalMenu(View view) {
        Intent intent = new Intent(this, PersonalActivity.class);
        startActivity(intent);
    }

    public void openAdministrativeMenu(View view) {
        Intent intent;
        Toast.makeText(this, "COMING SOON", Toast.LENGTH_SHORT).show();
       /* if (Constants.getUser().getUserType().getType().equals("ADMIN"))
            intent = new Intent(this, AdminAdministrativeActivity.class);
        else
            intent = new Intent(this, SecretaryAdministrativeActivity.class);
        startActivity(intent);*/
    }

    public void logout(View view) {
        Constants.logout(this);
    }
}
