package th.ac.kmutt.dashboard.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.dashboard.xstream.common.ImakeXML;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by imake on 22/11/2015.
 */

@XStreamAlias("StudentM")
public class StudentM  extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    // query 1
    private String noof_format;
    private Integer noof;


    // query 2
    private BigDecimal percent_undergraduate;
    private String no_of_undergraduate_format;
    private Integer no_of_undergraduate;


    // query 3
    private BigDecimal percent_graduate;
    private Integer no_of_graduate;
    private String  no_of_graduate_format;

    // query 4
    private BigDecimal percent_male;
    private BigDecimal percent_female;

     //query 5
     private Integer no_of_student;
    private Integer faculty_ratio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoof_format() {
        return noof_format;
    }

    public void setNoof_format(String noof_format) {
        this.noof_format = noof_format;
    }

    public Integer getNoof() {
        return noof;
    }

    public void setNoof(Integer noof) {
        this.noof = noof;
    }

    public BigDecimal getPercent_undergraduate() {
        return percent_undergraduate;
    }

    public void setPercent_undergraduate(BigDecimal percent_undergraduate) {
        this.percent_undergraduate = percent_undergraduate;
    }

    public String getNo_of_undergraduate_format() {
        return no_of_undergraduate_format;
    }

    public void setNo_of_undergraduate_format(String no_of_undergraduate_format) {
        this.no_of_undergraduate_format = no_of_undergraduate_format;
    }

    public Integer getNo_of_undergraduate() {
        return no_of_undergraduate;
    }

    public void setNo_of_undergraduate(Integer no_of_undergraduate) {
        this.no_of_undergraduate = no_of_undergraduate;
    }

    public BigDecimal getPercent_graduate() {
        return percent_graduate;
    }

    public void setPercent_graduate(BigDecimal percent_graduate) {
        this.percent_graduate = percent_graduate;
    }

    public Integer getNo_of_graduate() {
        return no_of_graduate;
    }

    public void setNo_of_graduate(Integer no_of_graduate) {
        this.no_of_graduate = no_of_graduate;
    }

    public String getNo_of_graduate_format() {
        return no_of_graduate_format;
    }

    public void setNo_of_graduate_format(String no_of_graduate_format) {
        this.no_of_graduate_format = no_of_graduate_format;
    }

    public BigDecimal getPercent_male() {
        return percent_male;
    }

    public void setPercent_male(BigDecimal percent_male) {
        this.percent_male = percent_male;
    }

    public BigDecimal getPercent_female() {
        return percent_female;
    }

    public void setPercent_female(BigDecimal percent_female) {
        this.percent_female = percent_female;
    }

    public Integer getNo_of_student() {
        return no_of_student;
    }

    public void setNo_of_student(Integer no_of_student) {
        this.no_of_student = no_of_student;
    }

    public Integer getFaculty_ratio() {
        return faculty_ratio;
    }

    public void setFaculty_ratio(Integer faculty_ratio) {
        this.faculty_ratio = faculty_ratio;
    }
}
