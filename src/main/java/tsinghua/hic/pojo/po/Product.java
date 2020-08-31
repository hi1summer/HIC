package tsinghua.hic.pojo.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author summer
 * @Email gonick@163.com
 * @Date 2020年8月31日
 * @Desc
 */
@Entity
public class Product {
    @Id
    private String gid;
    private Date create_time;
    private Date update_time;
    private String product_desc;
    private String product_name;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    @Override
    public String toString() {
        return "Product [gid=" + gid + ", create_time=" + create_time
                + ", update_time=" + update_time + ", product_desc="
                + product_desc + ", product_name=" + product_name + "]";
    }

}
