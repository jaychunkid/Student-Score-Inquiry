package view.connect;

import javax.swing.*;
import java.awt.*;

//连接界面实现
public class ConnectUI extends JFrame implements ConnectView {

    private ConnectView.Controller controller;

    public ConnectUI(ConnectView.Controller controller) {
        super();
        this.controller = controller;
        initView();
    }

    private void initView(){
        setTitle("连接服务器");
        setSize(250, 140);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //主面板
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        panel.setLayout(layout);
        add(panel);

        //host输入框与标签
        JLabel labelHost = new JLabel("IP:");
        JTextField textFieldHost = new JTextField();
        labelHost.setPreferredSize(new Dimension(60, 20));
        textFieldHost.setPreferredSize(new Dimension(230, 20));

        //port输入框与标签
        JLabel labelPort = new JLabel("端口:");
        JTextField textFieldPort = new JTextField();
        labelPort.setPreferredSize(new Dimension(55, 20));
        textFieldPort.setPreferredSize(new Dimension(230, 20));

        //启动连接的按钮
        JButton button = new JButton("连接");
        button.addActionListener(e -> {
            //获取用户输入的host、port
            String host = textFieldHost.getText();
            String port = textFieldPort.getText();
            controller.onConnectButtonClick(host, port);
        });

        //组界面布局设置
        GroupLayout.ParallelGroup hGroup1 = layout.createParallelGroup().addComponent(labelHost).addComponent(labelPort);
        GroupLayout.ParallelGroup hGroup2 = layout.createParallelGroup().addComponent(textFieldHost).addComponent(textFieldPort);
        GroupLayout.SequentialGroup hGroup3 = layout.createSequentialGroup().addGroup(hGroup1).addGroup(hGroup2);
        GroupLayout.ParallelGroup hGroup = layout.createParallelGroup().addGroup(hGroup3).addComponent(button, GroupLayout.Alignment.CENTER);
        layout.setHorizontalGroup(hGroup);

        GroupLayout.ParallelGroup vGroup1 = layout.createParallelGroup().addComponent(labelHost).addComponent(textFieldHost);
        GroupLayout.ParallelGroup vGroup2 = layout.createParallelGroup().addComponent(labelPort).addComponent(textFieldPort);
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup().addGroup(vGroup1).addGroup(vGroup2).addComponent(button);
        layout.setVerticalGroup(vGroup);

    }

    @Override
    public void setController(ConnectView.Controller controller){
        this.controller = controller;
    }

}
