package managedbean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.DatabaseOperations;
import entity.*;
import managedbean.helper.ShoppingCartItem;

@Named("cart")
@SessionScoped
public class CartPageBean extends BaseBean {
    private static final long serialVersionUID = -2011232754979343219L;

    // --------------------------------CONSTRUCTORS--------------------------------

    public CartPageBean() {
    }

    
    // --------------------------------PAGE_FUNCTIONS--------------------------------

    // execute checkout
    public void buy() {
        if (cart.getItemCount() < 1) {
            return;
        }
        Receipt receipt = new Receipt(getNow(), "Paypal", cart.getTotal());
        Set<OrderDetail> details = makeDetails(receipt);

        // make transaction
        DatabaseOperations.makeReceipt(this.user, details, receipt);
    }


    // --------------------------------PRIVATE_METHODS--------------------------------

    // Use items in cart to make order details
    private Set<OrderDetail> makeDetails(Receipt r) {
        Set<OrderDetail> details = new HashSet<OrderDetail>();
        OrderBookKey keys = new OrderBookKey();
        keys.setOrderid(r.getOrderid());

        for (ShoppingCartItem item : cart.getCartItems()) {
            OrderDetail detail = new OrderDetail();
            detail.setAmount(item.getQuantity());
            detail.setOrderBook(item.getItem());
            detail.setDetailOrder(r);
            keys.setBookid(item.getItem().getBookId());
            detail.setId(keys);
            details.add(detail);
        }
        return details;
    }

    // Get current datetime
    private String getNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String s = dtf.format(now).toString();
        return s;
    }

}