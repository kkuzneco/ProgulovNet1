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
import com.progulov.progulovnet.StudentModel;
import com.progulov.progulovnet.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.progulov.progulovnet.R.layout.cardview;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

   public boolean[] selects = {false, false, false,false,false,false,false,false,false,false,false,false, false};
    public int setItems[] = new int[13];
    private List<StudentModel> listOfStudents = new ArrayList<>();
    int pos = 0;

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
                .inflate(cardview, parent, false);
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
    class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            Name = (TextView) itemView.findViewById(R.id.subject_name);
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int positionIndex = getAdapterPosition();
           if(selects[positionIndex] )
            selects[positionIndex] = false ;
           else selects[positionIndex]=true;
            Log.d("selectItem", String.valueOf(selects[positionIndex]));
            if(cv.isSelected()){
                // Log.d("selectItem", "remove selection");
                cv.setCardBackgroundColor(Color.WHITE);
                cv.setSelected(false);
            }
            else{
                cv.setCardBackgroundColor(Color.parseColor("#cef2cb"));
                cv.setSelected(true);
            }
        }

    }

}
