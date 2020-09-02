package tsinghua.hic.pojo.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The persistent class for the transactions database table.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "transactions")
@NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 6109701414432072333L;

    @Id
    private String transactionid;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_time")
    @JsonIgnore
    private Date createTime;

    @Column(name = "transaction_desc")
    private String transactionDesc;

    @Column(name = "transaction_name")
    private String transactionName;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_time")
    @JsonIgnore
    private Date updateTime;

    public Transaction() {
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}