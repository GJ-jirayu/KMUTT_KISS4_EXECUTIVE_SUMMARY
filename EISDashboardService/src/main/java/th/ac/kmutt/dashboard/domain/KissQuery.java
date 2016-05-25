package th.ac.kmutt.dashboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by imake on 01/12/2015.
 */
@Entity
@Table(name = "KISS_QUERY")
public class KissQuery implements Serializable {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "QUERY_STATEMENT")
    private String queryStatement;

    @Column(name = "DESC")
    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQueryStatement() {
        return queryStatement;
    }

    public void setQueryStatement(String queryStatement) {
        this.queryStatement = queryStatement;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
