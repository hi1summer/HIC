package tsinghua.hic.pojo.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The persistent class for the productinfo database table.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@NamedQuery(name = "Productinfo.findAll", query = "SELECT p FROM Productinfo p")
public class Productinfo implements Serializable {

    private static final long serialVersionUID = -5718510996893076068L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String productinfoid;

    @Lob
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_time")
    @JsonIgnore
    private Date createTime;

    @Column(name = "express_desc")
    private String expressDesc;

    @Column(name = "express_name")
    private String expressName;

    private String expressid;

    @Column(name = "info_type")
    private short infoType;

    @Column(name = "producer_desc")
    private String producerDesc;

    @Column(name = "producer_name")
    private String producerName;

    private String producerid;

    @Column(name = "transaction_desc")
    private String transactionDesc;

    @Column(name = "transaction_name")
    private String transactionName;

    private String transactionid;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_time")
    @JsonIgnore
    private Date updateTime;

    // bi-directional many-to-one association to Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gid")
    @JsonIgnore
    private Product product;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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

    public String getExpressid() {
        return expressid;
    }

    public void setExpressid(String expressid) {
        this.expressid = expressid;
    }

    public short getInfoType() {
        return infoType;
    }

    public void setInfoType(short infoType) {
        this.infoType = infoType;
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

    public String getProducerid() {
        return producerid;
    }

    public void setProducerid(String producerid) {
        this.producerid = producerid;
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

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}