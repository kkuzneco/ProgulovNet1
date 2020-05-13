package com.progulov.progulovnet.adapter;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

import com.progulov.progulovnet.Lecturer;
import com.progulov.progulovnet.R;
import com.progulov.progulovnet.LecturerModel;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import 	android.util.SparseBooleanArray;

public class LecturerAdapter extends RecyclerView.Adapter<LecturerAdapter.LecturerViewHolder> {

    private SparseBooleanArray selectedItems;
    private List<LecturerModel> listOfLecturers = new ArrayList<>();

    public void setItems(Collection<LecturerModel> lecturers) {
        listOfLecturers.addAll(lecturers);
        //   notifyDataSetChanged();
    }

    public void clearItems() {
        listOfLecturers.clear();
        //  notifyDataSetChanged();
    }

    @Override
    public LecturerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new LecturerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LecturerViewHolder holder, int position) {

        // holder.cv.setSelected(selectedItems.get(position, false));
        holder.bind(listOfLecturers.get(position));
    }

    @Override
    public int getItemCount() {
        return listOfLecturers.size();
    }



    // Предоставляет прямую ссылку на каждый View-компонент
    // Используется для кэширования View-компонентов и последующего быстрого доступа к ним
    class LecturerViewHolder extends RecyclerView.ViewHolder {
        // Ваш ViewHolder должен содержать переменные для всех
        // View-компонентов, которым вы хотите задавать какие-либо свойства
        // в процессе работы пользователя со списком
        CardView cv;
        TextView Name;
        TextView department;


        public void bind(LecturerModel lecturer) {
            Name.setText(lecturer.name);
            department.setText(lecturer.department);

        }


        // Мы также создали конструктор, который принимает на вход View-компонент строкИ
        // и ищет все дочерние компоненты

        public LecturerViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            Name = (TextView) itemView.findViewById(R.id.subject_name);
            department = (TextView) itemView.findViewById(R.id.subject_depatrment);


        }
    }

}
