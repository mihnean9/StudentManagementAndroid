package com.idp.studentmanagement.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.idp.studentmanagement.R;
import com.idp.studentmanagement.api.JsonRequests;
import com.idp.studentmanagement.api.RetrofitClient;
import com.idp.studentmanagement.general.Constants;
import com.idp.studentmanagement.objects.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPersonalDataActivity extends AppCompatActivity {

    private EditText txtNewPass;
    private EditText txtNewPassConf;
    private EditText txtNewUsername;
    private EditText txtNewFName;
    private EditText txtNewLName;
    private EditText txtNewPhone;
    private EditText txtNewEmail;
    private JsonRequests jsonRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_data);
        ConstraintLayout layout = findViewById(R.id.layout_edit_personal);
        layout.setBackgroundResource(Constants.getBackground());

        RetrofitClient retrofit = RetrofitClient.getInstance();
        jsonRequests = retrofit.getJson();

        txtNewPass = findViewById(R.id.txt_edit_pers_new_pass);
        txtNewPassConf = findViewById(R.id.txt_edit_pers_conf_pass);
        txtNewUsername = findViewById(R.id.txt_edit_pers_new_user);
        txtNewFName = findViewById(R.id.txt_edit_pers_new_fname);
        txtNewLName = findViewById(R.id.txt_edit_pers_new_lname);
        txtNewPhone = findViewById(R.id.txt_edit_pers_new_phone);
        txtNewEmail = findViewById(R.id.txt_edit_pers_new_email);
    }

    public void putUser(View view) {
        String password = txtNewPass.getText().toString();
        String confirmation = txtNewPassConf.getText().toString();
        String username = txtNewUsername.getText().toString();
        String fname = txtNewFName.getText().toString();
        String lname = txtNewLName.getText().toString();
        String phone = txtNewPhone.getText().toString();
        String email = txtNewEmail.getText().toString();

        if (password.length() == 0 &&
            username.length() == 0 &&
            fname.length() == 0 &&
            lname.length() == 0 &&
            phone.length() == 0 &&
            email.length() == 0) {
            Toast.makeText(this, "Niciun camp completat!", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = Constants.getUser();

        if (password.length() > 0) {
            if (password.length() < 6) {
                Toast.makeText(this, "Recomandam parola de\ncel putin 6 caractere", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals((confirmation))) {
                Toast.makeText(this, "Parolele nu se potrivesc!", Toast.LENGTH_SHORT).show();
                return;
            }
            user.setPassword(password);
        }

        if (username.length() > 0) {
            user.setLogin(username);
        }

        if (fname.length() > 0) {
            user.setFirstName(fname);
        }

        if (lname.length() > 0) {
            user.setLastName(lname);
        }

        if (phone.length() > 0) {
            if (phone.length() != 9) {
                Toast.makeText(this, "Nr de telefon trebuie\nsa fie format din 9 cifre!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (phone.charAt(0) == '0') {
                Toast.makeText(this, "Nr de telefon nu trebuie\nsa inceapa cu 0!", Toast.LENGTH_SHORT).show();
                return;
            }
            user.setPhoneNumber(Integer.parseInt(phone));
        }

        if (email.length() > 0) {
            if (!email.contains("@")) {
                Toast.makeText(this, "Emailul trebuie sa fie\nde forma a@b", Toast.LENGTH_SHORT).show();
                return;
            }
            user.setEmail(email);
        }

        Call<User> call = jsonRequests.putUser(user.getId(), Constants.getToken(), user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User responseUser = response.body();
                if (responseUser == null) {
                    Toast.makeText(EditPersonalDataActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(EditPersonalDataActivity.this, "Schimbarile au fost salvate!", Toast.LENGTH_SHORT).show();
                Constants.setUser(responseUser);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }
}
