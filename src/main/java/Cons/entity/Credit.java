package Cons.entity;

import java.io.Serializable;
import java.util.Optional;

public class Credit implements Serializable {

    private static final long serialVersionUID = -5649509538893231525L;

    private String userId;

    private Double balance = 0.0;

    private String creditLine ;

    private User user;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(String creditLine) {
        this.creditLine = creditLine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void main(String[] args) {
        System.out.println(get());
    }

    private static String get(){
        Credit credit=new Credit();
        credit.setUserId("safa");
        return Optional.of(credit).map(c -> c.getUser())
                .map(u -> u.getUsername())
                .map(n -> n.toUpperCase()).orElse("123456");
    }
}