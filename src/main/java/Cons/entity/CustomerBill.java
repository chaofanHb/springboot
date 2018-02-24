package Cons.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by Administrator on 2017/9/6.
 */
@Entity(value = "customerBill", noClassnameStored = true)
public class CustomerBill {
    @Id
    private String id;
    private String customerCompanyName; //公司名
    private String customerId; //打款账户
    private double paymentAmount; //收款金额
    private double addAmount; //加款金额
    private double turnover; //成交额
    private String totalMonth; //统计月份 年月
    private double correMoney; //修正金额
    private double settleAccount; //结算金额
    private double monthStartAmount; //月初余额
    private double monthEndAmount; //月末余额
    private double billAmount; //开票金额
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerCompanyName() {
        return customerCompanyName;
    }

    public void setCustomerCompanyName(String customerCompanyName) {
        this.customerCompanyName = customerCompanyName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public double getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(double addAmount) {
        this.addAmount = addAmount;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public String getTotalMonth() {
        return totalMonth;
    }

    public void setTotalMonth(String totalMonth) {
        this.totalMonth = totalMonth;
    }

    public double getCorreMoney() {
        return correMoney;
    }

    public void setCorreMoney(double correMoney) {
        this.correMoney = correMoney;
    }

    public double getSettleAccount() {
        return settleAccount;
    }

    public void setSettleAccount(double settleAccount) {
        this.settleAccount = settleAccount;
    }

    public double getMonthStartAmount() {
        return monthStartAmount;
    }

    public void setMonthStartAmount(double monthStartAmount) {
        this.monthStartAmount = monthStartAmount;
    }

    public double getMonthEndAmount() {
        return monthEndAmount;
    }

    public void setMonthEndAmount(double monthEndAmount) {
        this.monthEndAmount = monthEndAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    @Override
    public String toString() {
        return "CustomerBill{" +
                "id='" + id + '\'' +
                ", customerCompanyName='" + customerCompanyName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", addAmount=" + addAmount +
                ", turnover=" + turnover +
                ", totalMonth='" + totalMonth + '\'' +
                ", correMoney=" + correMoney +
                ", settleAccount=" + settleAccount +
                ", monthStartAmount=" + monthStartAmount +
                ", monthEndAmount=" + monthEndAmount +
                ", billAmount=" + billAmount +
                ", remark='" + remark + '\'' +
                '}';
    }
}
