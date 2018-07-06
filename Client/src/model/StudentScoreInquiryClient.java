package model;

import model.source.ResultBean;
import model.source.StudentScoreDataSource;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

//封装客户端操作
public class StudentScoreInquiryClient {

    private StudentScoreDataSource dataSource;     //数据来源

    //根据传入的IP和端口号连接服务器，返回连接是否成功
    public boolean connect(String host, int port){
        boolean result = true;
        try {
            String registryUrl = "rmi://" + host + ":" + port + "/StudentScoreDataSource";
            dataSource = (StudentScoreDataSource) Naming.lookup(registryUrl);
        } catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    //查询学生的所有成绩
    public ResultBean<List<Score>> queryByStudentId(String studentId) {
        ResultBean<List<Score>> resultBean = null;
        try {
            resultBean = dataSource.requestScoreByStudentId(studentId);
        } catch (RemoteException e){
            e.printStackTrace();
            resultBean = new ResultBean<>();
            resultBean.setErrorCode(ResultBean.ERROR_NETWORK);
        }
        return resultBean;
    }

    //查询课程的所有成绩
    public ResultBean<List<Score>> queryByClassId(String classId) {
        ResultBean<List<Score>> resultBean = null;
        try {
            resultBean = dataSource.requestScoreByClassId(classId);
        } catch (RemoteException e){
            e.printStackTrace();
            resultBean = new ResultBean<>();
            resultBean.setErrorCode(ResultBean.ERROR_NETWORK);
        }
        return resultBean;
    }

}
