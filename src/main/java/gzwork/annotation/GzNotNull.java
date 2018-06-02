package gzwork.annotation;

import java.lang.annotation.*;

/**
 * 默认提供规则
 * 校验非空字段
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
//默认提供的规则应该加上该注解
@GzValid
public @interface GzNotNull {
    /**
     * 内置正则表达式
     */
    String regex="^\\S+$";

    /**
     * 字段为空的提示信息
     *
     * @return 默认:字段不能为空
     */
    String nullMsg() default " 字段不能为空";

    /**
     * 验证失败的提示信息
     * @return 默认:字段不合法
     */
    String errorMsg() default " 字段不合法";

}
