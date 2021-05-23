package com.idp.studentmanagement.secretary;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idp.studentmanagement.R;

import org.jetbrains.annotations.NotNull;

public class ClassesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView txtvClass;
    ClassesRecyclerViewAdapter.OnClassClickListener onClassClickListener;

    public ClassesViewHolder(@NonNull @NotNull View itemView, ClassesRecyclerViewAdapter.OnClassClickListener onClassClickListener) {
        super(itemView);

        txtvClass = itemView.findViewById(R.id.txtv_class);
        this.onClassClickListener = onClassClickListener;
        itemView.setOnClickListener(this);
    }

    void setData(String className) {
        txtvClass.setText(className);
    }

    @Override
    public void onClick(View view) {
        onClassClickListener.onClassClick(getAdapterPosition());
    }
}