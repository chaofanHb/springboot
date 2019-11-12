package cn.spring.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/9/15.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Fileds {

    String name() default "";


}
