/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

/**
 *
 * @author ASUS
 */
public class Mark {
    private Student studentid;
    private Subject subjectid;
    private int smalltest1;
    private int bigtest1;
    private int finalltest1;
    private int smalltest2;
    private int bigtest2;
    private int finalltest2;
    
    public Mark() {
    }

    public Student getStudentid() {
        return studentid;
    }

    public void setStudentid(Student studentid) {
        this.studentid = studentid;
    }

    public Subject getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Subject subjectid) {
        this.subjectid = subjectid;
    }

    public int getSmalltest1() {
        return smalltest1;
    }

    public void setSmalltest1(int smalltest1) {
        this.smalltest1 = smalltest1;
    }

    public int getBigtest1() {
        return bigtest1;
    }

    public void setBigtest1(int bigtest1) {
        this.bigtest1 = bigtest1;
    }

    public int getFinalltest1() {
        return finalltest1;
    }

    public void setFinalltest1(int finalltest1) {
        this.finalltest1 = finalltest1;
    }

    public int getSmalltest2() {
        return smalltest2;
    }

    public void setSmalltest2(int smalltest2) {
        this.smalltest2 = smalltest2;
    }

    public int getBigtest2() {
        return bigtest2;
    }

    public void setBigtest2(int bigtest2) {
        this.bigtest2 = bigtest2;
    }

    public int getFinalltest2() {
        return finalltest2;
    }

    public void setFinalltest2(int finalltest2) {
        this.finalltest2 = finalltest2;
    }

    

    
}
