package tsinghua.hic.pojo.po;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the producthash database table.
 * 
 */
@Entity
@NamedQuery(name="Producthash.findAll", query="SELECT p FROM Producthash p")
public class Producthash implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String gid;

	private String hash;

	//bi-directional one-to-one association to Product
	@OneToOne
	@JoinColumn(name="gid")
	private Product product;

	public Producthash() {
	}

	public String getGid() {
		return this.gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getHash() {
		return this.hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}