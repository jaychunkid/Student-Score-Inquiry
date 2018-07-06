package view.connect;

import view.BaseView;

//连接界面接口
public interface ConnectView extends BaseView<ConnectView.Controller> {

    interface Controller{

        //连接按钮点击监听
        void onConnectButtonClick(String host, String port);

    }

}
