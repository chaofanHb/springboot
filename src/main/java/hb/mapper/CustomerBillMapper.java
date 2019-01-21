package hb.mapper;

import hb.entity.CustomerBill;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */
@Repository
public interface CustomerBillMapper {
    @Select("select * from sys_customer_month_bill")
    @Results({@Result(property = "id",column = "id"),@Result(property = "customerCompanyName",column = "customer_company_name"),@Result(property = "customerId",column = "customer_id"),@Result(property = "paymentAmount",column = "payment_amount"),
            @Result(property = "addAmount",column = "add_amount"),@Result(property = "turnover",column = "turnover"),@Result(property = "totalMonth",column = "total_month"),@Result(property = "correMoney",column = "corre_money"),
            @Result(property = "settleAccount",column = "settle_account"),@Result(property = "monthStartAmount",column = "month_start_amount"),@Result(property = "monthEndAmount",column = "month_end_amount"),@Result(property = "billAmount",column = "bill_amount")})
    public List<CustomerBill> getAll();
}
