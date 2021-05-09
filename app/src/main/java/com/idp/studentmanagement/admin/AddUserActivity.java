package com.idp.studentmanagement.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.idp.studentmanagement.R;
import com.idp.studentmanagement.general.Constants;
import com.idp.studentmanagement.general.Util;
import com.idp.studentmanagement.objects.Admin;
import com.idp.studentmanagement.objects.Faculty;
import com.idp.studentmanagement.objects.Secretary;
import com.idp.studentmanagement.objects.UserTypes;
import com.idp.studentmanagement.api.JsonRequests;
import com.idp.studentmanagement.api.RetrofitClient;
import com.idp.studentmanagement.objects.Grupa;
import com.idp.studentmanagement.objects.Student;
import com.idp.studentmanagement.objects.User;
import com.idp.studentmanagement.objects.UserType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddUserActivity extends AppCompatActivity {

    private EditText txtSIN;
    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtFatherInitial;
    private EditText txtCNP;
    private Spinner spnGrupa;
    private Spinner spnUserType;
    private TextView txtvGrupaDetails;
    private EditText txtUsername;
    private EditText txtPassword;
    private Spinner spnFaculty;

    private JsonRequests jsonRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Util.getGrupe();
        Util.getFacutlies();

        txtvGrupaDetails = findViewById(R.id.txtv_grupa_details);
        txtSIN = findViewById(R.id.txt_sin);
        txtFirstName = findViewById(R.id.txt_fname);
        txtLastName = findViewById(R.id.txt_lname);
        txtFatherInitial = findViewById(R.id.txt_fi);
        txtCNP = findViewById(R.id.txt_cnp);
        spnGrupa = findViewById(R.id.spn_grupa);

        ArrayAdapter<Grupa> grupaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Constants.getGrupe());
        grupaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGrupa.setAdapter(grupaAdapter);
        spnGrupa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Grupa tmp = (Grupa) parent.getSelectedItem();
                if (tmp != null)
                    txtvGrupaDetails.setText(tmp.displayGrupaDetailed());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spnUserType = findViewById(R.id.spn_usertype);

        ArrayAdapter<UserTypes> userTypeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, UserTypes.values());
        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnUserType.setAdapter(userTypeAdapter);
        spnUserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserTypes type = (UserTypes) parent.getSelectedItem();
                if (type == UserTypes.STUDENT) {
                    txtCNP.setVisibility(View.VISIBLE);
                    txtFatherInitial.setVisibility(View.VISIBLE);
                    txtSIN.setVisibility(View.VISIBLE);
                    spnGrupa.setVisibility(View.VISIBLE);
                    txtUsername.setVisibility(View.INVISIBLE);
                    txtPassword.setVisibility(View.INVISIBLE);
                    txtvGrupaDetails.setVisibility(View.VISIBLE);
                    spnFaculty.setVisibility(View.INVISIBLE);
                } else {
                    txtCNP.setVisibility(View.INVISIBLE);
                    txtFatherInitial.setVisibility(View.INVISIBLE);
                    txtSIN.setVisibility(View.INVISIBLE);
                    spnGrupa.setVisibility(View.INVISIBLE);
                    txtUsername.setVisibility(View.VISIBLE);
                    txtPassword.setVisibility(View.VISIBLE);
                    txtvGrupaDetails.setVisibility(View.INVISIBLE);
                    if (type == UserTypes.ADMIN) {
                        spnFaculty.setVisibility(View.INVISIBLE);
                    } else {
                        spnFaculty.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spnFaculty = findViewById(R.id.spn_faculty);
        Util.setFacultyAdapter(this, spnFaculty);

        txtUsername = findViewById(R.id.txt_add_user);
        txtPassword = findViewById(R.id.txt_add_pass);
        
        RetrofitClient retrofit = RetrofitClient.getInstance();
        jsonRequests = retrofit.getJson();
    }

    @SuppressLint("SetTextI18n")
    public void postPerson(View button) {

        switch ((UserTypes) spnUserType.getSelectedItem()) {
            case ADMIN:
                postAdmin();
                break;

            case SECRETARY:
                postSecretary();
                break;

            case STUDENT:
                postStudent();
                break;
        }
    }

    public void postAdmin() {
        if (!validateUserRequest())
            return;

        String login = txtUsername.getText().toString().toLowerCase().trim();
        String pass = txtPassword.getText().toString();
        String fname = txtFirstName.getText().toString().trim();
        String lname = txtLastName.getText().toString().trim();
        User newUser = new User(login, pass, getSelectedUserType(), fname, lname);
        Admin admin = new Admin(newUser);

        Call<Admin> call = jsonRequests.postAdmin(Constants.getToken(), admin);

        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddUserActivity.this, "Userul deja exista!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body() == null) {
                    Toast.makeText(AddUserActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                    return;
                }

                User postResponse = response.body().getUser();

                String content = "Utilizator adaugat!\n";
                content += "Nume utilizator: " + postResponse.getLogin() + "\n";
                content += "Nume: " + postResponse.getName() + "\n";
                content += "Functie: " + postResponse.getUserType().getType() + "\n\n";
                Toast.makeText(AddUserActivity.this, content, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                Toast.makeText(AddUserActivity.this, "Requestul a esuat!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void postSecretary() {
        if (!validateUserRequest())
            return;

        String login = txtUsername.getText().toString().toLowerCase().trim();
        String pass = txtPassword.getText().toString();
        String fname = txtFirstName.getText().toString().trim();
        String lname = txtLastName.getText().toString().trim();
        User newUser = new User(login, pass, getSelectedUserType(), fname, lname);
        Secretary secretary = new Secretary(newUser, getSelectedFaculty());

        Call<Secretary> call = jsonRequests.postSecretary(Constants.getToken(), secretary);

        call.enqueue(new Callback<Secretary>() {
            @Override
            public void onResponse(Call<Secretary> call, Response<Secretary> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddUserActivity.this, "Userul deja exista!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body() == null) {
                    Toast.makeText(AddUserActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                    return;
                }
                Secretary postResponse = response.body();

                String content = "Utilizator adaugat!\n";
                content += "Nume utilizator: " + postResponse.getUser().getLogin() + "\n";
                content += "Nume: " + postResponse.getUser().getName() + "\n";
                content += "Facultate: " + postResponse.getFaculty().getName() + "\n";
                content += "Functie: " + postResponse.getUser().getUserType().getType() + "\n\n";
                Toast.makeText(AddUserActivity.this, content, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Secretary> call, Throwable t) {
                Toast.makeText(AddUserActivity.this, "Requestul a esuat!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void postStudent() {

        if (!validateStudentRequest())
            return;

        long no = Long.parseLong(txtSIN.getText().toString());
        String pass = txtCNP.getText().toString();
        String login = txtFirstName.getText().toString().toLowerCase() + "." + txtLastName.getText().toString().toLowerCase().trim();
        String fname = txtFirstName.getText().toString().trim();
        String lname = txtLastName.getText().toString().trim();
        User newUser = new User(login, pass, getSelectedUserType(), fname, lname);
        Student student = new Student(newUser, no, txtFatherInitial.getText().toString().charAt(0), Long.parseLong(pass), getSelectedGrupa());
        

        Call<Student> call = jsonRequests.postStudent(Constants.getToken(), student);

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(AddUserActivity.this, "Userul deja exista!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Student postResponse = response.body();

                String content = "Utilizator adaugat!\n";
                //content += "Code: " + response.code() + "\n";
                content += "SIN: " + postResponse.getSin() + "\n";
                content += "Nume: " + postResponse.getUser().getName() + "\n";
                content += "Functie: " + postResponse.getUser().getUserType().getType() + "\n\n";
                Toast.makeText(AddUserActivity.this, content, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Toast.makeText(AddUserActivity.this, "Requestul a esuat!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    Boolean validateStudentRequest() {
        if (txtSIN.getText().toString().length() == 0 ||
                txtFirstName.getText().toString().length() == 0 ||
                txtLastName.getText().toString().length() == 0 ||
                txtCNP.getText().toString().length() == 0 ||
                txtFatherInitial.getText().toString().length() == 0 ||
                getSelectedUserType().getType().length() == 0) {
            Toast.makeText(AddUserActivity.this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (txtCNP.getText().toString().length() != 13) {
            Toast.makeText(AddUserActivity.this, "CNP must be 13 digits long", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    Boolean validateUserRequest() {
        if (txtUsername.getText().toString().length() == 0 ||
                txtFirstName.getText().toString().length() == 0 ||
                txtLastName.getText().toString().length() == 0 ||
                txtPassword.getText().toString().length() == 0) {
            Toast.makeText(AddUserActivity.this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public Grupa getSelectedGrupa() {
        return (Grupa) spnGrupa.getSelectedItem();
    }

    public UserType getSelectedUserType() {
        return new UserType(spnUserType.getSelectedItem().toString());
    }

    public Faculty getSelectedFaculty() {
        return (Faculty) spnFaculty.getSelectedItem();
    }

}
