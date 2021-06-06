package com.example.class_manager;

public class TeachersDb {
    private String Slno;
    private String course;
    private String teacherName;
    private String roomNo;
    private String phNo;
    private String mail;

    public String getCourse() {
        return course;
    }

    public String getSlno() {
        return Slno;
    }

    public void setSlno(String slno) {
        Slno = slno;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public TeachersDb() {

    }
}
