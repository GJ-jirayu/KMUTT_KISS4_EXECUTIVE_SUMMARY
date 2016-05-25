package th.ac.kmutt.dashboard.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by imake on 22/11/2015.
 */
@Entity
@Table(name = "KISS_LANGUAGE")
public class KissLanguage implements Serializable {
    @Id
    @Column(name = "KEY_LANG")
    private String keyLang;

    @Column(name = "VALUES_LANG_ENG")
    private String valuesLangEng;

    @Column(name = "VALUES_LANG_THAI")
    private String valuesLangThai;

    public String getKeyLang() {
        return keyLang;
    }

    public void setKeyLang(String keyLang) {
        this.keyLang = keyLang;
    }

    public String getValuesLangEng() {
        return valuesLangEng;
    }

    public void setValuesLangEng(String valuesLangEng) {
        this.valuesLangEng = valuesLangEng;
    }

    public String getValuesLangThai() {
        return valuesLangThai;
    }

    public void setValuesLangThai(String valuesLangThai) {
        this.valuesLangThai = valuesLangThai;
    }
}
