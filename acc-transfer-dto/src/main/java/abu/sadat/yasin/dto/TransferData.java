package abu.sadat.yasin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * @author Abu Sadat Mohammed Yasin, Dhaka, Bangladesh
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferData extends BaseData {

    @Column(name = "TRANSACTIONID", unique = true, updatable = true)
    private String transactionId; 

    @NotNull
    private String srcAccountNo; //SRCACCOUNTNO;

    @NotNull
    private String desAccountNo; //DESACCOUNTNO;
    
    @NotNull
    private double amount;

    @NotNull
    @Length(min = 1, max = 1)
    private String type; //TYPE; 
    
    @NotNull
    private int status; //STATUS; 

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getSrcAccountNo() {
        return srcAccountNo;
    }

    public void setSrcAccountNo(String srcAccountNo) {
        this.srcAccountNo = srcAccountNo;
    }

    public String getDesAccountNo() {
        return desAccountNo;
    }

    public void setDesAccountNo(String desAccountNo) {
        this.desAccountNo = desAccountNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransferData{" + "transactionId=" + transactionId + ", srcAccountNo=" + srcAccountNo + ", desAccountNo=" + desAccountNo + ", amount=" + amount + ", type=" + type + ", status=" + status + '}';
    }
   
}
