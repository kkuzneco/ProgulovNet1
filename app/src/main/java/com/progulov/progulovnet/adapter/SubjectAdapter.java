package com.progulov.progulovnet.adapter;

import android.graphics.Color;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import com.progulov.progulovnet.R;
import com.progulov.progulovnet.SubjectModel;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import 	android.util.SparseBooleanArray;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private SparseBooleanArray selectedItems;
    private List<SubjectModel> listOfSubjects = new ArrayList<>();

    public void setItems(Collection<SubjectModel> subjects) {
        listOfSubjects.addAll(subjects);
     //   notifyDataSetChanged();
    }

    public void clearItems() {
        listOfSubjects.clear();
      //  notifyDataSetChanged();
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int position) {
        final CardView cardView = holder.cv;
       // holder.Name.setText(listOfSubjects.get(position));
        cardView.setTag(listOfSubjects.get(position));
       // holder.cv.setSelected(selectedItems.get(position, false));
        holder.bind(listOfSubjects.get(position));
    }

    @Override
    public int getItemCount() {
        return listOfSubjects.size();
    }

    // Предоставляет прямую ссылку на каждый View-компонент
    // Используется для кэширования View-компонентов и последующего быстрого доступа к ним
    class SubjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Ваш ViewHolder должен содержать переменные для всех
        // View-компонентов, которым вы хотите задавать какие-либо свойства
        // в процессе работы пользователя со списком
        CardView cv;
        TextView Name;
        TextView department;

        public void bind(SubjectModel subject) {
            Name.setText(subject.name);
            department.setText(subject.department);
        }

        // Мы также создали конструктор, который принимает на вход View-компонент строкИ
        // и ищет все дочерние компоненты

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            Name = (TextView) itemView.findViewById(R.id.subject_name);
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(cv.isSelected()){
               // Log.d("selectItem", "remove selection");
                cv.setCardBackgroundColor(Color.WHITE);
                cv.setSelected(false);
            }else{
               // Log.d("selectItem", "set selection");
                cv.setCardBackgroundColor(Color.LTGRAY);
                cv.setSelected(true);
            }
        }
    }
}

