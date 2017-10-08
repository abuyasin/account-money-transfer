package abu.sadat.yasin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 * @author Abu Sadat Mohammed Yasin, Dhaka, Bangladesh
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountData extends BaseData {

    @NotNull
    @Length(min = 8, max = 20)
    private String accountNo; // ACCOUNTNO;

    @NotNull
    @Length(min = 1, max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "fullName Must be alpha numeric")
    private String fullName; // FULLNAME;

    @NotNull
    private double balance; //BALANCE;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountData{" + "accountNo=" + accountNo + ", fullName=" + fullName + ", balance=" + balance + '}';
    }

}
