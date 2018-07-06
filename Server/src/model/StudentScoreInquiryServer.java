package model;

import model.source.StudentScoreDataSource;
import model.source.StudentScoreRepository;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//封装服务器的创建操作
public class StudentScoreInquiryServer {

    public StudentScoreInquiryServer(int port){
        init(port);
    }

    //传入需要绑定的端口号, 在该端口上初始化服务器
    private void init(int port){
        try{
            //在端口上激活RMI注册表
            startRegistry(port);
            //生成对象stub
            StudentScoreDataSource stub = (StudentScoreDataSource) UnicastRemoteObject
                    .exportObject(new StudentScoreRepository(), port);
            //将stub的引用存储到注册表中
            String registryUrl = "rmi://localhost:" + port + "/StudentScoreDataSource";
            Naming.bind(registryUrl, stub);
            System.out.println("Server registered.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void startRegistry(int port) throws RemoteException {
        try{
            //尝试获取端口上的RMI注册表
            Registry registry = LocateRegistry.getRegistry(port);
            registry.list();     //若RMI注册表不存在则会抛出RemoteException异常
        } catch (RemoteException e){
            System.out.println("RMI registry can't be at port " + port);
            LocateRegistry.createRegistry(port);     //在端口上创建RMI注册表
            System.out.println("RMI registry created at port " + port);
        }
    }

}
