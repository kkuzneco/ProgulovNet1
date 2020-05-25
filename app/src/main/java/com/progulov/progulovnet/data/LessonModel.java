package com.progulov.progulovnet.data;

public class LessonModel {
    public String subject_name;
    public String lecturer_name;
    public String dateTime;

    // public String department;
    public String id;


    public LessonModel(String id, String subject, String lecturer, String dateTime) {
        this.id = id;
        this.subject_name = subject;
        this.lecturer_name = lecturer;
        this.dateTime = dateTime;



    }
    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }


}
