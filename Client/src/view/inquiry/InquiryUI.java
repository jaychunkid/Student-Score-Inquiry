package view.inquiry;

import model.Score;
import util.Constant;
import util.UiUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

//查询界面实现
public class InquiryUI extends JFrame implements InquiryView {

    private InquiryView.Controller controller;
    private DefaultTableModel tableModel;

    public InquiryUI(InquiryView.Controller controller){
        super();
        this.controller = controller;
        initView();
    }

    private void initView(){
        setTitle("学生成绩查询");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //主面板
        Box panel = Box.createVerticalBox();
        add(panel);

        //查询信息类型选择按钮
        JRadioButton button1 = new JRadioButton("学生id", true);
        JRadioButton button2 = new JRadioButton("课程id");

        //查找信息输入框与标签
        Box inputBox = Box.createHorizontalBox();
        JTextField textFieldInfo = new JTextField();
        textFieldInfo.setPreferredSize(new Dimension(50, 20));
        JButton searchButton = new JButton("查找");
        searchButton.addActionListener(e -> {
            if(button1.isSelected()){
                controller.onSearchButtonClick(textFieldInfo.getText(), Constant.SEARCH_TYPE_STUDENT_ID);
            } else if(button2.isSelected()){
                controller.onSearchButtonClick(textFieldInfo.getText(), Constant.SEARCH_TYPE_CLASS_ID);
            } else {
                UiUtils.showErrorDialog(InquiryUI.this, "请选择查找方式");
            }
        });
        inputBox.add(Box.createHorizontalStrut(5));
        inputBox.add(textFieldInfo);
        inputBox.add(Box.createHorizontalStrut(3));
        inputBox.add(searchButton);
        inputBox.add(Box.createHorizontalStrut(5));
        panel.add(Box.createVerticalStrut(5));
        panel.add(inputBox);

        //查询信息类型单选按钮组
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(button1);
        buttonGroup.add(button2);
        Box selectBox = Box.createHorizontalBox();
        selectBox.add(button1);
        selectBox.add(button2);
        panel.add(selectBox);

        //学生成绩数据表
        Object[] columnName = {"学生id", "学生姓名", "课程id", "课程名", "学生成绩"};
        Object[][] rowData = new Object[1][5];
        tableModel = new DefaultTableModel(rowData, columnName);
        JTable data = new JTable(tableModel);
        //设置表头各列不允许拖动
        data.getTableHeader().setReorderingAllowed(false);
        //设置表格数据按列排序功能
        RowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
        data.setRowSorter(rowSorter);
        //设置表格不可编辑
        data.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(data);
        panel.add(Box.createVerticalStrut(10));
        panel.add(scrollPane);
    }

    @Override
    public void updateData(List<Score> scoreList) {
        //清除表中原有数据
        int i = tableModel.getRowCount();
        while (i > 0) {
            tableModel.removeRow(--i);
        }
        tableModel.setRowCount(0);
        //向表中插入新的数据
        int j = 0;
        for (Score score : scoreList) {
            Object[] data = new Object[]{score.getStudentId(), score.getStudentName(),
                    score.getClassId(), score.getClassName(), score.getScore()};
            tableModel.insertRow(j++, data);
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
