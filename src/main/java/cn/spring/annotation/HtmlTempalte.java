package cn.spring.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * Created by Administrator on 2017/9/15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface HtmlTempalte {

    String name() default "null";

}
