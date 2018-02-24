package Cons.annotation;

/**
 * Created by Administrator on 2017/9/15.
 */
@HtmlTempalte(name = "xxx")
public class Human {
    @Fileds
    private String name;
    @Fileds
    private String dept;
    @Fileds
    private String age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
