package Cons.jdk8;

/**
 * Created by Hebin on 2018/5/16.
 */
public interface DefaultInterface {
    String init(String a, String b);
    default String get(){
        return "tree";
    }
}
