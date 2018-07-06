package database;

import java.sql.*;

//封装通过JDBC对MySQL数据库的操作
public class JDBCManager {

    private final static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";     //MySQL驱动名
    private final static String URL = "jdbc:mysql://localhost:3306/school?useSSL=false&&serverTimezone=CST";
    private final static String USERNAME = "";     //这里需要填入数据库的用户名
    private final static String PASSWORD = "";     //这里需要填入数据库的密码

    private static JDBCManager INSTANCE = new JDBCManager();

    private Connection connection;

    //单例，线程安全
    public static JDBCManager getInstance(){
        return INSTANCE;
    }

    private JDBCManager(){
        init();
    }

    //初始化与数据库建立连接
    private void init(){
        try {
            //装载驱动
            Class.forName(DRIVER_CLASS_NAME);
            //连接数据库
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connecting success");
        } catch (Exception e){
            connection = null;
            e.printStackTrace();
        }
    }

    //执行查询语句
    public ResultSet query(String sql, Object... params){
        if(connection != null) {
            if (sql != null && !"".equals(sql) && !"".equals(sql.trim())) {
                ResultSet resultSet = null;
                if(params.length > 0) {
                    //传入参数包含sql语句参数, 则用参数替换sql语句中的占位符
                    try {
                        PreparedStatement statement = connection.prepareStatement(sql);
                        for(int i = 0; i < params.length; ++i){
                            Object object = params[i];
                            statement.setObject(i + 1, object);
                        }
                        resultSet = statement.executeQuery();
                        System.out.println("Execute query " + statement.toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    //传入参数只有sql语句, 则直接执行
                    try{
                        Statement statement = connection.createStatement();
                        resultSet = statement.executeQuery(sql);
                        System.out.println("Execute query " + sql);
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                return resultSet;
            } else {
                System.out.println("Sql statement should not be empty");
                return null;
            }
        } else {
            System.out.println("Failed to connect database");
            return null;
        }
    }

}
