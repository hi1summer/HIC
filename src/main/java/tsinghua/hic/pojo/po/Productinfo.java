package tsinghua.hic.pojo.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The persistent class for the productinfo database table.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@NamedQuery(name = "Productinfo.findAll", query = "SELECT p FROM Productinfo p")
public class Productinfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4953903797728213645L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String productinfoid;

    private String content;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "express_desc")
    private String expressDesc;

    @Column(name = "express_name")
    private String expressName;

    @Column(name = "gid")
    private String gid;

    // 1 producer 2 express 3 transaction 4 verifyuser
    @Column(name = "info_type")
    private short infoType;

    private String loc;

    @Column(name = "producer_desc")
    private String producerDesc;

    @Column(name = "producer_name")
    private String producerName;

    @Column(name = "transaction_desc")
    private String transactionDesc;

    @Column(name = "transaction_name")
    private String transactionName;

    @Column(name = "update_time")
    private String updateTime;

    @Column(name = "verifyuser_desc")
    private String verifyuserDesc;

    @Column(name = "verifyuser_name")
    private String verifyuserName;

    public Productinfo() {
    }

    public String getProductinfoid() {
        return productinfoid;
    }

    public void setProductinfoid(String productinfoid) {
        this.productinfoid = productinfoid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExpressDesc() {
        return expressDesc;
    }

    public void setExpressDesc(String expressDesc) {
        this.expressDesc = expressDesc;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public short getInfoType() {
        return infoType;
    }

    public void setInfoType(short infoType) {
        this.infoType = infoType;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getProducerDesc() {
        return producerDesc;
    }

    public void setProducerDesc(String producerDesc) {
        this.producerDesc = producerDesc;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getVerifyuserDesc() {
        return verifyuserDesc;
    }

    public void setVerifyuserDesc(String verifyuserDesc) {
        this.verifyuserDesc = verifyuserDesc;
    }

    public String getVerifyuserName() {
        return verifyuserName;
    }

    public void setVerifyuserName(String verifyuserName) {
        this.verifyuserName = verifyuserName;
    }

}