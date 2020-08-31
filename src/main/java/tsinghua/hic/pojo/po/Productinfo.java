package tsinghua.hic.pojo.po;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the productinfo database table.
 * 
 */
@Entity
@NamedQuery(name="Productinfo.findAll", query="SELECT p FROM Productinfo p")
public class Productinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String productinfoid;

	@Lob
	private String content;

	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="express_desc")
	private String expressDesc;

	@Column(name="express_name")
	private String expressName;

	private String expressid;

	@Column(name="info_type")
	private short infoType;

	@Column(name="producer_desc")
	private String producerDesc;

	@Column(name="producer_name")
	private String producerName;

	private String producerid;

	@Column(name="transaction_desc")
	private String transactionDesc;

	@Column(name="transaction_name")
	private String transactionName;

	private String transactionid;

	@Temporal(TemporalType.DATE)
	@Column(name="update_time")
	private Date updateTime;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="gid")
	private Product product;

	public Productinfo() {
	}

	public String getProductinfoid() {
		return this.productinfoid;
	}

	public void setProductinfoid(String productinfoid) {
		this.productinfoid = productinfoid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getExpressDesc() {
		return this.expressDesc;
	}

	public void setExpressDesc(String expressDesc) {
		this.expressDesc = expressDesc;
	}

	public String getExpressName() {
		return this.expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressid() {
		return this.expressid;
	}

	public void setExpressid(String expressid) {
		this.expressid = expressid;
	}

	public short getInfoType() {
		return this.infoType;
	}

	public void setInfoType(short infoType) {
		this.infoType = infoType;
	}

	public String getProducerDesc() {
		return this.producerDesc;
	}

	public void setProducerDesc(String producerDesc) {
		this.producerDesc = producerDesc;
	}

	public String getProducerName() {
		return this.producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getProducerid() {
		return this.producerid;
	}

	public void setProducerid(String producerid) {
		this.producerid = producerid;
	}

	public String getTransactionDesc() {
		return this.transactionDesc;
	}

	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}

	public String getTransactionName() {
		return this.transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	public String getTransactionid() {
		return this.transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}