package com.example.user.todolist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.user.todolist.R;
import com.example.user.todolist.model.Task;

import java.util.Calendar;

public class AddEditItemFragment extends Fragment {

    private static final String ARG_TASK = "arg.task";

    private Task mTask = new Task();
    private Button mSaveEditBtn;
    private EditText mItemTitleEditText, mItemDescEditText;
    private TextView mItemDateTextView, mItemTimeTextView, mItemPriorityCount;
    private CheckBox mItemReminderCheckBox, mItemRepeatCheckBox;
    private ImageView mMinusPriorityImageView, mPlusPriorityImageView;
    private RadioGroup mRepeatRadioGroup;
    private int mPriorityCount = 0;
    private boolean mReminder = false, mRepeat = false;
    Calendar myCalendar = Calendar.getInstance();

    public IOnActionListener mIOnActionListener;


    public static AddEditItemFragment newInstance(Task task) {
        AddEditItemFragment mAddEditItemFragment = new AddEditItemFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_TASK, task);
        mAddEditItemFragment.setArguments(args);

        return mAddEditItemFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("Add/Edit Item");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_item, container, false);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init(view);
        if (savedInstanceState == null){
            mSaveEditBtn.setText("Save");
        }
        else{
            mSaveEditBtn.setText("Edit");

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    public void init(View view){
        mSaveEditBtn = (Button) view.findViewById(R.id.item_save_edit_btn);
        mItemTitleEditText = (EditText) view.findViewById(R.id.save_edit_item_title);
        mItemDescEditText = (EditText)view.findViewById(R.id.save_edit_item_description);
        mItemDateTextView = (TextView) view.findViewById(R.id.save_edit_item_date);
        mItemTimeTextView = (TextView) view.findViewById(R.id.save_edit_item_time);
        mItemReminderCheckBox = (CheckBox) view.findViewById(R.id.save_edit_item_reminder);
        mItemRepeatCheckBox = (CheckBox) view.findViewById(R.id.save_edit_item_repeat);
        mItemPriorityCount = (TextView) view.findViewById(R.id.save_edit_item_priority_count);
        mMinusPriorityImageView = (ImageView) view.findViewById(R.id.save_edit_item_counter_minus_iv);
        mPlusPriorityImageView = (ImageView) view.findViewById(R.id.save_edit_item_counter_plus_iv);
        mRepeatRadioGroup = (RadioGroup) view.findViewById(R.id.save_edit_item_repeat_radio_group);


        mSaveEditBtn.setText("Save");

        mSaveEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSaveEditBtn.getText() == "Edit")
                    mSaveEditBtn.setText("Save");
                else{
                    setInfo();
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
                if (((CheckBox) v).isChecked()) {
                    mRepeat = true;
                    mRepeatRadioGroup.setVisibility(View.VISIBLE);
                }else
                {
                    mRepeat = false;
                    mRepeatRadioGroup.setVisibility(View.GONE);
                }
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
        initData();
    }

    private void initData() {
        Bundle args = getArguments();
        if (args != null && args.containsKey(ARG_TASK) && args.getParcelable(ARG_TASK) != null) {
            mTask = args.getParcelable(ARG_TASK);
            mItemTitleEditText.setText(mTask.getTitle());
            mItemDescEditText.setText(mTask.getDescription());
            mItemReminderCheckBox.setChecked(mTask.getReminder());
            mItemRepeatCheckBox.setChecked(mTask.getRepeat());
            mRepeatRadioGroup.check(mTask.getRepeatType());
            /*mItemPriorityCount.setText(mTask.getPriority());*/
        } else {
            mTask = new Task();
        }
    }


    public void getInfo (Task task){
        mItemTitleEditText.setText(task.getTitle());
        mItemDescEditText.setText(task.getDescription());
        mItemReminderCheckBox.setChecked(task.getReminder());
        mItemRepeatCheckBox.setChecked(task.getRepeat());
        mRepeatRadioGroup.check(task.getRepeatType());
        mItemPriorityCount.setText(task.getPriority());
    }

    public void setInfo (){
        mTask.setTitle(mItemTitleEditText.getText().toString());
        mTask.setDescription(mItemDescEditText.getText().toString());
        mTask.setDate((myCalendar.getTime()));
        mTask.setReminder(mReminder);
        mTask.setRepeat(mRepeat);
        if (mItemRepeatCheckBox.isChecked()) {
            mTask.setRepeatType(mRepeatRadioGroup.getCheckedRadioButtonId());
        }
        mTask.setPriority(mPriorityCount);

        if (mIOnActionListener != null) {
            mIOnActionListener.onTaskSaved(mTask);
            Log.v("OOOO", "oooooo = " + mTask.getTitle());
        }
        getActivity().onBackPressed();
    }



    public void setmIOnActionListener(IOnActionListener mIOnActionListener) {
        this.mIOnActionListener = mIOnActionListener;
    }

    public interface IOnActionListener {
        void onTaskSaved(Task task);
        void onTaskRemoved(Task task);
    }
}
