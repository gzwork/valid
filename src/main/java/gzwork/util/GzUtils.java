package gzwork.util;

import java.util.regex.Pattern;

/**
 * 辅助工具提供
 * @author Gzwork
 * @version v1.0 2018年5月30日
 */
public class GzUtils {

    private GzUtils(){

    }

    /**
     * 判断对象是否为空
     * @param object 待判断的对象
     * @return 校验结果
     */
    public static boolean isEmpry(Object object){
        return null==object||object.toString().isEmpty();
    }

    /**
     * 根据正在表达式匹配输入结果
     * @param regex 表达式
     * @param input 输的的结果
     * @return 是否匹配成功
     */
    public static boolean validByRegex(String regex,String input){
        return Pattern.matches(regex,input);
    }

    /**
     * 判断是否是空数组
     * @param arrays  数组
     * @return 校验结果
     */
    public static boolean isEmpryArray(Object [] arrays){
        return arrays==null||arrays.length==0;
    }
}
