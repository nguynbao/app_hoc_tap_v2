package com.example.myapplication.chapter6.five;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.model.City;
import com.example.myapplication.net.ProvinceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class city_activity extends AppCompatActivity {
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_city);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spinner = findViewById(R.id.city);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://provinces.open-api.vn/").addConverterFactory(GsonConverterFactory.create()).build();
        ProvinceApi api = retrofit.create(ProvinceApi.class);

        api.getProvinces().enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<City> provinces = response.body();
                    List<String> provinceNames = new ArrayList<>();
                    for (City p : provinces) {
                        provinceNames.add(p.getName());
                    }
                    // Tạo adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(city_activity.this,
                            android.R.layout.simple_spinner_item, provinceNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                } else {
                    Toast.makeText(city_activity.this, "Không lấy được dữ liệu tỉnh thành", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Toast.makeText(city_activity.this, "Lỗi khi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}