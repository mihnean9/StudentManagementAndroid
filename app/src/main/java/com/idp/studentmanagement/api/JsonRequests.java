package com.idp.studentmanagement.api;

import com.idp.studentmanagement.objects.Admin;
import com.idp.studentmanagement.objects.Clasa;
import com.idp.studentmanagement.objects.Faculty;
import com.idp.studentmanagement.objects.Grade;
import com.idp.studentmanagement.objects.Grupa;
import com.idp.studentmanagement.objects.Login;
import com.idp.studentmanagement.objects.Major;
import com.idp.studentmanagement.objects.Secretary;
import com.idp.studentmanagement.objects.Student;
import com.idp.studentmanagement.objects.Seria;
import com.idp.studentmanagement.objects.Token;
import com.idp.studentmanagement.objects.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonRequests {

    @POST("login")
    Call<Token> postLogin(@Body Login login);

    @GET("users/login/{username}")
    Call<User> getUser(@Path(value = "username", encoded = true) String username,
                       @Header("Authorization") String token);

    @PUT("users/{id}")
    Call<User> putUser(@Path(value = "id") int id,
                       @Header("Authorization") String token,
                       @Body User user);

    @GET("students/username/{username}")
    Call<Student> getStudent(@Path(value = "username", encoded = true) String username,
                            @Header("Authorization") String token);

    @PUT("students/{id}")
    Call<Student> putStudent(@Path(value = "id") int id,
                             @Header("Authorization") String token,
                             @Body Student student);

    @GET("users")
    Call<List<User>> getUsers(@Header("Authorization") String token);

    @GET("faculties")
    Call<List<Faculty>> getFaculties(@Header("Authorization") String token);

    @POST("faculties")
    Call<Faculty> postFaculty(@Header("Authorization") String token, @Body Faculty faculty);

    @PUT("faculties/{id}")
    Call<Faculty> putFaculty(@Path(value = "id") int id,
                             @Header("Authorization") String token,
                             @Body Faculty faculty);

    @GET("majors")
    Call<List<Major>> getMajors(@Header("Authorization") String token);

    @POST("majors")
    Call<Major> postMajor(@Header("Authorization") String token, @Body Major major);

    @PUT("majors/{id}")
    Call<Major> putMajor(@Path(value = "id") int id,
                         @Header("Authorization") String token,
                         @Body Major major);

    @GET("serii")
    Call<List<Seria>> getSerii(@Header("Authorization") String token);

    @POST("serii")
    Call<Seria> postSerie(@Header("Authorization") String token, @Body Seria seria);

    @PUT("serii/{id}")
    Call<Seria> putSerie(@Path(value = "id") int id,
                         @Header("Authorization") String token,
                         @Body Seria seria);

    @GET("grupe")
    Call<List<Grupa>> getGrupe(@Header("Authorization") String token);

    @POST("grupe")
    Call<Grupa> postGrupa(@Header("Authorization") String token, @Body Grupa grupa);

    @PUT("grupe/{id}")
    Call<Grupa> putGrupa(@Path(value = "id") int id,
                         @Header("Authorization") String token,
                         @Body Grupa grupa);

    @GET("students")
    Call<List<Student>> getStudents(@Header("Authorization") String token);

    @POST("students")
    Call<Student> postStudent(@Header("Authorization") String token, @Body Student student);

    @GET("admins")
    Call<List<Admin>> getAdmins(@Header("Authorization") String token);

    @POST("admins")
    Call<Admin> postAdmin(@Header("Authorization") String token, @Body Admin admin);

    @GET("secretaries")
    Call<List<Secretary>> getSecretaries(@Header("Authorization") String token);

    @POST("secretaries")
    Call<Secretary> postSecretary(@Header("Authorization") String token, @Body Secretary secretary);

    @GET("classes")
    Call<List<Clasa>> getClasses(@Header("Authorization") String token);

    @GET("grades/student/{sin}")
    Call<List<Grade>> getGrades(@Path(value = "sin") long sin,
                                @Header("Authorization") String token);

    @POST("grades")
    Call<Grade> postGrade(@Header("Authorization") String token,
                          @Body Grade grade);

}
