package com.example.user.todolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.todolist.R;
import com.example.user.todolist.adapter.TasksRecyclerAdapter;
import com.example.user.todolist.model.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final int REQUEST_CODE_ADD_TASK = 100;
    private static final int REQUEST_CODE_EDIT_TASK = 101;

    public RecyclerView mTasksRV;
    public TasksRecyclerAdapter mTaskListAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTasksRV = findViewById(R.id.rv_tasks_list);
        mTaskListAdapter = new TasksRecyclerAdapter(this);
        mLayoutManager = new LinearLayoutManager(this);
        mTasksRV.setAdapter(mTaskListAdapter);
        mTasksRV.setLayoutManager(mLayoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, AddEditItemActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_TASK);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_ADD_TASK:
                if (resultCode == RESULT_OK) {
                    Task task = data.getParcelableExtra(AddEditItemActivity.KEY_ADDED_TASK);
                    mTaskListAdapter = new TasksRecyclerAdapter(this);
                    mTaskListAdapter.addOrUpdateTask(task);
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
