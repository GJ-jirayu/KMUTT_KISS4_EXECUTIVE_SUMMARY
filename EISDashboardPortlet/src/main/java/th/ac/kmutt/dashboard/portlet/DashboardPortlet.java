package th.ac.kmutt.dashboard.portlet;

import com.google.gson.Gson;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;
import org.springframework.web.portlet.bind.annotation.EventMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import th.ac.kmutt.dashboard.form.DashboardForm;
import th.ac.kmutt.dashboard.model.KissLanguageM;
import th.ac.kmutt.dashboard.model.YearM;
import th.ac.kmutt.dashboard.service.DashboardService;

import javax.portlet.*;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Created by imake on 22/11/2015.
 */

@Controller("dashboardController")
@RequestMapping("VIEW")
@SessionAttributes({"dashboardForm","kissLanguage"})
public class DashboardPortlet {

    private static final Logger logger = Logger
            .getLogger(DashboardPortlet.class);
    private static final  Runtime rt = Runtime.getRuntime();
    @Autowired
    @Qualifier("dashboardServiceWSImpl")
    private DashboardService dashboardService;
    @InitBinder
    public void initBinder(PortletRequestDataBinder binder, PortletPreferences preferences) {
        logger.debug("initBinder");
        final String[] ALLOWED_FIELDS = {"year", "lang"};

        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

        binder.setAllowedFields(ALLOWED_FIELDS);
    }
    @RequestMapping
    public String displayDashboard(PortletRequest request, Model model) {
        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        String id= themeDisplay.getPortletDisplay().getId();
        String instanceId=themeDisplay.getPortletDisplay().getInstanceId();
        DashboardForm dashboardForm = null;
        if (!model.containsAttribute("dashboardForm")) {
            dashboardForm = new DashboardForm();
            model.addAttribute("dashboardForm",
                    dashboardForm);
        } else {
            dashboardForm = (DashboardForm) model.asMap().get("dashboardForm");
        }
        if (!(dashboardForm.getLang()!=null && dashboardForm.getLang().trim().length()>0)){
            dashboardForm.setLang("eng");
        }

        KissLanguageM kissLanguageM=new KissLanguageM();
        kissLanguageM.setLang(dashboardForm.getLang());
        logger.info("into lang->"+kissLanguageM.getLang());
        logger.info("into year 2->"+request.getParameter("year"));
        logger.info("into lang 2->"+request.getParameter("lang"));

        model.addAttribute("kissLanguage",
                dashboardService.getKissLanguageM(kissLanguageM));
        YearM yearM=new YearM();
        model.addAttribute("yearM",
                dashboardService.listYears(yearM));
        model.addAttribute("dashboardForm",
                dashboardForm);
        logger.info("into list DashboardPortlet");
        return "dashboard/display";
    }
    @RequestMapping(params = "action=doSubmit") // action phase
    public void doSubmit(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("dashboardForm") DashboardForm dashboardForm,
                             BindingResult result, Model model) {

        logger.info("into doSubmit lang->" + dashboardForm.getLang());
        response.setRenderParameter("action", "list");
    }


    @ResourceMapping(value = "research_group_resource_get_pdf")
    public void loadPdf(@RequestParam("year")  String year,@RequestParam("lang")  String lang, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "Executive_Dashboard";
        //String year=request.getParameter("year");
        //String lang=request.getParameter("lang");
        logger.info("year="+year+",lang="+lang);
        response.setCharacterEncoding("UTF-8");
        String contentType = "image/jpeg";
        String fileGen=genToken();
        String rootPath="/home/portal/";
        String server_url="http://10.1.127.61:8080/web/eis/executive_summary?year="+year+"&lang="+lang;

        /*
        String rootPath="/aoe/";
        String server_url="http://localhost:8080/web/guest/eis?year="+year+"&lang="+lang;
*/
		/* mtrId="20";
		 mdc_key="chart1";*/
        FileInputStream fin=null;
        File file=null;
        try {
            long start =System.currentTimeMillis();
            String cmd="/usr/local/bin/wkhtmltoimage --javascript-delay 10000 --no-stop-slow-scripts --format jpg --load-error-handling  ignore --width 1366  "+server_url+"  "+rootPath+"tmp/"+fileGen+".jpg";
            //String cmd="/usr/local/data/HttpServer/apache2/htdocs/wkhtmltoimage-amd64 --javascript-delay 3000 --quality 75 --crop-w "+width+" --crop-h "+height+" --format jpg  --use-xserver http://localhost:8080/MISSProcessImage/render?mtrId="+mtrId+"_"+mdc_key+"_"+chart+"_"+lang+"_"+width+"_"+height+" /tmp/"+fileGen+".jpg";
            //String cmd="";
            Process proc=null;
            System.out.println("cmd=>"+cmd);
                proc = rt.exec(cmd);

            th.ac.kmutt.dashboard.thread.StreamGobbler outputGobbler = new
                    th.ac.kmutt.dashboard.thread.StreamGobbler(proc.getInputStream(), "OUTPUT");

            // kick them off
            // errorGobbler.start();
            outputGobbler.start();

            // any error???
            // any error message?
            th.ac.kmutt.dashboard.thread.StreamGobbler errorGobbler = new
                    th.ac.kmutt.dashboard.thread.StreamGobbler(proc.getErrorStream(), "ERROR");
            errorGobbler.start();
            try {
                int exitVal = proc.waitFor();

                System.out.println("exitVal="+exitVal);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            long end =System.currentTimeMillis();
            //System.out.println(end-start);
           file= new File(rootPath+"tmp/"+fileGen+".jpg");
             fin= new FileInputStream(file);
            String contentDispositionType = "attachment; filename=\"" + filename + "\"";
            byte[] content= IOUtils.toByteArray(fin);
            PortletResponseUtil.sendFile(request, response, filename,content , contentType, contentDispositionType);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(fin!=null)
                fin.close();
            file.delete();
        }


    }
    private String genToken(){
        StringBuffer sb = new StringBuffer();
        for (int i = 36; i > 0; i -= 12) {
            int n = Math.min(12, Math.abs(i));
            sb.append(org.apache.commons.lang3.StringUtils.leftPad(Long.toString(Math.round(Math.random() * Math.pow(36, n)), 36), n, '0'));
        }
        return sb.toString();
    }
}
