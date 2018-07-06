package view.inquiry;

import model.Score;
import util.Constant;
import view.BaseView;

import java.util.List;

//查询界面接口
public interface InquiryView extends BaseView<InquiryView.Controller> {

    //更新界面成绩数据
    void updateData(List<Score> scoreList);

    interface Controller{

        //查询按钮监听
        void onSearchButtonClick(String searchData, Constant type);

    }

}
