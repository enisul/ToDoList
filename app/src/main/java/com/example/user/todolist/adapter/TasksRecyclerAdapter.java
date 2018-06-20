package com.example.user.todolist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.todolist.R;
import com.example.user.todolist.model.Task;
import com.example.user.todolist.viewHolder.TaskItemViewHolder;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class TasksRecyclerAdapter extends RecyclerView.Adapter<TaskItemViewHolder> {
    private static final String TAG = "TasksAdapter";
    public ArrayList<Task> mTasksList;

    public TasksRecyclerAdapter(Context context) {
        super();
        mTasksList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TaskItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_rv, parent, false);
        return new TaskItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskItemViewHolder holder, int position) {
        Task mTask = mTasksList.get(position);
        holder.mItemTitle.setText(mTask.getTitle());
        holder.mItemDescription.setText(mTask.getDescription());
        holder.mItemDate.setText(mTask.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return mTasksList.size();
    }

    public void addOrUpdateTask(Task task) {
        mTasksList.add(task);
        notifyDataSetChanged();
    }

}
