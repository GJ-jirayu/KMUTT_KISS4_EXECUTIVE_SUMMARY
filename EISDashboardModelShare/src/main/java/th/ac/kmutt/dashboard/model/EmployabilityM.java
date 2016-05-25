package th.ac.kmutt.dashboard.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.dashboard.xstream.common.ImakeXML;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by imake on 22/11/2015.
 */

@XStreamAlias("EmployabilityM")
public class EmployabilityM  extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal undergraduate;
    private BigDecimal graduate;

    public BigDecimal getUndergraduate() {
        return undergraduate;
    }

    public void setUndergraduate(BigDecimal undergraduate) {
        this.undergraduate = undergraduate;
    }

    public BigDecimal getGraduate() {
        return graduate;
    }

    public void setGraduate(BigDecimal graduate) {
        this.graduate = graduate;
    }
}
