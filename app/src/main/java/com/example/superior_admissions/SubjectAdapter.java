package com.example.superior_admissions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    Context context;
    ArrayList<SubjectModel> list;

    public SubjectAdapter(Context context, ArrayList<SubjectModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.subjects_list,parent,false);
        return new SubjectViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        holder.sbjName.setText(list.get(position).getSbjName());
        holder.sbjCredit.setText(String.valueOf(list.get(position).getSbjCredit()));
        holder.sbjDesc.setText(list.get(position).getSbjDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder{

        TextView sbjName, sbjCredit, sbjDesc;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);

            sbjName = itemView.findViewById(R.id.subjectName);
            sbjCredit = itemView.findViewById(R.id.creditHours);
            sbjDesc = itemView.findViewById(R.id.subjectDesc);

        }
    }
}
