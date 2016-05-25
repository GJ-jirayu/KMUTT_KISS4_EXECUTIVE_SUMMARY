package th.ac.kmutt.dashboard.rest.resource;

import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import th.ac.kmutt.dashboard.model.DashboardM;
import th.ac.kmutt.dashboard.service.DashboardService;
import th.ac.kmutt.dashboard.xstream.common.ImakeResultMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by imake on 22/11/2015.
 */
public class DashboardResource extends BaseResource {
   // private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("dashboardServiceJpaImpl")
    private DashboardService dashboardService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;

    public DashboardResource() {
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
            xstream.processAnnotations(DashboardM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            DashboardM xsource = new DashboardM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (DashboardM) xtarget;
                if (xsource != null) {
                   DashboardM modelResult = dashboardService.getDashboardDynamic(xsource);
                   // DashboardM modelResult = dashboardService.getDashboard(xsource);
                    ImakeResultMessage imakeMessage = new ImakeResultMessage();
                    if (modelResult != null) {
                        List<DashboardM> models = new ArrayList<DashboardM>(1);

                        // get Model List
                        models.add(modelResult);

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


    private Representation returnUpdateRecord(Representation entity, DashboardM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<DashboardM> xsources = new ArrayList<DashboardM>(1);
        model.setUpdateRecord(updateRecord);
        xsources.add(model);
        imakeMessage.setResultListObj(xsources);
        return getRepresentation(entity, imakeMessage, xstream);
    }

    @Override
    protected Representation get(Variant variant) throws ResourceException {
        // TODO Auto-generated method stub
        DashboardM modelResult = dashboardService.getDashboard(null);
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        if (modelResult != null) {
            List<DashboardM> models = new ArrayList<DashboardM>(1);

            // get Model List
            models.add(modelResult);

            imakeMessage.setResultListObj(models);
        }
       return getRepresentation(null, imakeMessage, xstream);

    }

}
