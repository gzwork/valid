package gzwork.entity;

import gzwork.annotation.GzCustomValid;
import gzwork.annotation.GzValid;
import gzwork.annotation.GzNotNull;

@GzValid
public class User {
    @GzNotNull(nullMsg = "用户名不能为空",errorMsg = "用户名不合法")
    private  String userName;
    @GzCustomValid(regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$",errorMsg = "邮箱格式不正确",nullMsg = "请输入用户邮箱")
    private String userEmail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
