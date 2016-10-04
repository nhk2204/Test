package jp.techacademy.kanta.nakayama.test;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by nhk2204 on 2016/10/04.
 */
public class Task extends RealmObject implements Serializable{
    @PrimaryKey
    private int id;
    private String taskName1;
    private String taskName2;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getTaskName1(){
        return taskName1;
    }
    public void setTaskName1(String taskName1){
        this.taskName1=taskName1;
    }

    public String getTaskName2(){
        return taskName2;
    }
    public void setTaskName2(String taskName2){
        this.taskName2=taskName2;
    }
}
