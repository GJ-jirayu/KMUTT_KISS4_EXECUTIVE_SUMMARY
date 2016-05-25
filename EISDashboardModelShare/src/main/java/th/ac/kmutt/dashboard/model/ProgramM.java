package th.ac.kmutt.dashboard.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.dashboard.xstream.common.ImakeXML;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by imake on 22/11/2015.
 */

@XStreamAlias("ProgramM")
public class ProgramM  extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    //query 1
    private Integer programs_for_undergraduate;
    private Integer programs_for_graduate;
    private BigDecimal percent_programs_for_undergraduate;
    private BigDecimal percent_programs_for_graduate;
    //query 2
    private Integer international_program;

    //query 3
    private Integer international_program_for_undergraduate;
    private Integer international_program_for_graduate;

    public Integer getPrograms_for_undergraduate() {
        return programs_for_undergraduate;
    }

    public void setPrograms_for_undergraduate(Integer programs_for_undergraduate) {
        this.programs_for_undergraduate = programs_for_undergraduate;
    }

    public Integer getPrograms_for_graduate() {
        return programs_for_graduate;
    }

    public void setPrograms_for_graduate(Integer programs_for_graduate) {
        this.programs_for_graduate = programs_for_graduate;
    }

    public Integer getInternational_program() {
        return international_program;
    }

    public void setInternational_program(Integer international_program) {
        this.international_program = international_program;
    }

    public Integer getInternational_program_for_undergraduate() {
        return international_program_for_undergraduate;
    }

    public void setInternational_program_for_undergraduate(Integer international_program_for_undergraduate) {
        this.international_program_for_undergraduate = international_program_for_undergraduate;
    }

    public BigDecimal getPercent_programs_for_undergraduate() {
        return percent_programs_for_undergraduate;
    }

    public void setPercent_programs_for_undergraduate(BigDecimal percent_programs_for_undergraduate) {
        this.percent_programs_for_undergraduate = percent_programs_for_undergraduate;
    }

    public BigDecimal getPercent_programs_for_graduate() {
        return percent_programs_for_graduate;
    }

    public void setPercent_programs_for_graduate(BigDecimal percent_programs_for_graduate) {
        this.percent_programs_for_graduate = percent_programs_for_graduate;
    }

    public Integer getInternational_program_for_graduate() {
        return international_program_for_graduate;
    }

    public void setInternational_program_for_graduate(Integer international_program_for_graduate) {
        this.international_program_for_graduate = international_program_for_graduate;
    }
}
