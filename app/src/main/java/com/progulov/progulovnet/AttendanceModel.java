package com.progulov.progulovnet;

public class AttendanceModel {

    public String id;
    public String studentName;
    public String lessonId;
    public boolean status;


   AttendanceModel(String attendanceId, String lessonId, String studentName,  boolean status) {
       this.id = attendanceId;
        this.studentName = studentName;
        this.lessonId = lessonId;
        this.status = status;

    }
    public String getid() {
        return id;
    }
}
