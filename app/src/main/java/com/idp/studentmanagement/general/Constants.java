package com.idp.studentmanagement.general;

import android.content.Context;
import android.content.Intent;

import com.idp.studentmanagement.R;
import com.idp.studentmanagement.MainActivity;
import com.idp.studentmanagement.account.AccountActivity;
import com.idp.studentmanagement.objects.Faculty;
import com.idp.studentmanagement.objects.Grupa;
import com.idp.studentmanagement.objects.Major;
import com.idp.studentmanagement.objects.Seria;
import com.idp.studentmanagement.objects.Student;
import com.idp.studentmanagement.objects.User;
import com.idp.studentmanagement.student.StudentAccountActivity;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    private static String token;
    private static User user;
    private static Constants mInstance;
    private static List<Faculty> faculties;
    private static List<Major> majors;
    private static List<Seria> serii;
    private static List<Grupa> grupe;
    private static int background;
    public static Class<?> loginClass;
    public static String viewIntent;
    public static Student currentStudent;

    private Constants() {
        user = new User("", "", null, "", "", "", 0);
        faculties = new ArrayList<>();
        majors = new ArrayList<>();
        serii = new ArrayList<>();
        grupe = new ArrayList<>();
        currentStudent = null;
    }

    public static Constants getInstance() {
        if (mInstance == null)
            mInstance = new Constants();
        return mInstance;
    }

    public static void logout(Context context) {
        token = "";
        Intent logoutIntent = new Intent(context, MainActivity.class);
        context.startActivity(logoutIntent);
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Constants.user = user;
        switch (user.getUserType().getType()) {
            case "ADMIN":
                background = R.drawable.redbackground;
                loginClass = AccountActivity.class;
                break;

            case "SECRETARY":
                background = R.drawable.greenbackground;
                loginClass = AccountActivity.class;
                break;

            case "STUDENT":
                background = R.drawable.bluebackground;
                loginClass = StudentAccountActivity.class;
                break;
        }
    }

    public static Student getCurrentStudent() {
        return currentStudent;
    }

    public static void setCurrentStudent(Student currentStudent) {
        Constants.currentStudent = currentStudent;
    }

    public static void setToken(String jwt) {
        token = "Bearer " + jwt;
    }

    public static String getToken() {
        return token;
    }

    public static List<Faculty> getFaculties() {
        return faculties;
    }

    public static void setFaculties(List<Faculty> faculties) {
        Constants.faculties = faculties;
    }

    public static List<Major> getMajors() {
        return majors;
    }

    public static void setMajors(List<Major> majors) {
        Constants.majors = majors;
    }

    public static List<Seria> getSerii() {
        return serii;
    }

    public static void setSerii(List<Seria> serii) {
        Constants.serii = serii;
    }

    public static List<Grupa> getGrupe() {
        return grupe;
    }

    public static void setGrupe(List<Grupa> grupe) {
        Constants.grupe = grupe;
    }

    public static int getBackground() {
        return background;
    }

    public static Class<?> getLoginClass() {
        return loginClass;
    }

    public static String getViewIntent() {
        return viewIntent;
    }

    public static void setViewIntent(String viewIntent) {
        Constants.viewIntent = viewIntent;
    }

}
