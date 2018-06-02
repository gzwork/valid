package gzwork.common;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 校验信息返回结果
 *
 * @author Gzwork
 */
public class ValidMsg {
    /**
     * 是否通过验证
     */
    private boolean isValid;
    /**
     * 检验错误字段及错误信息
     */
    private Map<String, String> validMsg;
    /**
     * 附加信息
     */
    private String otherMsg;
    /**
     * 校验对象class名字
     */
    private String className;

    public ValidMsg(boolean isValid, Map<String, String> validMsg, String otherMsg, String className) {
        this.isValid = isValid;
        this.validMsg = validMsg;
        this.otherMsg = otherMsg;
        this.className = className;
    }

    public boolean getValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Map<String, String> getValidMsg() {
        return validMsg;
    }

    public void setValidMsg(Map<String, String> validMsg) {
        this.validMsg = validMsg;
    }

    public String getOtherMsg() {
        return otherMsg;
    }

    public void setOtherMsg(String otherMsg) {
        this.otherMsg = otherMsg;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "ValidMsg{" +
                "isValid=" + isValid +
                ", validMsg=" + validMsg +
                ", otherMsg='" + otherMsg + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
