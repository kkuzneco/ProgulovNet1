package com.progulov.progulovnet.adapter;
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




public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {


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
        holder.bind(listOfSubjects.get(position));
    }

    @Override
    public int getItemCount() {
        return listOfSubjects.size();
    }



    // Предоставляет прямую ссылку на каждый View-компонент
    // Используется для кэширования View-компонентов и последующего быстрого доступа к ним
    class SubjectViewHolder extends RecyclerView.ViewHolder {
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
            department = (TextView) itemView.findViewById(R.id.subject_depatrment);


        }
    }

}

