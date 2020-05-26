package com.progulov.progulovnet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.progulov.progulovnet.LessonModel;
import com.progulov.progulovnet.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.LessonsViewHolder>{

    public int setItems[] = new int[13];
    private List<LessonModel> listOfLessons = new ArrayList<>();
    int pos = 0;

    public void setItems(Collection<LessonModel> lessons) {
        listOfLessons.addAll(lessons);
        //   notifyDataSetChanged();
    }

    public void clearItems() {
        listOfLessons.clear();
        //  notifyDataSetChanged();
    }

    @Override
    public LessonsAdapter.LessonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new LessonsAdapter.LessonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonsViewHolder holder, int position) {
        holder.bind(listOfLessons.get(position));
    }

    @Override
    public int getItemCount() {
        return listOfLessons.size();
    }

    // Предоставляет прямую ссылку на каждый View-компонент
    // Используется для кэширования View-компонентов и последующего быстрого доступа к ним
    class LessonsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Ваш ViewHolder должен содержать переменные для всех
        // View-компонентов, которым вы хотите задавать какие-либо свойства
        // в процессе работы пользователя со списком
        CardView cv;
        TextView Subject_name;
        TextView Lecturer_name;

        public void bind(LessonModel lesson) {
            Subject_name.setText(lesson.subject_name);
            Lecturer_name.setText(lesson.lecturer_name);

        }

        // Мы также создали конструктор, который принимает на вход View-компонент строкИ
        // и ищет все дочерние компоненты

        public LessonsViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            Subject_name = (TextView) itemView.findViewById(R.id.subject_name);
            Lecturer_name =(TextView)itemView.findViewById(R.id.lecturer_name);
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }

    }
}
