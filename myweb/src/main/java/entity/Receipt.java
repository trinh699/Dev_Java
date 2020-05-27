package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "receipt")
public class Receipt implements Serializable {
    private static final long serialVersionUID = -1737859893948370664L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "orderid")
    private Integer orderid;
    @Column(name = "orderdate")
    private String date;
    @Column(name = "orderpayment")
    private String paymentMethod;
    private Double total;

    // Non-accessed
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private User orderUser;

    @OneToMany(mappedBy = "detailOrder", cascade = CascadeType.ALL)
    Set<OrderDetail> orderDetails;

    // Constructors
    public Receipt() {
    }

    public Receipt(String date, String paymentMethod, Double total) {
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.total = total;
    }

    // Accessors
    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public User getOrderUser() {
        return orderUser;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setOrderUser(User orderUser) {
        this.orderUser = orderUser;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderUser == null) ? 0 : orderUser.hashCode());
        result = prime * result + ((orderid == null) ? 0 : orderid.hashCode());
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
        Receipt other = (Receipt) obj;
        if (orderUser == null) {
            if (other.orderUser != null)
                return false;
        } else if (!orderUser.equals(other.orderUser))
            return false;
        if (orderid == null) {
            if (other.orderid != null)
                return false;
        } else if (!orderid.equals(other.orderid))
            return false;
        return true;
    }

}
