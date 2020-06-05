package managedbean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.*;

@Named
@SessionScoped
public class UserReceiptPageBean extends BaseBean {
    private static final long serialVersionUID = -6708608590841958450L;
    private Map<Receipt, List<OrderDetail>> receiptHistory;
    @Inject
    private UserPageBean userPage;

    // --------------------------------------CONSTRUCTOR--------------------------------------

    public UserReceiptPageBean() {
        receiptHistory = new HashMap<Receipt, List<OrderDetail>>();
    }

    // --------------------------------------BEAN_FUNCTION--------------------------------------

    @PostConstruct
    public void initialize() {
        userPage.loadReceipt();
        List<Receipt> receiptList = userPage.getUserReceipts();
        for (Receipt receipt : receiptList) {
            receiptHistory.put(receipt, receipt.getOrderDetails());
        }
    }

    public void viewReceipts() {
        for (Map.Entry<Receipt, List<OrderDetail>> entry : receiptHistory.entrySet()) {
            System.out.println("Date: " + entry.getKey().getDate());
            System.out.println("Items: ");
            for (OrderDetail detail : entry.getValue()) {
                System.out.println(String.format("Book: %1$s \tamount: %2$s", detail.getOrderBook().getTitle(),
                        detail.getAmount()));
            }
            System.out.println("Total: " +  entry.getKey().getTotal());
            System.out.println("Payment method: " + entry.getKey().getPaymentMethod());
        }
    }

    // --------------------------------------ACCESSORS--------------------------------------

    public Map<Receipt, List<OrderDetail>> getReceiptHistory() {
        return receiptHistory;
    }

    public void setReceiptHistory(Map<Receipt, List<OrderDetail>> receiptHistory) {
        this.receiptHistory = receiptHistory;
    }

}