package Cons.annotation;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/9/15.
 */
public class Test {
    public static void main(String[] args) {
        get(Human.class);
    }


    public static void get(Class<?> clazz){
        Field[] fields= clazz.getDeclaredFields();
        try {
            Object obj= clazz.newInstance();
            for (int i = 0; i <fields.length ; i++) {
                fields[i].setAccessible(true);
                fields[i].set(obj,String.valueOf(i));
            }
           Human human= (Human) obj;
            System.out.println(human.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    };
}
