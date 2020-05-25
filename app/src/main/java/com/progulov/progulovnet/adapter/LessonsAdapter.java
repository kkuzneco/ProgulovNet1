package com.progulov.progulovnet.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.progulov.progulovnet.R;

import com.progulov.progulovnet.data.LessonModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static com.progulov.progulovnet.R.layout.lessoncardview;
public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.LessonsViewHolder>  {
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
                .inflate(R.layout.lessoncardview, parent, false);
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
        TextView Name;



        public void bind(StudentModel student) {
            Name.setText(student.name);

        }


        // Мы также создали конструктор, который принимает на вход View-компонент строкИ
        // и ищет все дочерние компоненты

        public LessonsViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            Name = (TextView) itemView.findViewById(R.id.subject_name);

            cv.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
        }

    }

}
