package abu.sadat.yasin.dto;

import java.io.Serializable;

/**
 * @author Abu Sadat Mohammed Yasin, Dhaka, Bangladesh
 */
public class BaseData implements Serializable {

    private int code; 
    
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseData{" + "code=" + code + ", msg=" + msg + '}';
    }
       
}
