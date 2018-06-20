package com.example.user.todolist.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.todolist.R;
import com.example.user.todolist.model.Task;

import java.util.ArrayList;

public class AddEditItemActivity extends AppCompatActivity{

    private static final String TAG = "AddEditItemActivity";
    public static final String KEY_ADDED_TASK = "task";

    private Task mTask = new Task();

    private Button mSaveEditBtn;
    private EditText mItemTitleEditText, mItemDescEditText;
    private TextView mItemDateTextView, mItemPriorityCount;
    private CheckBox mItemReminderCheckBox, mItemRepeatCheckBox;
    private ImageView mMinusPriorityImageView, mPlusPriorityImageView;
    private RadioGroup mRepeatRadioGroup;
    private int mPriorityCount = 0;
    private boolean mReminder = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_item);

        mSaveEditBtn = (Button) findViewById(R.id.item_save_edit_btn);
        mItemTitleEditText = (EditText) findViewById(R.id.save_edit_item_title);
        mItemDescEditText = (EditText)findViewById(R.id.save_edit_item_description);
        mItemDateTextView = (TextView) findViewById(R.id.save_edit_item_date);
        mItemReminderCheckBox = (CheckBox) findViewById(R.id.save_edit_item_reminder);
        mItemRepeatCheckBox = (CheckBox) findViewById(R.id.save_edit_item_repeat);
        mItemPriorityCount = (TextView) findViewById(R.id.save_edit_item_priority_count);
        mMinusPriorityImageView = (ImageView) findViewById(R.id.save_edit_item_counter_minus_iv);
        mPlusPriorityImageView = (ImageView) findViewById(R.id.save_edit_item_counter_plus_iv);
        mRepeatRadioGroup = (RadioGroup) findViewById(R.id.save_edit_item_repeat_radio_group);

        mSaveEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSaveEditBtn.getText() == "Edit")
                    mSaveEditBtn.setText("Save");
                else{
                    setInfo();
                    Intent intent = new Intent();
                    intent.putExtra(KEY_ADDED_TASK, mTask);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });

        mMinusPriorityImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPriorityCount > 0){
                    mPriorityCount--;
                    mItemPriorityCount.setText(mPriorityCount + "");
                }
                else mItemPriorityCount.setText("0");
            }
        });

        mPlusPriorityImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPriorityCount < 5){
                    mPriorityCount++;
                    mItemPriorityCount.setText(mPriorityCount + "");
                }
                else  mItemPriorityCount.setText("5");
            }
        });

        mItemRepeatCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                    mRepeatRadioGroup.setVisibility(View.VISIBLE);
                else
                    mRepeatRadioGroup.setVisibility(View.GONE);
            }
        });

        mItemReminderCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                    mReminder = true;
                else
                    mReminder = false;
            }
        });

    }

    public void getInfo (Task task){
        mItemTitleEditText.setText(task.getTitle());
        mItemDescEditText.setText(task.getDescription());
    }

    public void setInfo (){
        mTask.setTitle(mItemTitleEditText.getText().toString());
        mTask.setDescription("rrrrr");
    }

}
