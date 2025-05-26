package com.example.myapplication.adapter;

import com.example.myapplication.R;
import com.example.myapplication.model.Chapter;
import com.example.myapplication.chapter.Chapter5Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {
    private List<Chapter> chapterList;

    public ChapterAdapter(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    public static class ChapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvChapterTitle);

        }
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        Chapter chapter = chapterList.get(position);
        holder.tvTitle.setText(chapter.getTitle());
        holder.itemView.setOnClickListener(v ->  {
            if (chapter.getTitle().contains("Chương 5: Xử lý tập tin, lưu trạng thái ứng dụng")) {
                Intent intent = new Intent(v.getContext(), Chapter5Activity.class);
                v.getContext().startActivity(intent);
            }
    });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }
}
