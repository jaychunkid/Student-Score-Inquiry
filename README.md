## 学生成绩查询系统
基于 Java RM I实现的学生成绩查询程序，支持通过 学生id 或者 课程id 查询程序。数据库使用 mysql，程序使用 JDBC 的方式连接数据库。
### Java RMI
服务端定义远程接口继承`Remote`接口，实现接口并编译得到 Stub 文件和 Skeleton 文件，之后在注册表上进行注册后，绑定端口运行。
其中，接口传入的参数和返回值都必须是可序列化的，接口中声明的方法都必须抛出`RemoteExceptino`异常。
客户端需要获取远程接口的拷贝和 Stub 文件，即可通过远程接口调用服务器的方法。
* `Java 1.2`以及之后的版本中，服务器端不再需要 Skeleton 文件。
* `Java 1.5`以及之后的版本中，客户端不建议直接获取静态的 Stub 文件，而是在注册表中动态获取。
### Server
* `JDBCManager`：封装JDBC操作
* `StudentScoreDataSource`：远程方法接口
* `StudentScoreRepository`：远程方法接口实现
* `StudentScoreInquiryServer`：封装服务器创建操作，使用`Registry`对象在端口上创建RMI注册表，
通过`UnicastRemoteObject`对象的`exportObject`方法生成 stub 对象，使用`Naming`类的`bind`方法将 stub 对象存储到注册表中。
### Client
* `StudentScoreInquiryClient`：封装客户端操作。其中，连接服务器时使用`Naming`类的`lookup`方法可以获取对应IP和端口上的远程接口。
* `ClientController`：利用`SwingUtilities.invokeLate`方法以及`SwingWorker`对象实现界面更新与后台操作的线程分离。
### 程序测试
* 服务器运行需要 mysql 数据库的连接器jar包，并且需要在`JDBCManager`对象中填写用户名及密码
* 数据库初始化实例 <br><br> ![](https://github.com/jaychunkid/Student-Score-Inquiry/raw/master/screenshot/database.PNG)

  
