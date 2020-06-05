package managedbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("paypal")
@RequestScoped
public class PaypalTransaction extends BaseBean {
    private static final long serialVersionUID = 5346326539256561201L;
}