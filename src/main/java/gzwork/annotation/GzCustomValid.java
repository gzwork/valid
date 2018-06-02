package gzwork.annotation;

import java.lang.annotation.*;

/**
 * 用户自定义校验
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GzCustomValid {
    /**
     * 内置正则表达式
     */
    String regex();

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
