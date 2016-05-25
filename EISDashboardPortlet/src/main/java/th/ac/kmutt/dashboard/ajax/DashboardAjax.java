package th.ac.kmutt.dashboard.ajax;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import th.ac.kmutt.dashboard.model.DashboardM;
import th.ac.kmutt.dashboard.model.KissLanguageM;
import th.ac.kmutt.dashboard.service.DashboardService;

import javax.servlet.ServletContext;

public class DashboardAjax {

    private DashboardService dashboardService;

    public DashboardAjax() {
        WebContext ctx = WebContextFactory.get();
        ServletContext servletContext = ctx.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        dashboardService = (DashboardService) wac.getBean("dashboardServiceWSImpl");
    }
    public DashboardM getDashboard(DashboardM param){
        return dashboardService.getDashboard(param);
    }
    public KissLanguageM getKissLanguage(KissLanguageM param){
        return dashboardService.getKissLanguageM(param);
    }
}
