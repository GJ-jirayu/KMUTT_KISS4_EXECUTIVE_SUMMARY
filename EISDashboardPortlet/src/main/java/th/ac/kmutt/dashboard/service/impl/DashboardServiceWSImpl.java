package th.ac.kmutt.dashboard.service.impl;

import org.springframework.stereotype.Service;
import th.ac.kmutt.dashboard.constant.ServiceConstant;
import th.ac.kmutt.dashboard.model.DashboardM;
import th.ac.kmutt.dashboard.model.KissLanguageM;
import th.ac.kmutt.dashboard.model.YearM;
import th.ac.kmutt.dashboard.service.DashboardService;
import th.ac.kmutt.dashboard.xstream.common.ImakeResultMessage;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Service("dashboardServiceWSImpl")
public class DashboardServiceWSImpl extends PostCommon implements DashboardService {

    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    @Override
    public DashboardM getDashboard(DashboardM param) {
        param.setServiceName(ServiceConstant.DASHBOARD_GET);
        ImakeResultMessage imakeMessage = postMessage(param, param.getClass().getName(), "dashboard", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (DashboardM) imakeMessage.getResultListObj().get(0);
        else
            return null;
        /*
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return  imakeMessage.getResultListObj();
        else
            return null;
            */
    }

    @Override
    public KissLanguageM getKissLanguageM(KissLanguageM param) {
        param.setServiceName(ServiceConstant.LANG_GET);
        ImakeResultMessage imakeMessage = postMessage(param, param.getClass().getName(), "kisslang", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (KissLanguageM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public YearM listYears(YearM param) {
        param.setServiceName(ServiceConstant.LANG_GET);
        ImakeResultMessage imakeMessage = postMessage(param, param.getClass().getName(), "years", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (YearM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

}
