package gzwork.annotation;

import java.lang.annotation.*;

/**
 * 启用校验的公共注解
 * 需要校验的对象,应该都加上此注解,包括内置注解
 * @author Gzwork
 * @version v1.0 2018年5月30日
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GzValid {

}
