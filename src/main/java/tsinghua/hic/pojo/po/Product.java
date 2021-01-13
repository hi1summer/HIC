package tsinghua.hic.pojo.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The persistent class for the product database table.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {

    private static final long serialVersionUID = 544804407821105902L;

    @Id
    private String id;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "expire_time")
    private String expireTime;

    @Column(name = "firstvalidate_time")
    private String firstvalidateTime;

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "validate_count")
    private int validateCount;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getFirstvalidateTime() {
        return firstvalidateTime;
    }

    public void setFirstvalidateTime(String firstvalidateTime) {
        this.firstvalidateTime = firstvalidateTime;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getValidateCount() {
        return validateCount;
    }

    public void setValidateCount(int validateCount) {
        this.validateCount = validateCount;
    }

}