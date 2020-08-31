package tsinghua.hic.pojo.po;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the express database table.
 * 
 */
@Entity
@NamedQuery(name="Express.findAll", query="SELECT e FROM Express e")
public class Express implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String expressid;

	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="express_desc")
	private String expressDesc;

	@Column(name="express_name")
	private String expressName;

	@Temporal(TemporalType.DATE)
	@Column(name="update_time")
	private Date updateTime;

	public Express() {
	}

	public String getExpressid() {
		return this.expressid;
	}

	public void setExpressid(String expressid) {
		this.expressid = expressid;
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

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}