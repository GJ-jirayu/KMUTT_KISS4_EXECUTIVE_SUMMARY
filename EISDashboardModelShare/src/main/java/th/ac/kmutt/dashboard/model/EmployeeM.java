package th.ac.kmutt.dashboard.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.dashboard.xstream.common.ImakeXML;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by imake on 22/11/2015.
 */

@XStreamAlias("EmployeeM")
public class EmployeeM  extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    //query 1
    private Integer month_key;
    private BigDecimal percent_faculty_member_doctor;
    private Integer faculty_member_doctor;
    private String faculty_member_doctor_format;
    // query 2
    private Integer faculty_member;
    private String faculty_member_format;

    //query 3
    private Integer full_time;
    private String full_time_format;

    //query 4
    private BigDecimal factory_member_bachelor_lower_percent;
    private BigDecimal factory_member_master_percent;
    private BigDecimal factory_member_doctoral_percent;

    //query 5
    private BigDecimal researcher_bachelor_lower_percent;
    private BigDecimal researcher_master_percent;
    private BigDecimal researcher_doctoral_percent;

    //query 6
    private BigDecimal full_time_bachelor_lower_percent;
    private BigDecimal full_time_master_percent;
    private BigDecimal full_time_doctoral_percent;

    //query 4 ** mutch be percent
    //query 5 ** mutch be percent
    //query 5 ** mutch be percent


    public Integer getMonth_key() {
        return month_key;
    }

    public void setMonth_key(Integer month_key) {
        this.month_key = month_key;
    }

    public BigDecimal getPercent_faculty_member_doctor() {
        return percent_faculty_member_doctor;
    }

    public void setPercent_faculty_member_doctor(BigDecimal percent_faculty_member_doctor) {
        this.percent_faculty_member_doctor = percent_faculty_member_doctor;
    }

    public Integer getFaculty_member_doctor() {
        return faculty_member_doctor;
    }

    public void setFaculty_member_doctor(Integer faculty_member_doctor) {
        this.faculty_member_doctor = faculty_member_doctor;
    }

    public String getFaculty_member_doctor_format() {
        return faculty_member_doctor_format;
    }

    public void setFaculty_member_doctor_format(String faculty_member_doctor_format) {
        this.faculty_member_doctor_format = faculty_member_doctor_format;
    }

    public Integer getFaculty_member() {
        return faculty_member;
    }

    public void setFaculty_member(Integer faculty_member) {
        this.faculty_member = faculty_member;
    }

    public String getFaculty_member_format() {
        return faculty_member_format;
    }

    public void setFaculty_member_format(String faculty_member_format) {
        this.faculty_member_format = faculty_member_format;
    }

    public Integer getFull_time() {
        return full_time;
    }

    public void setFull_time(Integer full_time) {
        this.full_time = full_time;
    }

    public String getFull_time_format() {
        return full_time_format;
    }

    public void setFull_time_format(String full_time_format) {
        this.full_time_format = full_time_format;
    }

    public BigDecimal getFactory_member_bachelor_lower_percent() {
        return factory_member_bachelor_lower_percent;
    }

    public void setFactory_member_bachelor_lower_percent(BigDecimal factory_member_bachelor_lower_percent) {
        this.factory_member_bachelor_lower_percent = factory_member_bachelor_lower_percent;
    }

    public BigDecimal getFactory_member_master_percent() {
        return factory_member_master_percent;
    }

    public void setFactory_member_master_percent(BigDecimal factory_member_master_percent) {
        this.factory_member_master_percent = factory_member_master_percent;
    }

    public BigDecimal getFactory_member_doctoral_percent() {
        return factory_member_doctoral_percent;
    }

    public void setFactory_member_doctoral_percent(BigDecimal factory_member_doctoral_percent) {
        this.factory_member_doctoral_percent = factory_member_doctoral_percent;
    }

    public BigDecimal getResearcher_bachelor_lower_percent() {
        return researcher_bachelor_lower_percent;
    }

    public void setResearcher_bachelor_lower_percent(BigDecimal researcher_bachelor_lower_percent) {
        this.researcher_bachelor_lower_percent = researcher_bachelor_lower_percent;
    }

    public BigDecimal getResearcher_master_percent() {
        return researcher_master_percent;
    }

    public void setResearcher_master_percent(BigDecimal researcher_master_percent) {
        this.researcher_master_percent = researcher_master_percent;
    }

    public BigDecimal getResearcher_doctoral_percent() {
        return researcher_doctoral_percent;
    }

    public void setResearcher_doctoral_percent(BigDecimal researcher_doctoral_percent) {
        this.researcher_doctoral_percent = researcher_doctoral_percent;
    }

    public BigDecimal getFull_time_bachelor_lower_percent() {
        return full_time_bachelor_lower_percent;
    }

    public void setFull_time_bachelor_lower_percent(BigDecimal full_time_bachelor_lower_percent) {
        this.full_time_bachelor_lower_percent = full_time_bachelor_lower_percent;
    }

    public BigDecimal getFull_time_master_percent() {
        return full_time_master_percent;
    }

    public void setFull_time_master_percent(BigDecimal full_time_master_percent) {
        this.full_time_master_percent = full_time_master_percent;
    }

    public BigDecimal getFull_time_doctoral_percent() {
        return full_time_doctoral_percent;
    }

    public void setFull_time_doctoral_percent(BigDecimal full_time_doctoral_percent) {
        this.full_time_doctoral_percent = full_time_doctoral_percent;
    }
}
