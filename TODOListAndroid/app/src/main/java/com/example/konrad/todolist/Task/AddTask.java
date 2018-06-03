package com.example.konrad.todolist.Task;


import java.io.Serializable;

public class AddTask implements Serializable{
    public String description;
    public String name;
    public String data = "01/01/1999";

    public AddTask(){

    }
    public AddTask(String description, String name, String data){
        this.description = description;
        this.data = data;
        this.name = name;
    }



    public String getData() {
        return data;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }


}
