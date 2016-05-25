package th.ac.kmutt.dashboard.model;

import th.ac.kmutt.dashboard.xstream.common.ImakeXML;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by imake on 24/11/2015.
 */
public class KissLanguageM extends ImakeXML implements Serializable {
    private static final long serialVersionUID = 1L;
    private String lang;
    private Map<String,String> languageMap;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Map<String, String> getLanguageMap() {
        return languageMap;
    }

    public void setLanguageMap(Map<String, String> languageMap) {
        this.languageMap = languageMap;

    }
}
