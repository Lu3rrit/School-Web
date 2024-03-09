/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Student {
    private String studentID;
    private ClassStudent classID;
    private String firstname;
    private String lastname;
    private boolean gender;
    private Date dob;
    private String address;
    private String photo;
    ArrayList<Mark> marks = new ArrayList<>();

    public ArrayList<Mark> getMarks() {
        return marks;
    }

    public void setMarks(ArrayList<Mark> marks) {
        this.marks = marks;
    }
    
    public Student() {
    }

    public Student(String studentID, ClassStudent classID, String firstname, String lastname, boolean gender, Date dob, String address, String photo) {
        this.studentID = studentID;
        this.classID = classID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.photo = photo;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public ClassStudent getClassID() {
        return classID;
    }

    public void setClassID(ClassStudent classID) {
        this.classID = classID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    
    
}
