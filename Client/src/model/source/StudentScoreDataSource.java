package model.source;

import model.Score;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

//RMI远程服务器接口源文件副本
public interface StudentScoreDataSource extends Remote {

    //利用学生id查找该学生的所有成绩
    ResultBean<List<Score>> requestScoreByStudentId(String studentId) throws RemoteException;

    //利用课程id查找该课程所有学生的成绩
    ResultBean<List<Score>> requestScoreByClassId(String classId) throws RemoteException;

}