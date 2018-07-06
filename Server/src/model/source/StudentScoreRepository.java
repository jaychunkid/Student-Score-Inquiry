package model.source;

import database.JDBCManager;
import model.Score;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//学生成绩查询接口实现
public class StudentScoreRepository implements StudentScoreDataSource {

    @Override
    public ResultBean<List<Score>> requestScoreByStudentId(String studentId) {
        ResultSet resultSet = JDBCManager.getInstance().query("SELECT score.sid,lesson.lid,sname,lname,score " +
                        "FROM student,lesson,score WHERE score.sid=? AND score.sid=student.sid " +
                        "AND score.lid=lesson.lid ORDER BY lid",
                studentId);
        return convertToScoreList(resultSet);
    }

    @Override
    public ResultBean<List<Score>> requestScoreByClassId(String classId) {
        ResultSet resultSet = JDBCManager.getInstance().query("SELECT score.sid,lesson.lid,sname,lname,score " +
                        "FROM student,lesson,score WHERE score.lid=? AND score.sid=student.sid " +
                        "AND score.lid=lesson.lid ORDER BY sid",
                classId);
        return convertToScoreList(resultSet);
    }

    //提取JDBC查询结果转换为List<Score>类型的结果集
    private ResultBean<List<Score>> convertToScoreList(ResultSet resultSet) {
        ResultBean<List<Score>> resultBean = new ResultBean<>();
        if(resultSet != null) {
            try {
                List<Score> resultList = new ArrayList<>();
                while (resultSet.next()) {
                    Score score = new Score();
                    score.setClassId(resultSet.getString("lid"));
                    score.setClassName(resultSet.getString("lname"));
                    score.setStudentId(resultSet.getString("sid"));
                    score.setStudentName(resultSet.getString("sname"));
                    score.setScore(Integer.parseInt(resultSet.getString("score")));
                    resultList.add(score);
                }
                resultBean.setErrorCode(ResultBean.ERROR_OK);
                resultBean.setData(resultList);
            } catch (SQLException e){
                resultBean.setErrorCode(ResultBean.ERROR_DATABASE);
            }
        } else {
            resultBean.setErrorCode(ResultBean.ERROR_OK);
        }
        return resultBean;
    }

}
