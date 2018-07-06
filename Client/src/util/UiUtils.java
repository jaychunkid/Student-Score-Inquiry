package util;

import javax.swing.*;
import java.awt.*;

public class UiUtils {

    //弹出错误信息的对话框
    public static void showErrorDialog(Component component, String errorMessage){
        JOptionPane.showMessageDialog(component, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    //弹出普通信息的对话框
    public static void showInformationDialog(Component component, String message){
        JOptionPane.showMessageDialog(component, message, null, JOptionPane.INFORMATION_MESSAGE);
    }

    //弹出确认信息的对话框
    public static boolean showConfirmDialog(Component component, String message){
        return JOptionPane.showConfirmDialog(component, message, "Confirm", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
    }

    //鼠标图标设置为忙等状态
    public static void setBusyCursor(Component component){
        component.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    }

    //鼠标图标恢复
    public static void resetCursor(Component component){
        component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

}
