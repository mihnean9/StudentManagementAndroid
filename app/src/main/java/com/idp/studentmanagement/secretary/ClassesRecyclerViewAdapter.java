package com.idp.studentmanagement.secretary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idp.studentmanagement.R;
import com.idp.studentmanagement.objects.Clasa;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ClassesRecyclerViewAdapter extends RecyclerView.Adapter<ClassesViewHolder> {

    private List<Clasa> classList;
    private OnClassClickListener onClassClickListener;

    public ClassesRecyclerViewAdapter(List<Clasa> data, OnClassClickListener onClassClickListener) {
        this.classList = data;
        this.onClassClickListener = onClassClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ClassesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_list_item, parent, false);
        return new ClassesViewHolder(view, onClassClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ClassesViewHolder holder, int position) {
        holder.setData(classList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    public interface OnClassClickListener {
        void onClassClick(int position);
    }
}
