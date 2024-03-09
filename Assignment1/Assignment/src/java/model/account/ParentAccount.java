/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account;

import java.util.ArrayList;
import model.entity.Student;

/**
 *
 * @author ASUS
 */
public class ParentAccount {
    private String username;
    private String password;
    private Student studentID;
    ArrayList<ParentFeature> features = new ArrayList<>();

    public ArrayList<ParentFeature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<ParentFeature> features) {
        this.features = features;
    }
    
    public ParentAccount() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student getStudentID() {
        return studentID;
    }

    public void setStudentID(Student studentID) {
        this.studentID = studentID;
    }
    

    
}
