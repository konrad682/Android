package com.example.konrad.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.konrad.todolist.Task.AddTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity{
    final Context context = this;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<AddTask> addTaskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        String writtenTasks = prefs.getString("tasks", null);
       if(writtenTasks != null) {
           GsonBuilder builder = new GsonBuilder();
           Gson gson = builder.create();

           addTaskList = gson.fromJson(writtenTasks, new TypeToken<List<AddTask>>() {
           }.getType());

           mRecyclerView = findViewById(R.id.currencies_list);
           mRecyclerView.setHasFixedSize(true);
           mLayoutManager = new LinearLayoutManager(this);
           mRecyclerView.setLayoutManager(mLayoutManager);
           mRecyclerView.setAdapter(new MyAdapter(addTaskList, mRecyclerView,context));

       }
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddTaskActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }
  /*  @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Delete"){
            Toast.makeText(getApplicationContext(),"Delete",Toast.LENGTH_LONG).show();
            long positionToDelete = ((AdapterView.AdapterContextMenuInfo)item.getMenuInfo()).id;
            addTaskList.remove(positionToDelete);
        }
        else if(item.getTitle()=="Edit"){
            Toast.makeText(getApplicationContext(),"sending sms code",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }*/

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                AddTask task = new AddTask();
                task.name = data.getStringExtra("name");
                task.description = data.getStringExtra("desc");
                task.data = data.getStringExtra("data");

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                addTaskList.add(task);
                String serializedTasks = gson.toJson(addTaskList);

                SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("tasks", serializedTasks);
                editor.apply();

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(new MyAdapter(addTaskList, mRecyclerView,context));

            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
