package tsinghua.hic.pojo.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The persistent class for the productinfohash database
 * table.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@NamedQuery(name = "Productinfohash.findAll", query = "SELECT p FROM Productinfohash p")
public class Productinfohash implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4167770213269567201L;

    @Id
    private String productinfoid;

    private String hash;

    public Productinfohash() {
    }

    public String getProductinfoid() {
        return productinfoid;
    }

    public void setProductinfoid(String productinfoid) {
        this.productinfoid = productinfoid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}