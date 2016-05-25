package th.ac.kmutt.dashboard.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.dashboard.xstream.common.ImakeXML;

import java.io.Serializable;
import java.util.List;

/**
 * Created by imake on 30/11/2015.
 */

@XStreamAlias("YearM")
public class YearM  extends ImakeXML implements Serializable {
    private  List<Year> years;

    public List<Year> getYears() {
        return years;
    }

    public void setYears(List<Year> years) {
        this.years = years;
    }
}
