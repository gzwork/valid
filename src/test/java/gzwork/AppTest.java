package gzwork;

import static org.junit.Assert.assertTrue;

import gzwork.common.ValidMsg;
import gzwork.entity.User;
import gzwork.util.GzUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void valid() {
        User user = new User();
        ValidMsg validMsg = ValidObj.valid(user, true);
        Assert.assertFalse("校验失败", validMsg.getValid());
    }

    @Test
    public void validByRegex() {
        Assert.assertFalse("正则表达式错误", GzUtils.validByRegex("^\\S+$", ""));
    }

    @Test
    public void validObj() {
        User user = new User();
        user.setUserName("gzwork");
        user.setUserEmail("462994587@qq.com");
        ValidMsg validMsg = ValidObj.valid(user, false);
        Assert.assertTrue("代码逻辑出错",validMsg.getValid());
    }
}
