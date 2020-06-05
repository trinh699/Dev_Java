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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderBookKey other = (OrderBookKey) obj;
        if (bookid == null) {
            if (other.bookid != null)
                return false;
        } else if (!bookid.equals(other.bookid))
            return false;
        if (orderid == null) {
            if (other.orderid != null)
                return false;
        } else if (!orderid.equals(other.orderid))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookid == null) ? 0 : bookid.hashCode());
        result = prime * result + ((orderid == null) ? 0 : orderid.hashCode());
        return result;
    }

}