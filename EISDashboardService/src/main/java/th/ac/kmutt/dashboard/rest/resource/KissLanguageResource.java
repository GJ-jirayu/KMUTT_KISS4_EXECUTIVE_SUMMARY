package th.ac.kmutt.dashboard.rest.resource;

import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import th.ac.kmutt.dashboard.model.DashboardM;
import th.ac.kmutt.dashboard.model.KissLanguageM;
import th.ac.kmutt.dashboard.service.DashboardService;
import th.ac.kmutt.dashboard.xstream.common.ImakeResultMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by imake on 24/11/2015.
 */
public class KissLanguageResource extends BaseResource {

    // private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("dashboardServiceJpaImpl")
    private DashboardService dashboardService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;

    public KissLanguageResource() {
        super();
        logger.debug("into constructor ChartEntity");
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doInit() throws ResourceException {
        // TODO Auto-generated method stub
        super.doInit();
        logger.debug("into doInit");
    }

    @Override
    protected Representation post(Representation entity, Variant variant)
            throws ResourceException {
        // TODO Auto-generated method stub
        logger.debug("into Post ConsultantReportResource 2");
        InputStream in = null;
        try {
            in = entity.getStream();
            xstream.processAnnotations(KissLanguageM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            KissLanguageM xsource = new KissLanguageM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (KissLanguageM) xtarget;
                if (xsource != null) {
                    Map<String,String> langMap = dashboardService.listKissLanguages(xsource.getLang());
                    ImakeResultMessage imakeMessage = new ImakeResultMessage();
                    if (langMap != null) {
                        List<KissLanguageM> models = new ArrayList<KissLanguageM>(1);
                        xsource.setLanguageMap(langMap);
                        // get Model List
                        models.add(xsource);

                        imakeMessage.setResultListObj(models);
                    }
                    return getRepresentation(entity, imakeMessage, xstream);
                }

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            logger.debug(" into Finally Call");
            try {
                if (in != null)
                    in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }



    @Override
    protected Representation get(Variant variant) throws ResourceException {
        // TODO Auto-generated method stub
        Map<String,String> langMap= dashboardService.listKissLanguages("th");
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        KissLanguageM kissLanguageM=new KissLanguageM();
        if (langMap != null) {
            List<KissLanguageM> models = new ArrayList<KissLanguageM>(1);
            kissLanguageM.setLanguageMap(langMap);
            // get Model List
            models.add(kissLanguageM);

            imakeMessage.setResultListObj(models);
        }
        return getRepresentation(null, imakeMessage, xstream);

    }

}
