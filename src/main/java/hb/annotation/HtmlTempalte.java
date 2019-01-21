package hb.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/9/15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface HtmlTempalte {

    String name() default "null";

}
