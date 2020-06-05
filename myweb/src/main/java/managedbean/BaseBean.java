package managedbean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class BaseBean implements Serializable {
    public static final long serialVersionUID = -3280333692217446994L;
}