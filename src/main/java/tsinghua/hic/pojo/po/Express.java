package tsinghua.hic.pojo.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The persistent class for the express database table.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@NamedQuery(name = "Express.findAll", query = "SELECT e FROM Express e")
public class Express implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String expressid;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "express_desc")
    private String expressDesc;

    @Column(name = "express_name")
    private String expressName;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_time")
    private Date updateTime;

    public Express() {
    }

    public String getExpressid() {
        return expressid;
    }

    public void setExpressid(String expressid) {
        this.expressid = expressid;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}