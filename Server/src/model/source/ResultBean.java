package model.source;

import java.io.Serializable;

//数据集封装
public class ResultBean<T> implements Serializable {

    public final static int ERROR_OK = 200;
    public final static int ERROR_DATABASE = 300;
    public final static int ERROR_NETWORK = 400;

    private int errorCode;     //获取数据错误码
    private T data;            //获取到的数据(错误码不为ERROR_OK时data值不确定)

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
