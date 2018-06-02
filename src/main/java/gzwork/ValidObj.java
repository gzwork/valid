package gzwork;

import gzwork.common.ValidMsg;
import gzwork.reflect.ReflectUtils;
import gzwork.util.GzUtils;

import java.util.Map;

/**
 * 校验对象公用方法
 *
 * @author Gzwork
 */
public class ValidObj {

    private ValidObj() {

    }

    /**
     * 校验指定对象
     *
     * @param object 需要校验的对象
     * @param weak   如果开启弱校验,遇到非法字段则会停止后续的校验，提高性能
     * @return 校验结果集
     */
    public static ValidMsg valid(Object object, boolean weak) {
        if (GzUtils.isEmpry(object)) {
            return new ValidMsg(false, null, "校验对象为空", null);
        }
        if (ReflectUtils.isEnableGzValid(object)) {
            return new ValidMsg(true, null, "对象未启用校验", ReflectUtils.getObjClassName(object));
        }
        Map<String, String> result = ReflectUtils.validObj(object, weak);
        if (result.isEmpty()) {
            return new ValidMsg(true, result, "对象通过校验", ReflectUtils.getObjClassName(object));
        }
        return new ValidMsg(false, result, "对象未通过校验", ReflectUtils.getObjClassName(object));
    }

}
