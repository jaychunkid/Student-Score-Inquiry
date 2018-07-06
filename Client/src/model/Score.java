package model;

import java.io.Serializable;

//封装成绩信息
public class Score implements Serializable {

    private String classId;         //课程id
    private String studentId;       //学生id
    private String studentName;     //学生姓名
    private String className;       //课程名
    private int score;              //成绩

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
