package th.ac.kmutt.dashboard.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import th.ac.kmutt.dashboard.xstream.common.ImakeXML;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by imake on 22/11/2015.
 */

@XStreamAlias("BudgetM")
public class BudgetM  extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    @XStreamImplicit(itemFieldName="incomes")
    private List<String[]> income_list;
    @XStreamImplicit(itemFieldName="expenses")
    private List<String[]> expense_list;

    private BigDecimal income_amount;
    private String income_amount_format;

    private BigDecimal expense_amount;
    private String expense_amount_format;

    public List<String[]> getIncome_list() {
        return income_list;
    }

    public void setIncome_list(List<String[]> income_list) {
        this.income_list = income_list;
    }

    public List<String[]> getExpense_list() {
        return expense_list;
    }

    public void setExpense_list(List<String[]> expense_list) {
        this.expense_list = expense_list;
    }

    public BigDecimal getIncome_amount() {
        return income_amount;
    }

    public void setIncome_amount(BigDecimal income_amount) {
        this.income_amount = income_amount;
    }

    public String getIncome_amount_format() {
        return income_amount_format;
    }

    public void setIncome_amount_format(String income_amount_format) {
        this.income_amount_format = income_amount_format;
    }

    public BigDecimal getExpense_amount() {
        return expense_amount;
    }

    public void setExpense_amount(BigDecimal expense_amount) {
        this.expense_amount = expense_amount;
    }

    public String getExpense_amount_format() {
        return expense_amount_format;
    }

    public void setExpense_amount_format(String expense_amount_format) {
        this.expense_amount_format = expense_amount_format;
    }

}
