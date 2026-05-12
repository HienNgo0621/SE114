package com.example.myapplication;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/api/v1/employees")
    Call<List<Employee>> getAll();

    @GET("/api/v1/employee/{id}")
    Call<Employee> getEmployeeById(@Path("id") String id);

    @POST("/api/v1/create")
    Call<Employee> createEmployee(@Body Employee employee);

    @PUT("/api/v1/update/{id}")
    Call<Employee> updateEmployee(@Path("id") String id, @Body Employee employee);

    @DELETE("/api/v1/delete/{id}")
    Call<Void> deleteEmployee(@Path("id") String id);
}