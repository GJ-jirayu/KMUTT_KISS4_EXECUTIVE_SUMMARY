package th.ac.kmutt.dashboard.form;

import java.io.Serializable;

/**
 * Created by imake on 22/11/2015.
 */
public class DashboardForm extends CommonForm implements Serializable {
    private  String year;
    private String lang;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
