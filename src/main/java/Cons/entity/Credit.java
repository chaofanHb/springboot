package Cons.entity;

import java.io.Serializable;

public class Credit implements Serializable {

    private static final long serialVersionUID = -5649509538893231525L;

    private String userId;

    private Double balance = 0.0;

    private String creditLine ;

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
}