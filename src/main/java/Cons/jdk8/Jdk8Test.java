package Cons.jdk8;

import Cons.dto.User;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by Hebin on 2018/5/16.
 */
public class Jdk8Test {
    private static String out(DefaultInterface defaultInterface){
        return null;
    }

    public static void main(String[] args) {
        out((a,b)-> a+b);

        //Predicate接口  返回true和false
        Predicate<String> predicate1 = (str) -> str.length()>1;
        System.out.println(predicate1.test("11"));;
        System.out.println(predicate1.negate().test("11"));;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        System.out.println(predicate1.and(isNotEmpty).test("1"));;
        System.out.println(predicate1.or(isNotEmpty).test("1"));;

        //Function接口  简写单参数方法
        Function<String, Integer> toInteger = Integer::valueOf;
        // Integer::valueOf 执行完后执行 String::valueOf  入参String 返回值String
        // backToString 有 toInteger 的入参，String::valueOf 的返回值
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");     // "123"

        //Supplier 接口  返回一个任意范型的值  无参
        Supplier<User> personSupplier = User::new;
        personSupplier.get();   // new Person

        //Consumer 接口
        Consumer<User> greeter = (u) -> System.out.println("Hello, " + u.getName());
        greeter.accept(new User("Luke", "Skywalker"));




    }
}
