package th.ac.kmutt.dashboard.model;

/**
 * Created by imake on 01/12/2015.
 */

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Year")
public class Year {
    public Year() {

    }

    public Year(String yearTH, String yearENG) {
        this.yearTH = yearTH;
        this.yearENG = yearENG;
    }

    private String yearTH;

    public String getYearENG() {
        return yearENG;
    }

    public void setYearENG(String yearENG) {
        this.yearENG = yearENG;
    }

    public String getYearTH() {
        return yearTH;
    }

    public void setYearTH(String yearTH) {
        this.yearTH = yearTH;
    }

    private String yearENG;

}
