package th.ac.kmutt.dashboard.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.dashboard.xstream.common.ImakeXML;

import java.io.Serializable;

/**
 * Created by imake on 22/11/2015.
 */

@XStreamAlias("AcademicM")
public class AcademicM  extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    //query 1
    private Integer faculties_schools;
    private Integer institute;

    public Integer getFaculties_schools() {
        return faculties_schools;
    }

    public void setFaculties_schools(Integer faculties_schools) {
        this.faculties_schools = faculties_schools;
    }

    public Integer getInstitute() {
        return institute;
    }

    public void setInstitute(Integer institute) {
        this.institute = institute;
    }
}
