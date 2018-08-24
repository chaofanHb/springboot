package Cons.jdk8;

import Cons.dto.User;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by Hebin on 2018/5/16.
 */
public class Jdk8Test {
    public static void main(String[] args) {
        //function();
        //array();
        date();
        System.exit(0);
    }

    private static void date(){
        //1.Clock
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        //2. ISO-8601格式且无时区信息的日期部分
        final LocalDate date = LocalDate.now();
        final LocalDate dateFromClock = LocalDate.now(clock);

        System.out.println(date);
        System.out.println(dateFromClock);

        // ISO-8601格式且无时区信息的时间部分
        final LocalTime time = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now(clock);

        System.out.println(time);
        System.out.println(timeFromClock);

        // 3.ISO-8601格式无时区信息的日期与时间
        final LocalDateTime datetime = LocalDateTime.now();
        final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);

        System.out.println(datetime);
        System.out.println(datetimeFromClock);

        // 4.特定时区的日期/时间，
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

        System.out.println(zonedDatetime);
        System.out.println(zonedDatetimeFromClock);
        System.out.println(zonedDatetimeFromZone);

        //5.在秒与纳秒级别上的一段时间
        final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
        final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);

        final Duration duration = Duration.between(from, to);
        System.out.println("Duration in days: " + duration.toDays());
        System.out.println("Duration in hours: " + duration.toHours());
    }

    private static void array() {
        String[] strs = {"a", "b", "c"};
        List<String> list = Arrays.asList(strs);
        //List<String> out = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> out = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        out.forEach((str) -> System.out.println(str));

        out.stream().filter(str -> str.equals("A") || str.equals("C")).forEach((str) -> System.out.println(str));
    }

    private static String out(DefaultInterface defaultInterface) {
        return defaultInterface.init("he", "bin");
    }

    private static void function() {
        System.out.println(out((a, b) -> a + b));

        //Predicate接口  返回true和false
        Predicate<String> predicate1 = (str) -> str.length() > 1;
        System.out.println(predicate1.test("11"));
        System.out.println(predicate1.negate().test("11"));

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        System.out.println(predicate1.and(isNotEmpty).test("1"));
        System.out.println(predicate1.or(isNotEmpty).test("1"));

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

        String rs = Optional.ofNullable(new User())
                .map(u -> {
                    Optional<String> o = Optional.ofNullable(u.getName());
                    return o.orElse("123456");
                })
                .orElse("11111");
        System.out.println(rs);
    }

}
