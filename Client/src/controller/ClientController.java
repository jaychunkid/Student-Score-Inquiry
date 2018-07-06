package controller;

import model.Score;
import model.StudentScoreInquiryClient;
import model.source.ResultBean;
import util.Constant;
import util.UiUtils;
import view.connect.ConnectUI;
import view.connect.ConnectView;
import view.inquiry.InquiryUI;
import view.inquiry.InquiryView;

import javax.swing.*;
import java.util.List;

//客户端控制层
public class ClientController implements ConnectView.Controller, InquiryView.Controller {

    private JFrame layout;                        //当前客户端界面
    private StudentScoreInquiryClient client;     //客户端Model

    public ClientController(){
        //初始化Model层
        client = new StudentScoreInquiryClient();
        //事件派发线程EDT中初始化界面
        SwingUtilities.invokeLater(() -> {
            layout = new ConnectUI(this);
            layout.setVisible(true);
        });
    }

    @Override
    public void onConnectButtonClick(String host, String port) {
        if(host != null && !"".equals(host) && port != null && !"".equals(port)){
            UiUtils.setBusyCursor(layout);
            //利用SwingWorker实现任务和UI响应的线程分离
            new SwingWorker<Boolean, Void>(){

                @Override
                protected Boolean doInBackground() {
                    //doInBackground()方法在子线程中执行
                    //连接服务器
                    return client.connect(host, Integer.parseInt(port));
                }

                @Override
                protected void done() {
                    //done()方法在EDT中执行
                    boolean result = false;
                    try {
                        //获取连接结果
                        result = get();
                    } catch (Exception e){
                        //获取结果发生异常，视为连接失败处理
                    } finally {
                        UiUtils.resetCursor(layout);
                        if(result){
                            //连接成功，切换到查询界面
                            layout.dispose();
                            layout = new InquiryUI(ClientController.this);
                            layout.setVisible(true);
                        } else {
                            UiUtils.showErrorDialog(layout, "连接服务器失败");
                        }
                    }
                }

            }.execute();
        } else {
            UiUtils.showErrorDialog(layout, "IP和端口不能为空");
        }
    }

    @Override
    public void onSearchButtonClick(String searchData, Constant type) {
        if(searchData != null && !"".equals(searchData)){
            UiUtils.setBusyCursor(layout);
            new SwingWorker<ResultBean<List<Score>>, Void>(){

                @Override
                protected ResultBean<List<Score>> doInBackground() {
                    //发起查询
                    if(type == Constant.SEARCH_TYPE_STUDENT_ID){
                        return client.queryByStudentId(searchData);
                    } else if(type == Constant.SEARCH_TYPE_CLASS_ID){
                        return client.queryByClassId(searchData);
                    } else {
                        return null;
                    }
                }

                @Override
                protected void done() {
                    UiUtils.resetCursor(layout);
                    //获取查询结果
                    try {
                        ResultBean<List<Score>> resultBean = get();
                        if(resultBean != null && resultBean.getErrorCode() == ResultBean.ERROR_OK){
                            List<Score> scoreList = resultBean.getData();
                            if(scoreList != null && scoreList.size() > 0){
                                //查询到数据则更新
                                ((InquiryView) layout).updateData(scoreList);
                            } else {
                                UiUtils.showInformationDialog(layout, "没有查到到相关信息");
                            }
                        } else {
                            UiUtils.showErrorDialog(layout, "从服务器获取数据失败");
                        }
                    } catch (Exception e){
                        //e.printStackTrace();
                        UiUtils.showErrorDialog(layout, "从服务器获取数据失败");
                    }
                }
            }.execute();
        } else {
            UiUtils.showErrorDialog(layout, "查找内容不能为空");
        }
    }

}
