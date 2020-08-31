package tsinghua.hic.pojo.po;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String gid;

	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="product_desc")
	private String productDesc;

	@Column(name="product_name")
	private String productName;

	@Temporal(TemporalType.DATE)
	@Column(name="update_time")
	private Date updateTime;

	//bi-directional one-to-one association to Producthash
	@OneToOne(mappedBy="product")
	private Producthash producthash;

	//bi-directional many-to-one association to Productinfo
	@OneToMany(mappedBy="product")
	private List<Productinfo> productinfos;

	public Product() {
	}

	public String getGid() {
		return this.gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProductDesc() {
		return this.productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Producthash getProducthash() {
		return this.producthash;
	}

	public void setProducthash(Producthash producthash) {
		this.producthash = producthash;
	}

	public List<Productinfo> getProductinfos() {
		return this.productinfos;
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