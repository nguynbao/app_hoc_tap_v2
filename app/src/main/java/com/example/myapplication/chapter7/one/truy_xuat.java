package com.example.myapplication.chapter7.one;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class truy_xuat extends AppCompatActivity {
    private static final String TAG = "truy_xuat";
    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts/1";

    private TextView tvHttpURLConnection, tvOkHttp, tvRetrofit, tvVolley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_truy_xuat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvHttpURLConnection = findViewById(R.id.tvHttpURLConnection);
        tvOkHttp = findViewById(R.id.tvOkHttp);
        tvRetrofit = findViewById(R.id.tvRetrofit);
        tvVolley = findViewById(R.id.tvVolley);

        // 1️⃣ HttpURLConnection
        new Thread(this::useHttpURLConnection).start();

        // 2️⃣ OkHttp
        useOkHttp();

        // 3️⃣ Retrofit
        useRetrofit();

        // 4️⃣ Volley
        useVolley();
    }

    // 1️⃣ HttpURLConnection
    private void useHttpURLConnection() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            connection.disconnect();
            runOnUiThread(() -> tvHttpURLConnection.setText("HttpURLConnection:\n" + result));
        } catch (Exception e) {
            e.printStackTrace();
            runOnUiThread(() -> tvHttpURLConnection.setText("HttpURLConnection Error: " + e.getMessage()));
        }
    }

    // 2️⃣ OkHttp
    private void useOkHttp() {
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder().url(API_URL).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, java.io.IOException e) {
                runOnUiThread(() -> tvOkHttp.setText("OkHttp Error: " + e.getMessage()));
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) {
                if (response.isSuccessful()) {
                    try (ResponseBody body = response.body()) {
                        String result = body.string();
                        runOnUiThread(() -> tvOkHttp.setText("OkHttp:\n" + result));
                    } catch (Exception e) {
                        runOnUiThread(() -> tvOkHttp.setText("OkHttp Parse Error: " + e.getMessage()));
                    }
                }
            }
        });
    }

    // 3️⃣ Retrofit
    interface ApiService {
        @GET("api/p/79?depth=2")
        retrofit2.Call<String> getDistricts();
    }

    private void useRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://provinces.open-api.vn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        service.getDistricts().enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    runOnUiThread(() -> tvRetrofit.setText("Retrofit:\n" + response.body()));
                }
            }

            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {
                runOnUiThread(() -> tvRetrofit.setText("Retrofit Error: " + t.getMessage()));
            }
        });
    }

    // 4️⃣ Volley
    private void useVolley() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, API_URL,
                response -> tvVolley.setText("Volley:\n" + response),
                error -> tvVolley.setText("Volley Error: " + error.getMessage())
        );
        queue.add(stringRequest);
    }
}
