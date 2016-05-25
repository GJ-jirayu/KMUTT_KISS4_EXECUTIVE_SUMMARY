package th.ac.kmutt.dashboard.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.dashboard.xstream.common.ImakeXML;

import java.io.Serializable;

/**
 * Created by imake on 21/11/2015.
 */

@XStreamAlias("DashboardM")
public class DashboardM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private EmployabilityM employabilityM;
    private AcademicM academicM;
    private ProgramM programM;
    private BudgetM budgetM;
    private EmployeeM employeeM;
    private StudentM studentM;
    private String year;
    private String lang;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public EmployabilityM getEmployabilityM() {
        return employabilityM;
    }

    public void setEmployabilityM(EmployabilityM employabilityM) {
        this.employabilityM = employabilityM;
    }

    public AcademicM getAcademicM() {
        return academicM;
    }

    public void setAcademicM(AcademicM academicM) {
        this.academicM = academicM;
    }

    public ProgramM getProgramM() {
        return programM;
    }

    public void setProgramM(ProgramM programM) {
        this.programM = programM;
    }

    public BudgetM getBudgetM() {
        return budgetM;
    }

    public void setBudgetM(BudgetM budgetM) {
        this.budgetM = budgetM;
    }

    public EmployeeM getEmployeeM() {
        return employeeM;
    }

    public void setEmployeeM(EmployeeM employeeM) {
        this.employeeM = employeeM;
    }

    public StudentM getStudentM() {
        return studentM;
    }

    public void setStudentM(StudentM studentM)
    {
        this.studentM = studentM;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
