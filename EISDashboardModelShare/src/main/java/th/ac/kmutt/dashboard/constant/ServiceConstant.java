package th.ac.kmutt.dashboard.constant;

import java.util.ResourceBundle;

public class ServiceConstant {
    public static final String hostReference = "http://10.2.0.76:10000/BPSService/RestletServlet/";

    public static final String LOG_APPENDER = "DashboardServicesLog";

    public static final String INTERFACE_RETURN_TYPE = "java.util.List";
    public static final String VOID_RETURN_TYPE = "void";
    public static final String ERROR_MESSAGE_KEY = "errorMessage";
    public static final String SUCCESS_MESSAGE_KEY = "successMessage";
    public static final String WARNING_MESSAGE_KEY = "warningMessage";

    public static final String ERROR_CONSTRAINT_VIOLATION_MESSAGE_CODE = "error.constraintViolation";

    public static final ResourceBundle bundle;
    public static String SCHEMA = "";

    static {
        bundle = ResourceBundle.getBundle("jdbc");
        SCHEMA = bundle.getString("schema");
    }

    public static final String MESSAGE_CODE_OK = "ok";
    public static final String MESSAGE_CODE_ERROR = "error";

    //SERVICE
    public static final String DASHBOARD_GET = "getDashboard";
    public static final String LANG_GET = "getLang";

}
