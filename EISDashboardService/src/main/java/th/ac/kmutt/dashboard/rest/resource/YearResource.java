package th.ac.kmutt.dashboard.rest.resource;

import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import th.ac.kmutt.dashboard.model.KissLanguageM;
import th.ac.kmutt.dashboard.model.Year;
import th.ac.kmutt.dashboard.model.YearM;
import th.ac.kmutt.dashboard.service.DashboardService;
import th.ac.kmutt.dashboard.xstream.common.ImakeResultMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by imake on 30/11/2015.
 */
public class YearResource extends BaseResource {

    // private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("dashboardServiceJpaImpl")
    private DashboardService dashboardService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;

    public YearResource() {
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
            throws ResourceException{
            // TODO Auto-generated method stub
            logger.debug("into Post ConsultantReportResource 2");
            InputStream in = null;
            try {
                in = entity.getStream();
                xstream.processAnnotations(YearM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
                YearM xsource = new YearM();
                Object xtarget = xstream.fromXML(in);
                if (xtarget != null) {
                    xsource = (YearM) xtarget;
                    if (xsource != null) {
                        List<Year> years= dashboardService.listYear();
                        ImakeResultMessage imakeMessage = new ImakeResultMessage();
                        if (years != null) {
                            List<YearM> models = new ArrayList<YearM>(1);
                            xsource.setYears(years);
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
        List<Year> years= dashboardService.listYear();
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        YearM xsource = new YearM();

        if (years != null) {
            List<YearM> models = new ArrayList<YearM>(1);
            xsource.setYears(years);
            // get Model List
            models.add(xsource);

            imakeMessage.setResultListObj(models);
        }
        return getRepresentation(null, imakeMessage, xstream);

    }
}
