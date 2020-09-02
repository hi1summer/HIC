package tsinghua.hic.pojo.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The persistent class for the product database table.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {

    private static final long serialVersionUID = 8319595579676546991L;

    @Id
    private String gid;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_time")
    @JsonIgnore
    private Date createTime;

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "product_name")
    private String productName;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_time")
    @JsonIgnore
    private Date updateTime;

    // bi-directional one-to-one association to Producthash
    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private Producthash producthash;

    // bi-directional many-to-one association to Productinfo
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Productinfo> productinfos;

    public Product() {
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Producthash getProducthash() {
        return producthash;
    }

    public void setProducthash(Producthash producthash) {
        this.producthash = producthash;
    }

    public List<Productinfo> getProductinfos() {
        return productinfos;
    }

    public void setProductinfos(List<Productinfo> productinfos) {
        this.productinfos = productinfos;
    }

    public Productinfo addProductinfo(Productinfo productinfo) {
        getProductinfos().add(productinfo);
        productinfo.setProduct(this);

        return productinfo;
    }

    public Productinfo removeProductinfo(Productinfo productinfo) {
        getProductinfos().remove(productinfo);
        productinfo.setProduct(null);

        return productinfo;
    }

}