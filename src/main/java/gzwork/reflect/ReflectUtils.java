package gzwork.reflect;

import gzwork.annotation.GzCustomValid;
import gzwork.annotation.GzValid;
import gzwork.util.GzUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 提供反射相关的工具支持
 *
 * @author Gzwork
 * @version v1.0 2018年5月30日
 */
public class ReflectUtils {

    /**
     * 获取对象className
     *
     * @param object  对象
     * @return 对象全限定名
     */
    public static String getObjClassName(Object object) {
        return object.getClass().getName();
    }

    /**
     * 对象是否开启验证
     *
     * @param object 需要验证的对象
     * @return 是否开启验证
     */
    public static boolean isEnableGzValid(Object object) {
        Annotation annotation = object.getClass().getAnnotation(GzValid.class);
        return null == annotation;
    }

    /**
     * 校验对象合法性
     *
     * @param object 需要校验的对象
     * @param weak   如果开启弱校验,遇到非法字段则会停止后续的校验，提高性能
     * @return 字段校验结果集
     */
    public static Map<String, String> validObj(Object object, boolean weak) {
        Map<String, String> result = new HashMap<>(16);
        Field[] declaredFields = object.getClass().getDeclaredFields();
        //如果对象中没有字段则直接返回
        if (GzUtils.isEmpryArray(declaredFields)) {
            return result;
        }
        //校验对象上面的全部字段信息
        for (Field field : declaredFields) {
            result = validField(object, field, result);
            //如果开启弱校验且校验结果集不是空
            if (weak && !result.isEmpty()) {
                return result;
            }
        }
        return result;
    }

    /**
     * 校验字段合法性
     *
     * @param object 校验的对象
     * @param field  校验的字段
     * @param result 存放结果集
     * @return 校验结果
     */
    private static Map<String, String> validField(Object object, Field field, Map<String, String> result) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        field.setAccessible(true);
        //如果字段
        if (GzUtils.isEmpryArray(annotations)) {
            return result;
        }
        boolean validResult ;
        try {
            //遍历字段上面的所有注解
            Class<? extends Annotation> annotationClass;
            Method nullMsgMethod = null;
            Method errorMsgMethod = null;
            Method regexMethod;
            String regex = null;
            for (Annotation annotation : annotations) {
                annotationClass = annotation.annotationType();
                //如果是GzValid的内置注解，则解析校验规则并校验字段结果
                if (null!=annotationClass.getAnnotation(GzValid.class)) {
                    Field regexField = annotationClass.getField("regex");
                    nullMsgMethod=annotationClass.getMethod("nullMsg");
                    errorMsgMethod = annotationClass.getMethod("errorMsg");
                    regex = regexField.get(annotationClass).toString();
                } else {
                    //如果是用户自定义的校验规则
                    if (annotationClass.isAssignableFrom(GzCustomValid.class)) {
                        regexMethod = annotationClass.getMethod("regex");
                        nullMsgMethod = annotationClass.getMethod("nullMsg");
                        errorMsgMethod = annotationClass.getMethod("errorMsg");
                        regex = regexMethod.invoke(annotation).toString();
                    }
                }
                if (nullMsgMethod != null && errorMsgMethod != null && regex != null) {
                    //判空
                    if (GzUtils.isEmpry(field.get(object))) {
                        result.put(field.getName(), nullMsgMethod.invoke(annotation).toString());
                        return result;
                    }
                    //根据正则表达式校验结果
                    validResult = GzUtils.validByRegex(regex, field.get(object).toString());
                    if (!validResult) {
                        result.put(field.getName(), errorMsgMethod.invoke(annotation).toString());
                    }
                }
            }
        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
}
