package com.example.user.todolist.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.todolist.R;

public class TaskItemViewHolder extends RecyclerView.ViewHolder {

    public TextView mItemTitle, mItemDescription, mItemDate;
    public TaskItemViewHolder(View itemView) {
        super(itemView);

        mItemTitle = itemView.findViewById(R.id.task_item_rv_title);
        mItemDescription = itemView.findViewById(R.id.task_item_rv_description);
        mItemDate = itemView.findViewById(R.id.task_item_rv_date);
    }
}
