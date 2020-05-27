package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderBookKey implements Serializable {
    private static final long serialVersionUID = -1982361728362813131L;
    @Column(name = "bookid")
    private Integer bookid;

    @Column(name = "orderid")
    private Integer orderid;

    public OrderBookKey() {
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OrderBookKey)) {
            return false;
        }
        OrderBookKey o = (OrderBookKey) obj;
        return o.getBookid().equals(o.getBookid()) && o.getOrderid().equals(o.getOrderid());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getOrderid() != null ? this.orderid.hashCode() : 0);
        return hash;
    }

}