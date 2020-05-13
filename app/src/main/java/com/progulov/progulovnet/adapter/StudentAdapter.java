package com.progulov.progulovnet.adapter;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.progulov.progulovnet.StudentModel;
import com.progulov.progulovnet.LecturerModel;
import com.progulov.progulovnet.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private SparseBooleanArray selectedItems;
    private List<StudentModel> listOfStudents = new ArrayList<>();

    public void setItems(Collection<StudentModel> students) {
        listOfStudents.addAll(students);
        //   notifyDataSetChanged();
    }

    public void clearItems() {
        listOfStudents.clear();
        //  notifyDataSetChanged();
    }

    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new StudentAdapter.StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentAdapter.StudentViewHolder holder, int position) {

        // holder.cv.setSelected(selectedItems.get(position, false));
        holder.bind(listOfStudents.get(position));
    }

    @Override
    public int getItemCount() {
        return listOfStudents.size();
    }



    // Предоставляет прямую ссылку на каждый View-компонент
    // Используется для кэширования View-компонентов и последующего быстрого доступа к ним
    class StudentViewHolder extends RecyclerView.ViewHolder {
        // Ваш ViewHolder должен содержать переменные для всех
        // View-компонентов, которым вы хотите задавать какие-либо свойства
        // в процессе работы пользователя со списком
        CardView cv;
        TextView Name;
        TextView department;


        public void bind(StudentModel student) {
            Name.setText(student.name);
            department.setText("");

        }


        // Мы также создали конструктор, который принимает на вход View-компонент строкИ
        // и ищет все дочерние компоненты

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            Name = (TextView) itemView.findViewById(R.id.subject_name);
            department = (TextView) itemView.findViewById(R.id.subject_depatrment);


        }
    }

}
