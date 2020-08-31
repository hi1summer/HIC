package tsinghua.hic.pojo.po;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the producer database table.
 * 
 */
@Entity
@NamedQuery(name="Producer.findAll", query="SELECT p FROM Producer p")
public class Producer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String producerid;

	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="producer_desc")
	private String producerDesc;

	@Column(name="producer_name")
	private String producerName;

	@Temporal(TemporalType.DATE)
	@Column(name="update_time")
	private Date updateTime;

	public Producer() {
	}

	public String getProducerid() {
		return this.producerid;
	}

	public void setProducerid(String producerid) {
		this.producerid = producerid;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}