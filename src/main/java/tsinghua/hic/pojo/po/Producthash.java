package tsinghua.hic.pojo.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The persistent class for the producthash database table.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@NamedQuery(name = "Producthash.findAll", query = "SELECT p FROM Producthash p")
public class Producthash implements Serializable {

    private static final long serialVersionUID = -3213362254571359317L;

    @Id
    private String gid;

    private String hash;

    // bi-directional one-to-one association to Product
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gid")
    @JsonIgnore
    private Product product;

    public Producthash() {
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}