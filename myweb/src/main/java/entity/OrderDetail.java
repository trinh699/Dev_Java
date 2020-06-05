package entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "receipt_book")
public class OrderDetail implements Serializable{
    private static final long serialVersionUID = -6977965686746886571L;

    @EmbeddedId
    private OrderBookKey id;

    @ManyToOne
    @MapsId("bookid")
    @JoinColumn(name = "bookid")
    private Book orderBook;

    @ManyToOne
    @MapsId("orderid")
    @JoinColumn(name = "orderid")
    private Receipt detailOrder;

    private Integer amount;

    public OrderDetail() {
    }

    public OrderBookKey getId() {
        return id;
    }

    public void setId(OrderBookKey id) {
        this.id = id;
    }

    public Book getOrderBook() {
        return orderBook;
    }

    public void setOrderBook(Book orderBook) {
        this.orderBook = orderBook;
    }

    public Receipt getDetailOrder() {
        return detailOrder;
    }

    public void setDetailOrder(Receipt detailOrder) {
        this.detailOrder = detailOrder;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderDetail other = (OrderDetail) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}

