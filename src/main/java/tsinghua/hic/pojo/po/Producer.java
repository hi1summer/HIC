package tsinghua.hic.pojo.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The persistent class for the producer database table.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@NamedQuery(name = "Producer.findAll", query = "SELECT p FROM Producer p")
public class Producer implements Serializable {

    private static final long serialVersionUID = 7556686711416902175L;

    @Id
    private String producerid;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_time")
    @JsonIgnore
    private Date createTime;

    @Column(name = "producer_desc")
    private String producerDesc;

    @Column(name = "producer_name")
    private String producerName;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_time")
    @JsonIgnore
    private Date updateTime;

    public Producer() {
    }

    public String getProducerid() {
        return producerid;
    }

    public void setProducerid(String producerid) {
        this.producerid = producerid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProducerDesc() {
        return producerDesc;
    }

    public void setProducerDesc(String producerDesc) {
        this.producerDesc = producerDesc;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}