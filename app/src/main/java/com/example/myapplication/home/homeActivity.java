package com.example.myapplication.home;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.ChapterAdapter;
import com.example.myapplication.R;
import com.example.myapplication.model.Chapter;

import java.util.ArrayList;
import java.util.List;

public class homeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ChapterAdapter chapterAdapter;
    private List<Chapter> chapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.RecyclerView);
        chapterList = getChapterList();
        chapterAdapter = new ChapterAdapter(chapterList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chapterAdapter);

    }
    private List<Chapter> getChapterList() {
        List<Chapter> list = new ArrayList<>();
        list.add(new Chapter("Chương 1: Giới thiệu"));
        list.add(new Chapter("Chương 2: Lập trình Android"));
        list.add(new Chapter("Chương 3: RecyclerView"));
        list.add(new Chapter("Chương 5: Xử lý tập tin, lưu trạng thái ứng dụng"));
        list.add(new Chapter("Chương 6: Xử Lý Đa Tiến Trình Và Dịch Vụ Trong Android"));
        list.add(new Chapter("Chương 7: NETWORKING APIs VÀ MULTIMEDIA APIs TRONG LẬP TRÌNH ANDROID"));
        return list;
    }


}