package th.ac.kmutt.dashboard.repository;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import th.ac.kmutt.dashboard.constant.ServiceConstant;
import th.ac.kmutt.dashboard.domain.KissLanguage;
import th.ac.kmutt.dashboard.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("dashboardRepository")
@Transactional
public class DashboardRepository {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @PersistenceContext(unitName = "HibernatePersistenceUnit")
    private EntityManager entityManager;

    @Autowired
    @PersistenceContext(unitName = "HibernatePersistenceLiferayUnit")
    private EntityManager portalEntityManager;

    @Autowired
    @PersistenceContext(unitName = "HibernatePersistenceKmuttUnit")
    private EntityManager kmuttEntityManager;
    public Map<String,String> listKissLanguages(String lang){
        Query query= kmuttEntityManager.createQuery(" select p from KissLanguage p ",KissLanguage.class);
        List<KissLanguage> obj=query.getResultList();
        Map<String,String> languageMap=new HashMap<String,String>();
        Object[] results=null;
        if(obj!=null && obj.size()>0){
            for (KissLanguage kissLanguage:obj){
                String value=kissLanguage.getValuesLangEng();
                if(lang.equals("th")){
                    value=kissLanguage.getValuesLangThai();
                }
                languageMap.put(kissLanguage.getKeyLang(),value);
            }
        }

        return languageMap;
    }
    private Object[] getStudentQuery1(String year){
        StringBuffer sb = new StringBuffer("SELECT \n" +
                "1 as ID,"+
                "VARCHAR_FORMAT(SUM(NO_OF_STUDENT), '999,999,999,999')   AS NO_OF_FORMAT ,"+
                "SUM(NO_OF_STUDENT) as NO_OF \n" +
                "FROM FACT_ALL_STUDENT FAS\n" +
                "INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "WHERE DS.ACADEMIC_YEAR = :year \n" +
                "AND LEFT(DS.SEMESTER,1)  = '1' ");
        logger.info("getStudentQuery1->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List<Object[]> obj=query.getResultList();
        Object[] results=null;
        if(obj!=null && obj.size()>0) {
            results= obj.get(0);
        }
        return results;
    }

    private Object[] getStudentQuery2(String year){
        StringBuffer sb = new StringBuffer("SELECT \n" +
                "DISTINCT DS.ACADEMIC_YEAR,\n" +
                "ROUND(DECIMAL((DECIMAL(QRYB.NO_OF_UNDERGRADUATE,10,2)/ (QRYA.NO_OF_ALL) )*100,10,0),0) AS PERCENT_UNDERGRADUATE,\n" +
                " VARCHAR_FORMAT(QRYB.NO_OF_UNDERGRADUATE,'999,999,999,999') as NO_OF_UNDERGRADUATE_FORMAT, "+
                "QRYB.NO_OF_UNDERGRADUATE\n" +
                "FROM FACT_ALL_STUDENT FAS\n" +
                "INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "INNER JOIN DIM_PROGRAM DP  ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY\n" +
                "INNER JOIN (SELECT \n" +
                "            DISTINCT DS.ACADEMIC_YEAR,\n" +
                "            SUM(NO_OF_STUDENT) AS NO_OF_ALL\n" +
                "            FROM FACT_ALL_STUDENT FAS\n" +
                "            INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "            INNER JOIN DIM_PROGRAM DP  ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY\n" +
                "            WHERE DS.ACADEMIC_YEAR = :year  \n" +
                "            AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "            GROUP BY DS.ACADEMIC_YEAR)QRYA ON QRYA.ACADEMIC_YEAR = DS.ACADEMIC_YEAR\n" +
                "INNER JOIN (SELECT \n" +
                "            DISTINCT DS.ACADEMIC_YEAR,\n" +
                "            SUM(NO_OF_STUDENT) AS NO_OF_UNDERGRADUATE\n" +
                "            FROM FACT_ALL_STUDENT FAS\n" +
                "            INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "            INNER JOIN DIM_PROGRAM DP  ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY\n" +
                "            WHERE DS.ACADEMIC_YEAR = :year \n" +
                "            AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "            AND DP.EDUCATION_LEVEL_GROUP = 'ป.ตรี'\n" +
                "            GROUP BY DS.ACADEMIC_YEAR)QRYB ON QRYB.ACADEMIC_YEAR = DS.ACADEMIC_YEAR\n" +
                "WHERE DS.ACADEMIC_YEAR = :year  \n" +
                "AND LEFT(DS.SEMESTER,1) = '1' ");
        logger.info("getStudentQuery2->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List<Object[]> obj=query.getResultList();
        Object[] results=null;
        if(obj!=null && obj.size()>0) {
            results= obj.get(0);
        }

        return results;
    }
    private Object[] getStudentQuery3(String year){
        StringBuffer sb = new StringBuffer("" +
                "SELECT \n" +
                "DISTINCT DS.ACADEMIC_YEAR,\n" +
                "ROUND(DECIMAL((DECIMAL(QRYB.NO_OF_GRADUATE,10,2)/ (QRYA.NO_OF_ALL) )*100,10,2),0) AS PERCENT_GRADUATE,\n" +
                "QRYB.NO_OF_GRADUATE,\n" +
                "varchar_format(QRYB.NO_OF_GRADUATE,'999,999,999,999') as NO_OF_GRADUATE_FORMAT "+
                "FROM FACT_ALL_STUDENT FAS\n" +
                "INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "INNER JOIN DIM_PROGRAM DP  ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY\n" +
                "INNER JOIN (SELECT \n" +
                "            DISTINCT DS.ACADEMIC_YEAR,\n" +
                "            SUM(NO_OF_STUDENT) AS NO_OF_ALL\n" +
                "            FROM FACT_ALL_STUDENT FAS\n" +
                "            INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "            INNER JOIN DIM_PROGRAM DP  ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY\n" +
                "            WHERE DS.ACADEMIC_YEAR = :year\n" +
                "            AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "            GROUP BY DS.ACADEMIC_YEAR)QRYA ON QRYA.ACADEMIC_YEAR = DS.ACADEMIC_YEAR\n" +
                "INNER JOIN (SELECT \n" +
                "            DISTINCT DS.ACADEMIC_YEAR,\n" +
                "            SUM(NO_OF_STUDENT) AS NO_OF_GRADUATE\n" +
                "            FROM FACT_ALL_STUDENT FAS\n" +
                "            INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "            INNER JOIN DIM_PROGRAM DP  ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY\n" +
                "            WHERE DS.ACADEMIC_YEAR = :year\n" +
                "            AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "            AND DP.EDUCATION_LEVEL_GROUP = 'บัณฑิตศึกษา'\n" +
                "            GROUP BY DS.ACADEMIC_YEAR)QRYB ON QRYB.ACADEMIC_YEAR = DS.ACADEMIC_YEAR\n" +
                "WHERE DS.ACADEMIC_YEAR = :year\n" +
                "AND LEFT(DS.SEMESTER,1) = '1'"

                );
        logger.info("getStudentQuery3->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List<Object[]> obj=query.getResultList();
        Object[] results=null;
        if(obj!=null && obj.size()>0) {
            results= obj.get(0);
        }
        return results;
    }

    private Object[] getStudentQuery4(String year){
        StringBuffer sb = new StringBuffer("SELECT \n" +
                "DISTINCT DS.ACADEMIC_YEAR,\n" +
                "ROUND(DECIMAL((DECIMAL(QRYB.NO_OF_MALE,10,2)/ (QRYA.NO_OF_ALL) )*100,10,1),0) AS PERCENT_MALE,\n" +
                "ROUND(DECIMAL((DECIMAL(QRYC.NO_OF_FEMALE,10,2)/ (QRYA.NO_OF_ALL) )*100,10,1),0) AS PERCENT_FEMALE\n" +
                "FROM FACT_ALL_STUDENT FAS\n" +
                "INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "INNER JOIN (SELECT \n" +
                "            DISTINCT DS.ACADEMIC_YEAR,\n" +
                "            SUM(NO_OF_STUDENT) AS NO_OF_ALL\n" +
                "            FROM FACT_ALL_STUDENT FAS\n" +
                "            INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "            WHERE DS.ACADEMIC_YEAR = :year\n" +
                "            AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "            GROUP BY DS.ACADEMIC_YEAR)QRYA ON QRYA.ACADEMIC_YEAR = DS.ACADEMIC_YEAR\n" +
                "INNER JOIN (SELECT \n" +
                "            DISTINCT DS.ACADEMIC_YEAR,\n" +
                "            SUM(NO_OF_STUDENT) AS NO_OF_MALE\n" +
                "            FROM FACT_ALL_STUDENT FAS\n" +
                "            INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "            INNER JOIN DIM_GENDER DG ON FAS.GENDER_KEY = DG.GENDER_KEY\n" +
                "            WHERE DS.ACADEMIC_YEAR = :year\n" +
                "            AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "            AND DG.GENDER_CODE = 'M'\n" +
                "            GROUP BY DS.ACADEMIC_YEAR)QRYB ON QRYB.ACADEMIC_YEAR = DS.ACADEMIC_YEAR\n" +
                "INNER JOIN (SELECT \n" +
                "            DISTINCT DS.ACADEMIC_YEAR,\n" +
                "            SUM(NO_OF_STUDENT) AS NO_OF_FEMALE\n" +
                "            FROM FACT_ALL_STUDENT FAS\n" +
                "            INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "            INNER JOIN DIM_GENDER DG ON FAS.GENDER_KEY = DG.GENDER_KEY\n" +
                "            WHERE DS.ACADEMIC_YEAR = :year\n" +
                "            AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "            AND DG.GENDER_CODE = 'F'\n" +
                "            GROUP BY DS.ACADEMIC_YEAR)QRYC ON QRYC.ACADEMIC_YEAR = DS.ACADEMIC_YEAR\n" +
                "WHERE DS.ACADEMIC_YEAR = :year\n" +
                "AND LEFT(DS.SEMESTER,1) = '1'");
        logger.info("getStudentQuery4->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List<Object[]> obj=query.getResultList();
        Object[] results=null;
        if(obj!=null && obj.size()>0) {
            results= obj.get(0);
        }
        return results;
    }


    private Object[] getStudentQuery5(String year){
        StringBuffer sb = new StringBuffer("SELECT\n" +
                "    DISTINCT DS.ACADEMIC_YEAR,\n" +
                "            (QRYA.NO_OF_ALL)/(QRYB.NO_OF_TEACHER)  AS NO_OF_STUDENT,\n" +
                "    1 AS \"FACULTY RATIO\"\n" +
                "    FROM FACT_ALL_STUDENT FAS\n" +
                "INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "INNER JOIN (SELECT \n" +
                "            DISTINCT DS.ACADEMIC_YEAR,\n" +
                "            SUM(NO_OF_STUDENT) AS NO_OF_ALL\n" +
                "            FROM FACT_ALL_STUDENT FAS\n" +
                "            INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "            WHERE DS.ACADEMIC_YEAR = :year\n" +
                "            AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "            GROUP BY DS.ACADEMIC_YEAR)QRYA ON QRYA.ACADEMIC_YEAR = DS.ACADEMIC_YEAR\n" +
                "INNER JOIN (SELECT \n" +
                "            DS.ACADEMIC_YEAR,\n" +
                "            SUM(FTS.NO_OF_TEACHER) AS NO_OF_TEACHER\n" +
                "            FROM FACT_TEACHER_STUDENT FTS\n" +
                "            INNER JOIN DIM_SEMESTER DS ON  FTS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "            WHERE DS.ACADEMIC_YEAR = :year\n" +
                "            AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "            GROUP BY DS.ACADEMIC_YEAR)QRYB ON QRYB.ACADEMIC_YEAR = DS.ACADEMIC_YEAR\n" +
                "WHERE DS.ACADEMIC_YEAR = :year\n" +
                "AND LEFT(DS.SEMESTER,1) = '1'");
        logger.info("getStudentQuery5->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List<Object[]> obj=query.getResultList();
        Object[] results=null;
        if(obj!=null && obj.size()>0) {
            results= obj.get(0);
        }
        return results;
    }
    private Object[] getEmployeeQuery1(String year){
        StringBuffer sb = new StringBuffer("SELECT DISTINCT HFE.MONTH_KEY,\n" +
                "       DECIMAL((DECIMAL (QRYC.FACULTY_MEMBER_DOCTOR,10,2) /QRYB.FACULTY_MEMBER)*100,10,0) AS FACULTY_MEMBER_DOCTOR_FORMAT ,\n" +
                "       QRYB.FACULTY_MEMBER,\n" +
                " varchar_format(QRYB.FACULTY_MEMBER,'999,999,999,999') as FACULTY_MEMBER_FORMAT  "+
                "FROM HR_FACT_EMPLOYEE HFE\n" +
                "INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                "INNER JOIN HR_DIM_EMP_LEVEL_TYPE DEL ON HFE.EMP_LEVEL_TYPE_KEY = DEL.EMP_LEVEL_TYPE_KEY\n" +
                "INNER JOIN (SELECT HFE.MONTH_KEY ,COUNT(HFE.EMP_KEY) AS FACULTY_MEMBER\n" +
                "            FROM HR_FACT_EMPLOYEE HFE\n" +
                "            INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                "            INNER JOIN HR_DIM_WORK_GROUP WD ON HFE.WORK_GROUP_KEY = WD.WORK_GROUP_KEY\n" +
                "            INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                "            WHERE DD.CALENDAR_YEAR = :year\n" +
                "            AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                "                                 )\n" +
                "            AND WD.WORK_GROUP_CODE = 1\n" +
                "            GROUP BY HFE.MONTH_KEY\n" +
                "            )QRYB ON HFE.MONTH_KEY = QRYB.MONTH_KEY\n" +
                "INNER JOIN (SELECT HFE.MONTH_KEY ,COUNT(HFE.EMP_KEY) AS FACULTY_MEMBER_DOCTOR\n" +
                "            FROM HR_FACT_EMPLOYEE HFE\n" +
                "            INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                "            INNER JOIN HR_DIM_WORK_GROUP WD ON HFE.WORK_GROUP_KEY = WD.WORK_GROUP_KEY\n" +
                "            INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                "            WHERE DD.CALENDAR_YEAR = :year\n" +
                "            AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                "                                 )\n" +
                "            AND WD.WORK_GROUP_CODE = 1\n" +
                "            AND ECL.EDUCATION_LEVEL_CODE = '43'\n" +
                "            GROUP BY HFE.MONTH_KEY\n" +
                "            )QRYC ON HFE.MONTH_KEY = QRYC.MONTH_KEY\n" +
                "WHERE DD.CALENDAR_YEAR = :year\n" +
                "AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                "                     FROM HR_FACT_EMPLOYEE HFE \n" +
                "                     INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                "                     WHERE DD.CALENDAR_YEAR = :year \n" +
                "                     )");
        logger.info("getEmployeeQuery1->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List<Object[]> obj=query.getResultList();
        Object[] results=null;
        if(obj!=null && obj.size()>0) {
            results= obj.get(0);
        }
        return results;
    }
    private Object[] getEmployeeQuery2(String year){
        StringBuffer sb = new StringBuffer("SELECT HFE.MONTH_KEY ,COUNT(HFE.EMP_KEY) AS FACULTY_MEMBER\n" +
                ",varchar_format(COUNT(HFE.EMP_KEY),'999,999,999,999') AS FACULTY_MEMBER_FORMAT "+
                "     FROM HR_FACT_EMPLOYEE HFE\n" +
                "            INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                "            INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY =    HLT.EMP_LEVEL_TYPE_KEY\n" +
                "            INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                "            WHERE DD.CALENDAR_YEAR = :year\n" +
                "            AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                "                                 )\n" +
                "            AND HLT.POSITION_NAME ='นักวิจัย'\n" +
                "            GROUP BY HFE.MONTH_KEY");
        logger.info("getEmployeeQuery2->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List<Object[]> obj=query.getResultList();
        Object[] results=null;
        if(obj!=null && obj.size()>0) {
            results= obj.get(0);
        }
        return results;
    }
    private Object[] getEmployeeQuery3(String year){
        StringBuffer sb = new StringBuffer("SELECT HFE.MONTH_KEY ,COUNT(HFE.EMP_KEY) AS FULL_TIME\n" +
                ",varchar_format(COUNT(HFE.EMP_KEY),'999,999,999,999') AS FULL_TIME_FORMAT "+
                "            FROM HR_FACT_EMPLOYEE HFE\n" +
                "            INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                "            INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                "            INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                "            WHERE DD.CALENDAR_YEAR = :year\n" +
                "            AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                "                                 )\n" +
                "            AND HLT.POSITION_NAME in ('กลุ่มงานช่าง','ชำนาญการ  ','ชำนาญงาน','อื่นๆ','กลุ่มงานบริการพื้นฐาน','ปฏิบัติการ','ปฏิบัติงาน','กลุ่มงานสนับสนุน','ชำนาญการพิเศษ','สายวิชาชีพอื่นๆ')\n" +
                "            GROUP BY HFE.MONTH_KEY");
        logger.info("getEmployeeQuery3->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List<Object[]> obj=query.getResultList();
        Object[] results=null;
        if(obj!=null && obj.size()>0) {
            results= obj.get(0);
        }
        return results;
    }


    private List<Object[]> getEmployeeQuery4(String year){
            StringBuffer sb = new StringBuffer("" +
                    "SELECT DISTINCT DD.CALENDAR_YEAR , 'Bachelor''s & Lower' AS EDUCATION_LEVEL_NAME ,ROUND(DECIMAL(DECIMAL(QRYB.NO_OF_EMP)/QRYA.NO_OF_EMP_ALL*100,10,2),0) AS PERCENT\n" +
                    "            FROM HR_FACT_EMPLOYEE HFE\n" +
                    "            INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "            INNER JOIN HR_DIM_WORK_GROUP WD ON HFE.WORK_GROUP_KEY = WD.WORK_GROUP_KEY\n" +
                    "            INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP_ALL\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_WORK_GROUP WD ON HFE.WORK_GROUP_KEY = WD.WORK_GROUP_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                              )\n" +
                    "                         AND WD.WORK_GROUP_CODE = 1\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYA ON DD.CALENDAR_YEAR = QRYA.CALENDAR_YEAR\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_WORK_GROUP WD ON HFE.WORK_GROUP_KEY = WD.WORK_GROUP_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                         )\n" +
                    "                         AND ECL.EDUCATION_LEVEL_GROUP_CODE IN  ('01','02')\n" +
                    "                         AND WD.WORK_GROUP_CODE = 1\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYB ON DD.CALENDAR_YEAR = QRYB.CALENDAR_YEAR\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                              FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                              INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                              WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                              )\n" +
                    " \n" +
                    "UNION ALL \n" +
                    "SELECT DISTINCT DD.CALENDAR_YEAR , 'Master''s' AS EDUCATION_LEVEL_NAME ,ROUND(DECIMAL(DECIMAL(QRYB.NO_OF_EMP)/QRYA.NO_OF_EMP_ALL*100,10,2 ),0) AS PERCENT\n" +
                    "            FROM HR_FACT_EMPLOYEE HFE\n" +
                    "            INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "            INNER JOIN HR_DIM_WORK_GROUP WD ON HFE.WORK_GROUP_KEY = WD.WORK_GROUP_KEY\n" +
                    "            INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP_ALL\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_WORK_GROUP WD ON HFE.WORK_GROUP_KEY = WD.WORK_GROUP_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                               )\n" +
                    "                         AND WD.WORK_GROUP_CODE = 1\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYA ON DD.CALENDAR_YEAR = QRYA.CALENDAR_YEAR\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_WORK_GROUP WD ON HFE.WORK_GROUP_KEY = WD.WORK_GROUP_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                 )\n" +
                    "                         AND ECL.EDUCATION_LEVEL_GROUP_CODE IN  ('03')\n" +
                    "                         AND WD.WORK_GROUP_CODE = 1\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYB ON DD.CALENDAR_YEAR = QRYB.CALENDAR_YEAR\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                              FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                              INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                              WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                               )\n" +
                    "UNION ALL \n" +
                    "SELECT DISTINCT DD.CALENDAR_YEAR , 'Doctoral' AS EDUCATION_LEVEL_NAME ,ROUND(DECIMAL(DECIMAL(QRYB.NO_OF_EMP)/QRYA.NO_OF_EMP_ALL*100,10,2 ),0) AS PERCENT\n" +
                    "            FROM HR_FACT_EMPLOYEE HFE\n" +
                    "            INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "            INNER JOIN HR_DIM_WORK_GROUP WD ON HFE.WORK_GROUP_KEY = WD.WORK_GROUP_KEY\n" +
                    "            INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP_ALL\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_WORK_GROUP WD ON HFE.WORK_GROUP_KEY = WD.WORK_GROUP_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                 )\n" +
                    "                         AND WD.WORK_GROUP_CODE = 1\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYA ON DD.CALENDAR_YEAR = QRYA.CALENDAR_YEAR\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_WORK_GROUP WD ON HFE.WORK_GROUP_KEY = WD.WORK_GROUP_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                 )\n" +
                    "                         AND ECL.EDUCATION_LEVEL_GROUP_CODE IN  ('04')\n" +
                    "                         AND WD.WORK_GROUP_CODE = 1\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYB ON DD.CALENDAR_YEAR = QRYB.CALENDAR_YEAR\n" +
                    "            WHERE DD.CALENDAR_YEAR = :year\n" +
                    "            AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                 ) \n");

        logger.info("getEmployeeQuery4->"+sb.toString());
            Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
            List obj=query.getResultList();
            List<Object[]> results=null;
            if(obj!=null) {
                results = (List<Object[]>) obj;
            }
            return results;
    }
    private List<Object[]> getEmployeeQuery5(String year){
            StringBuffer sb = new StringBuffer("SELECT DISTINCT DD.CALENDAR_YEAR , 'Bachelor''s & Lower' AS EDUCATION_LEVEL_NAME ,ROUND(DECIMAL(DECIMAL(QRYB.NO_OF_EMP)/QRYA.NO_OF_EMP_ALL*100,10,2),0) AS PERCENT\n" +
                    "            FROM HR_FACT_EMPLOYEE HFE\n" +
                    "            INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "            INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "            INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP_ALL\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                         )\n" +
                    "                         AND HLT.POSITION_NAME in ('นักวิจัย')\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYA ON DD.CALENDAR_YEAR = QRYA.CALENDAR_YEAR\n" +
                    "        INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                          AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                         )\n" +
                    "                         AND ECL.EDUCATION_LEVEL_GROUP_CODE IN  ('01','02')\n" +
                    "                         AND HLT.POSITION_NAME in ('นักวิจัย')\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYB ON DD.CALENDAR_YEAR = QRYB.CALENDAR_YEAR\n" +
                    "            WHERE DD.CALENDAR_YEAR = :year \n" +
                    "            AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                         )\n" +
                    " \n" +
                    "UNION ALL \n" +
                    "SELECT DISTINCT DD.CALENDAR_YEAR , 'Master''s' AS EDUCATION_LEVEL_NAME ,ROUND (DECIMAL(DECIMAL(QRYB.NO_OF_EMP)/ (QRYA.NO_OF_EMP_ALL)*100,10,2),0) AS PERCENT\n" +
                    "            FROM HR_FACT_EMPLOYEE HFE \n" +
                    "            INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "            INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "            INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP_ALL\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                         )\n" +
                    "                         AND HLT.POSITION_NAME in ('นักวิจัย')\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYA ON DD.CALENDAR_YEAR = QRYA.CALENDAR_YEAR\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                         )\n" +
                    "                         AND ECL.EDUCATION_LEVEL_GROUP_CODE IN  ('03')\n" +
                    "                         AND HLT.POSITION_NAME in ('นักวิจัย')\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYB ON DD.CALENDAR_YEAR = QRYB.CALENDAR_YEAR\n" +
                    "             WHERE DD.CALENDAR_YEAR = :year\n" +
                    "             AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                         )\n" +
                    "UNION ALL \n" +
                    "SELECT DISTINCT DD.CALENDAR_YEAR , 'Doctoral' AS EDUCATION_LEVEL_NAME ,ROUND(DECIMAL(DECIMAL(QRYB.NO_OF_EMP)/QRYA.NO_OF_EMP_ALL*100,10,2),0) AS PERCENT\n" +
                    "            FROM HR_FACT_EMPLOYEE HFE\n" +
                    "            INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "            INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "            INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP_ALL\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                         )\n" +
                    "                         AND HLT.POSITION_NAME in ('นักวิจัย')\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYA ON DD.CALENDAR_YEAR = QRYA.CALENDAR_YEAR\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                         )\n" +
                    "                         AND ECL.EDUCATION_LEVEL_GROUP_CODE IN  ('04')\n" +
                    "                         AND HLT.POSITION_NAME in ('นักวิจัย')\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYB ON DD.CALENDAR_YEAR = QRYB.CALENDAR_YEAR\n" +
                    "            WHERE DD.CALENDAR_YEAR = :year\n" +
                    "            AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                  FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                  INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                  WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                  )");
        logger.info("getEmployeeQuery5->"+sb.toString());
            Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
            List obj=query.getResultList();
            List<Object[]> results=null;
            if(obj!=null) {
                results = (List<Object[]>) obj;
            }
            return results;
    }
    private List<Object[]> getEmployeeQuery6(String year){
            StringBuffer sb = new StringBuffer("SELECT DISTINCT DD.CALENDAR_YEAR , 'Bachelor''s & Lower' AS EDUCATION_LEVEL_NAME ,ROUND(DECIMAL(DECIMAL(QRYB.NO_OF_EMP)/QRYA.NO_OF_EMP_ALL*100,10,2),0) AS PERCENT\n" +
                    "            FROM HR_FACT_EMPLOYEE HFE\n" +
                    "            INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "            INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "            INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP_ALL\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                              )\n" +
                    "                         AND HLT.POSITION_NAME in ('กลุ่มงานช่าง','ชำนาญการ  ','ชำนาญงาน','อื่นๆ','กลุ่มงานบริการพื้นฐาน','ปฏิบัติการ','ปฏิบัติงาน','กลุ่มงานสนับสนุน','ชำนาญการพิเศษ','สายวิชาชีพอื่นๆ')\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYA ON DD.CALENDAR_YEAR = QRYA.CALENDAR_YEAR\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                              )\n" +
                    "                         AND ECL.EDUCATION_LEVEL_GROUP_CODE IN  ('01','02')\n" +
                    "                         AND HLT.POSITION_NAME in ('กลุ่มงานช่าง','ชำนาญการ  ','ชำนาญงาน','อื่นๆ','กลุ่มงานบริการพื้นฐาน','ปฏิบัติการ','ปฏิบัติงาน','กลุ่มงานสนับสนุน','ชำนาญการพิเศษ','สายวิชาชีพอื่นๆ')\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYB ON DD.CALENDAR_YEAR = QRYB.CALENDAR_YEAR\n" +
                    "           WHERE DD.CALENDAR_YEAR = :year\n" +
                    "           AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                 FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                 INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                 WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                              )\n" +
                    " \n" +
                    "UNION ALL \n" +
                    "SELECT DISTINCT DD.CALENDAR_YEAR , 'Master''s' AS EDUCATION_LEVEL_NAME ,ROUND(DECIMAL(DECIMAL(QRYB.NO_OF_EMP)/QRYA.NO_OF_EMP_ALL*100,10,2),0)AS PERCENT\n" +
                    "            FROM HR_FACT_EMPLOYEE HFE\n" +
                    "            INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "            INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "            INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP_ALL\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                              )\n" +
                    "                         AND HLT.POSITION_NAME in ('กลุ่มงานช่าง','ชำนาญการ  ','ชำนาญงาน','อื่นๆ','กลุ่มงานบริการพื้นฐาน','ปฏิบัติการ','ปฏิบัติงาน','กลุ่มงานสนับสนุน','ชำนาญการพิเศษ','สายวิชาชีพอื่นๆ')\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYA ON DD.CALENDAR_YEAR = QRYA.CALENDAR_YEAR\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                              )\n" +
                    "                         AND ECL.EDUCATION_LEVEL_GROUP_CODE IN  ('03')\n" +
                    "                         AND HLT.POSITION_NAME in ('กลุ่มงานช่าง','ชำนาญการ  ','ชำนาญงาน','อื่นๆ','กลุ่มงานบริการพื้นฐาน','ปฏิบัติการ','ปฏิบัติงาน','กลุ่มงานสนับสนุน','ชำนาญการพิเศษ','สายวิชาชีพอื่นๆ')\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYB ON DD.CALENDAR_YEAR = QRYB.CALENDAR_YEAR\n" +
                    "             WHERE DD.CALENDAR_YEAR = :year\n" +
                    "             AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                              )\n" +
                    "UNION ALL \n" +
                    "SELECT DISTINCT DD.CALENDAR_YEAR , 'Doctoral' AS EDUCATION_LEVEL_NAME ,ROUND(DECIMAL(DECIMAL(QRYB.NO_OF_EMP)/QRYA.NO_OF_EMP_ALL*100,10,2),0) AS PERCENT\n" +
                    "            FROM HR_FACT_EMPLOYEE HFE\n" +
                    "            INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "            INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "            INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP_ALL\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                              )\n" +
                    "                         AND HLT.POSITION_NAME in ('กลุ่มงานช่าง','ชำนาญการ  ','ชำนาญงาน','อื่นๆ','กลุ่มงานบริการพื้นฐาน','ปฏิบัติการ','ปฏิบัติงาน','กลุ่มงานสนับสนุน','ชำนาญการพิเศษ','สายวิชาชีพอื่นๆ')\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYA ON DD.CALENDAR_YEAR = QRYA.CALENDAR_YEAR\n" +
                    "            INNER JOIN (SELECT DD.CALENDAR_YEAR,COUNT(HFE.EMP_KEY) AS NO_OF_EMP\n" +
                    "                         FROM HR_FACT_EMPLOYEE HFE\n" +
                    "                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EMP_LEVEL_TYPE HLT ON HFE.EMP_LEVEL_TYPE_KEY = HLT.EMP_LEVEL_TYPE_KEY\n" +
                    "                         INNER JOIN HR_DIM_EDUCATION_LEVEL ECL ON HFE.EDUCATION_LEVEL_KEY = ECL.EDUCATION_LEVEL_KEY\n" +
                    "                         WHERE DD.CALENDAR_YEAR = :year\n" +
                    "                         AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                         FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                         INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                         WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                              )\n" +
                    "                         AND ECL.EDUCATION_LEVEL_GROUP_CODE IN  ('04')\n" +
                    "                         AND HLT.POSITION_NAME in ('กลุ่มงานช่าง','ชำนาญการ  ','ชำนาญงาน','อื่นๆ','กลุ่มงานบริการพื้นฐาน','ปฏิบัติการ','ปฏิบัติงาน','กลุ่มงานสนับสนุน','ชำนาญการพิเศษ','สายวิชาชีพอื่นๆ')\n" +
                    "                         GROUP BY DD.CALENDAR_YEAR)QRYB ON DD.CALENDAR_YEAR = QRYB.CALENDAR_YEAR\n" +
                    "            WHERE DD.CALENDAR_YEAR = :year\n" +
                    "            AND HFE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY) \n" +
                    "                                   FROM HR_FACT_EMPLOYEE HFE \n" +
                    "                                   INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n" +
                    "                                   WHERE DD.CALENDAR_YEAR = :year \n" +
                    "                                              )");
        logger.info("getEmployeeQuery6->"+sb.toString());
            Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
            List obj=query.getResultList();
            List<Object[]> results=null;
            if(obj!=null) {
                results = (List<Object[]>) obj;
            }
            return results;
    }
    private List<Object[]> getAcademicQuery1(String year){
        StringBuffer sb = new StringBuffer("SELECT  'FACULTIES / SCHOOLS' AS NAME_GROUP,\n" +
                "COUNT (DISTINCT DF.FACULTY_CODE) AS NO_OF\n" +
                "FROM FACT_ALL_STUDENT FAS\n" +
                "INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "INNER JOIN DIM_FIELD DF ON FAS.FIELD_KEY = DF.FIELD_KEY\n" +
                "WHERE DS.ACADEMIC_YEAR = :year\n" +
                "AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "AND DF.FACULTY_CODE != '13400000'\n" +
                "GROUP BY 1\n" +
                "UNION ALL\n" +
                "SELECT  'INSTITUTE' AS NAME_GROUP,\n" +
                "COUNT (DISTINCT DF.FACULTY_CODE) AS NO_OF\n" +
                "FROM FACT_ALL_STUDENT FAS\n" +
                "INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "INNER JOIN DIM_FIELD DF ON FAS.FIELD_KEY = DF.FIELD_KEY\n" +
                "WHERE DS.ACADEMIC_YEAR = :year\n" +
                "AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "AND DF.FACULTY_CODE = '13400000'\n" +
                "GROUP BY 1");
        logger.info("getAcademicQuery1->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List obj=query.getResultList();
        List<Object[]> results=null;
        if(obj!=null) {
            results = (List<Object[]>) obj;
        }
        return results;
    }
    private List<Object[]> getProgramQuery1(String year){
        StringBuffer sb = new StringBuffer("SELECT QRYB.EDUCATION_LEVEL_GROUP , QRYB.NO_OF ,ROUND (DECIMAL(DECIMAL(QRYB.NO_OF,10,4 )/ QRYA.NO_OF_ALL *100,10,3),0) AS PERCENT\n" +
                "FROM(\n" +
                "    SELECT \n" +
                "    DS.ACADEMIC_YEAR,\n" +
                "    COUNT (DISTINCT DP.PROGRAM_CODE) AS NO_OF_ALL\n" +
                "    FROM FACT_ALL_STUDENT FAS\n" +
                "    INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "    INNER JOIN DIM_PROGRAM DP ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY \n" +
                "    WHERE DS.ACADEMIC_YEAR = :year\n" +
                "    AND DP.EDUCATION_LEVEL_GROUP IN('ป.ตรี','บัณฑิตศึกษา')\n" +
                "    AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "    GROUP BY DS.ACADEMIC_YEAR\n" +
                "    )QRYA\n" +
                "INNER JOIN (\n" +
                "        SELECT  'Programs for Graduate' AS EDUCATION_LEVEL_GROUP,\n" +
                "        DS.ACADEMIC_YEAR ,\n" +
                "        COUNT (DISTINCT DP.PROGRAM_CODE) AS NO_OF\n" +
                "        FROM FACT_ALL_STUDENT FAS\n" +
                "        INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "        INNER JOIN DIM_PROGRAM DP ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY \n" +
                "        WHERE DS.ACADEMIC_YEAR = :year\n" +
                "        AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "        AND DP.EDUCATION_LEVEL_GROUP = 'บัณฑิตศึกษา'\n" +
                "        GROUP BY 1,DS.ACADEMIC_YEAR\n" +
                "        UNION ALL\n" +
                "        SELECT  'Programs for Ungraduate' AS DUCATION_LEVEL_GROUP,\n" +
                "        DS.ACADEMIC_YEAR,\n" +
                "        COUNT (DISTINCT DP.PROGRAM_CODE) AS NO_OF\n" +
                "        FROM FACT_ALL_STUDENT FAS\n" +
                "        INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "        INNER JOIN DIM_PROGRAM DP ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY \n" +
                "        WHERE DS.ACADEMIC_YEAR = :year\n" +
                "        AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "        AND DP.EDUCATION_LEVEL_GROUP = 'ป.ตรี'\n" +
                "        GROUP BY 1,DS.ACADEMIC_YEAR\n" +
                "        )QRYB ON QRYA.ACADEMIC_YEAR = QRYB.ACADEMIC_YEAR");
        logger.info("getProgramQuery1->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List obj=query.getResultList();
        List<Object[]> results=null;
        if(obj!=null) {
            results = (List<Object[]>) obj;
        }
        return results;
    }

    private Object[] getProgramQuery2(String year){
        StringBuffer sb = new StringBuffer("SELECT  'International Program' AS EDUCATION_LEVEL_GROUP,\n" +
                "COUNT (DISTINCT DP.PROGRAM_CODE) AS NO_OF\n" +
                "FROM FACT_ALL_STUDENT FAS\n" +
                "INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "INNER JOIN DIM_PROGRAM DP ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY \n" +
                "WHERE DS.ACADEMIC_YEAR = :year\n" +
                "AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "AND DP.ARRANGE_TYPE = 'หลักสูตรนานาชาติ'\n" +
                "GROUP BY 1");
        logger.info("getProgramQuery2->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List<Object[]> obj=query.getResultList();
        Object[] results=null;
        if(obj!=null && obj.size()>0) {
            results = obj.get(0);
        }
        return results;
    }

    private List<Object[]> getProgramQuery3(String year){
        StringBuffer sb = new StringBuffer("SELECT  'International Program for Undergraduate' AS EDUCATION_LEVEL_GROUP,\n" +
                "COUNT (DISTINCT DP.PROGRAM_CODE) AS NO_OF\n" +
                "FROM FACT_ALL_STUDENT FAS\n" +
                "INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "INNER JOIN DIM_PROGRAM DP ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY \n" +
                "WHERE DS.ACADEMIC_YEAR = :year\n" +
                "AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "AND DP.EDUCATION_LEVEL_GROUP = 'ป.ตรี'\n" +
                "AND DP.ARRANGE_TYPE = 'หลักสูตรนานาชาติ'\n" +
                "GROUP BY 1\n" +
                "UNION ALL\n" +
                "SELECT  'International Program for Graduate' AS DUCATION_LEVEL_GROUP,\n" +
                "COUNT (DISTINCT DP.PROGRAM_CODE) AS NO_OF\n" +
                "FROM FACT_ALL_STUDENT FAS\n" +
                "INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n" +
                "INNER JOIN DIM_PROGRAM DP ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY \n" +
                "WHERE DS.ACADEMIC_YEAR = :year\n" +
                "AND LEFT(DS.SEMESTER,1) = '1'\n" +
                "AND DP.EDUCATION_LEVEL_GROUP = 'บัณฑิตศึกษา'\n" +
                "AND DP.ARRANGE_TYPE = 'หลักสูตรนานาชาติ'\n" +
                "GROUP BY 1\n");
        logger.info("getProgramQuery3->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List obj=query.getResultList();
        List<Object[]> results=null;
        if(obj!=null) {
            results = (List<Object[]>) obj;
        }
        return results;
    }
    private Object[] getEmployabilityQuery1(String year){

        StringBuffer sb = new StringBuffer("SELECT DISTINCT GRW.ACADEMIC_YEAR  ,DECIMAL(DECIMAL(GRYB.NO_OF_UNDERGRADUATE,10,2)/GRYA.NO_OF_ALL *100,10,0 )AS UNDERGRADUATE\n" +
                "FROM FACT_GRADUATE_WORK GRW\n" +
                "INNER JOIN(SELECT GRW.ACADEMIC_YEAR ,\n" +
                "        SUM(GRW.NO_OF_GRADUATE) AS NO_OF_ALL\n" +
                "        FROM FACT_GRADUATE_WORK GRW\n" +
                "        INNER JOIN DIM_STUDENT DS ON GRW.STUDENT_KEY = DS.STUDENT_KEY \n" +
                "        WHERE GRW.ACADEMIC_YEAR = :year\n" +
                "        AND DS.EDUCATION_LEVEL_CODE =001\n" +
                "        GROUP BY GRW.ACADEMIC_YEAR\n" +
                "       )GRYA ON GRYA.ACADEMIC_YEAR = GRW.ACADEMIC_YEAR \n" +
                "INNER JOIN(SELECT GRW.ACADEMIC_YEAR ,\n" +
                "        SUM(GRW.NO_OF_EMPLOY) AS NO_OF_UNDERGRADUATE\n" +
                "        FROM FACT_GRADUATE_WORK GRW\n" +
                "        INNER JOIN DIM_STUDENT DS ON GRW.STUDENT_KEY = DS.STUDENT_KEY \n" +
                "        WHERE GRW.ACADEMIC_YEAR = :year\n" +
                "        AND DS.EDUCATION_LEVEL_CODE =001\n" +
                "        GROUP BY GRW.ACADEMIC_YEAR\n" +
                "       )GRYB ON GRYB.ACADEMIC_YEAR = GRW.ACADEMIC_YEAR \n" +
                "WHERE GRW.ACADEMIC_YEAR = :year ");
        logger.info("getEmployabilityQuery1->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List<Object[]> obj=query.getResultList();
        Object[] results=null;
        if(obj!=null && obj.size()>0) {
            results= obj.get(0);
        }
        return results;
    }
    private Object[] getEmployabilityQuery2(String year){
        StringBuffer sb = new StringBuffer("SELECT DISTINCT GRW.ACADEMIC_YEAR  ,DECIMAL(DECIMAL(GRYB.NO_OF_GRADUATE,10,2)/GRYA.NO_OF_ALL *100,10,0 )AS GRADUATE\n" +
                "FROM FACT_GRADUATE_WORK GRW\n" +
                "INNER JOIN(SELECT GRW.ACADEMIC_YEAR ,\n" +
                "        SUM(GRW.NO_OF_GRADUATE) AS NO_OF_ALL\n" +
                "        FROM FACT_GRADUATE_WORK GRW\n" +
                "        INNER JOIN DIM_STUDENT DS ON GRW.STUDENT_KEY = DS.STUDENT_KEY \n" +
                "        WHERE GRW.ACADEMIC_YEAR = :year\n" +
                "        AND DS.EDUCATION_LEVEL_CODE  IN (002,003)\n" +
                "        GROUP BY GRW.ACADEMIC_YEAR\n" +
                "       )GRYA ON GRYA.ACADEMIC_YEAR = GRW.ACADEMIC_YEAR \n" +
                "INNER JOIN(SELECT GRW.ACADEMIC_YEAR ,\n" +
                "        SUM(GRW.NO_OF_EMPLOY) AS NO_OF_GRADUATE\n" +
                "        FROM FACT_GRADUATE_WORK GRW\n" +
                "        INNER JOIN DIM_STUDENT DS ON GRW.STUDENT_KEY = DS.STUDENT_KEY \n" +
                "        WHERE GRW.ACADEMIC_YEAR = :year\n" +
                "        AND DS.EDUCATION_LEVEL_CODE  IN (002,003)\n" +
                "        GROUP BY GRW.ACADEMIC_YEAR\n" +
                "       )GRYB ON GRYB.ACADEMIC_YEAR = GRW.ACADEMIC_YEAR \n" +
                "WHERE GRW.ACADEMIC_YEAR = :year");
        logger.info("getEmployabilityQuery2->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List<Object[]> obj=query.getResultList();
        Object[] results=null;
        if(obj!=null && obj.size()>0) {
            results= obj.get(0);
        }
        return results;
    }


    private List<Object[]> getBudgetQuery1(String year){
        StringBuffer sb = new StringBuffer("SELECT QRYA.ITEM_GROUP_NAME,QRYA.LEVEL_LINE," +
                " DECIMAL(DECIMAL (QRYA.ACTUAL_AMOUNT,10,0) / AMOUNT * 100,10,3) AS PERCENT\n" +
                "    FROM (SELECT QRYA.ITEM_GROUP_NAME,QRYA.LEVEL_LINE,QRYA.FISCAL_YEAR,SUM(QRYA.ACTUAL_AMOUNT) AS ACTUAL_AMOUNT\n" +
                "    FROM (\n" +
                "            SELECT DD.FISCAL_YEAR,\n" +
                "            FYD.ITEM_GROUP_NAME,\n" +
                "            FYD.LEVEL_CODE||FYD.LEVEL_LINE AS LEVEL_LINE,\n" +
                "            FYD.ACTUAL_AMOUNT\n" +
                "                    FROM FN_YEARLY_DEPARTMENT_TEST_EIS FYD\n" +
                "                    INNER JOIN DIM_DATE DD ON FYD.YEAR_KEY = DD.DATE_KEY\n" +
                "                    WHERE FYD.TEMPLATE_CODE = 6\n" +
                "                    AND FYD.PARENT = 11\n" +
                "                    AND DD.FISCAL_YEAR = :year\n" +
                "    )QRYA\n" +
                "    GROUP BY QRYA.ITEM_GROUP_NAME,QRYA.LEVEL_LINE,QRYA.FISCAL_YEAR )QRYA\n" +
                "    INNER JOIN (SELECT DD.FISCAL_YEAR,\n" +
                "                sum(FYD.ACTUAL_AMOUNT) as AMOUNT\n" +
                "    FROM FN_YEARLY_DEPARTMENT_TEST_EIS FYD\n" +
                "    INNER JOIN DIM_DATE DD ON FYD.YEAR_KEY = DD.DATE_KEY\n" +
                "    WHERE FYD.TEMPLATE_CODE = 6\n" +
                "    AND FYD.PARENT = 11\n" +
                "    AND DD.FISCAL_YEAR = :year\n" +
                "    GROUP BY DD.FISCAL_YEAR )QRYB ON QRYA.FISCAL_YEAR = QRYB.FISCAL_YEAR\n" +
                "    ORDER BY QRYA.LEVEL_LINE\n" +
                "     ");
        logger.info("getBudgetQuery1->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List obj=query.getResultList();
        List<Object[]> results=null;
        if(obj!=null) {
            results = (List<Object[]>) obj;
        }
        return results;
    }

    private Object[] getBudgetQuery2(String year){


        StringBuffer sb = new StringBuffer("SELECT  \n" +
                "sum(FYD.ACTUAL_AMOUNT)/1000000 as AMOUNT \n" +
                ",varchar_format(sum(FYD.ACTUAL_AMOUNT)/1000000,'999,999,999,999,999,999') as AMOUNT_FORMAT "+
                "FROM FN_YEARLY_DEPARTMENT_TEST_EIS FYD \n" +
                "INNER JOIN DIM_DATE DD ON FYD.YEAR_KEY = DD.DATE_KEY  \n" +
                "WHERE FYD.TEMPLATE_CODE = 6 \n" +
                "AND FYD.PARENT = 11 \n" +
                "AND DD.FISCAL_YEAR = :year ");
        logger.info("getBudgetQuery2->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List<Object[]> obj=query.getResultList();
        Object[] results=null;
        if(obj!=null && obj.size()>0) {
            results= obj.get(0);
        }
        return results;
    }
    private List<Object[]> getBudgetQuery3(String year){
        StringBuffer sb = new StringBuffer("SELECT QRYA.ITEM_GROUP_NAME,QRYA.LEVEL_LINE," +
                " DECIMAL(DECIMAL (QRYA.ACTUAL_AMOUNT,10,0) / AMOUNT * 100,10,3) AS PERCENT \n" +
                "FROM (SELECT QRYA.ITEM_GROUP_NAME,QRYA.LEVEL_LINE,QRYA.FISCAL_YEAR,SUM(QRYA.ACTUAL_AMOUNT) AS ACTUAL_AMOUNT \n" +
                "        FROM ( \n" +
                "            SELECT DD.FISCAL_YEAR, \n" +
                "            FYD.ITEM_GROUP_NAME, \n" +
                "            FYD.LEVEL_CODE||FYD.LEVEL_LINE AS LEVEL_LINE, \n" +
                "            FYD.ACTUAL_AMOUNT \n" +
                "            FROM FN_YEARLY_DEPARTMENT_TEST_EIS FYD \n" +
                "            INNER JOIN DIM_DATE DD ON FYD.YEAR_KEY = DD.DATE_KEY  \n" +
                "            WHERE FYD.TEMPLATE_CODE = 6 \n" +
                "            AND FYD.PARENT = 14 \n" +
                "            AND DD.FISCAL_YEAR = :year \n" +
                "            )QRYA \n" +
                "        GROUP BY QRYA.ITEM_GROUP_NAME,QRYA.LEVEL_LINE,QRYA.FISCAL_YEAR )QRYA \n" +
                "INNER JOIN (SELECT DD.FISCAL_YEAR, \n" +
                "             sum(FYD.ACTUAL_AMOUNT) as AMOUNT \n" +
                "            FROM FN_YEARLY_DEPARTMENT_TEST_EIS FYD \n" +
                "            INNER JOIN DIM_DATE DD ON FYD.YEAR_KEY = DD.DATE_KEY  \n" +
                "            WHERE FYD.TEMPLATE_CODE = 6 \n" +
                "            AND FYD.PARENT = 14 \n" +
                "            AND DD.FISCAL_YEAR = :year \n" +
                "            GROUP BY DD.FISCAL_YEAR )QRYB ON QRYA.FISCAL_YEAR = QRYB.FISCAL_YEAR \n" +
                "ORDER BY QRYA.LEVEL_LINE");
        /*
        StringBuffer sb = new StringBuffer("SELECT QRYA.ITEM_GROUP_NAME," +
                "QRYA.LEVEL_LINE,SUM(QRYA.ACTUAL_AMOUNT) AS  ACTUAL_AMOUNT,\n" +
                "varchar_format(SUM(QRYA.ACTUAL_AMOUNT),'999,999,999,999,999,999,999') AS  ACTUAL_AMOUNT_FORMAT \n" +
                "FROM (\n" +
                "SELECT DD.FISCAL_YEAR,\n" +
                "FYD.ITEM_GROUP_NAME,\n" +
                "FYD.LEVEL_CODE||FYD.LEVEL_LINE AS LEVEL_LINE,\n" +
                "FYD.ACTUAL_AMOUNT\n" +
                "FROM FN_YEARLY_DEPARTMENT_TEST_EIS FYD\n" +
                "INNER JOIN DIM_DATE DD ON FYD.YEAR_KEY = DD.DATE_KEY \n" +
                "WHERE FYD.TEMPLATE_CODE = 6\n" +
                "AND FYD.PARENT  = 14\n" +
                "AND DD.FISCAL_YEAR =  :year\n" +
                " )QRYA\n" +
                "GROUP BY QRYA.ITEM_GROUP_NAME,QRYA.LEVEL_LINE\n" +
                "order by QRYA.LEVEL_LINE ");
                */
        logger.info("getBudgetQuery3->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List obj=query.getResultList();
        List<Object[]> results=null;
        if(obj!=null) {
            results = (List<Object[]>) obj;
        }
        return results;
    }



    private Object[] getBudgetQuery4(String year){
        StringBuffer sb = new StringBuffer("SELECT \n" +
                "sum(FYD.ACTUAL_AMOUNT)/1000000 as AMOUNT\n" +
                ",varchar_format(sum(FYD.ACTUAL_AMOUNT)/1000000,'999,999,999,999,999,999') as AMOUNT_FORMAT "+
                "FROM FN_YEARLY_DEPARTMENT_TEST_EIS FYD\n" +
                "INNER JOIN DIM_DATE DD ON FYD.YEAR_KEY = DD.DATE_KEY \n" +
                "WHERE FYD.TEMPLATE_CODE = 6\n" +
                "AND FYD.PARENT = 14\n" +
                "AND DD.FISCAL_YEAR = :year ");
        logger.info("getBudgetQuery4->"+sb.toString());
        Query query= entityManager.createNativeQuery(sb.toString());
        query.setParameter("year",year);
        List<Object[]> obj=query.getResultList();
        Object[] results=null;
        if(obj!=null && obj.size()>0) {
            results= obj.get(0);
        }
        return results;
    }

    public List<Year> listYear() throws DataAccessException {
        StringBuffer sb = new StringBuffer(" SELECT DISTINCT DD.CALENDAR_YEAR AS    YEAR_TH,\n" +
                "   DD.CALENDAR_YEAR- (543) AS  YEAR_ENG \n" +
                "    FROM DIM_DATE DD\n" +
                "    WHERE DD.CALENDAR_YEAR <= YEAR(CURRENT DATE)+ (543)\n" +
                "    ORDER BY CALENDAR_YEAR DESC");
        Query query= entityManager.createNativeQuery(sb.toString());
        List obj=query.getResultList();
        List<java.lang.Object[]> results=null;
        List<Year> years=new ArrayList<Year>();
        if(obj!=null) {
            results = (List<java.lang.Object[]>) obj;
            if(results!=null && results.size()>0){
                for (int i = 0; i <results.size() ; i++) {

                    java.lang.Object[] year_array=(java.lang.Object[])results.get(i);
                    java.lang.Integer yearTH=(java.lang.Integer)year_array[0];
                    java.lang.Integer yearENG=(java.lang.Integer)year_array[1];
                    Year year =new Year(yearTH+"",yearENG+"");
                    years.add(year);
                }
            }
        }
        return  years;
    }
    public DashboardM getDashboard(DashboardM param) {
        // TODO Auto-generated method stub
        DashboardM dashboardM=new DashboardM();
        String year=param.getYear();
        String lang=param.getLang();

        if(!(year!=null && year.length()>0))
            year="2558";
        if(!(lang!=null && lang.length()>0))
            lang="th";

        if (lang.equals("eng")) {
            year = (Integer.parseInt(year) + 543)+"";
        }
        // localhost:3000/v1/dashboard
        StudentM studentM =new StudentM();
        AcademicM academicM =new AcademicM();
        ProgramM programM=new ProgramM();
        EmployeeM employeeM=new EmployeeM();
        EmployabilityM employabilityM=new EmployabilityM();
        BudgetM budgetM=new BudgetM();
        Object[] results=getStudentQuery1(year);
        if(results!=null ){
            studentM.setNoof_format(results[1]!=null?(((String)results[1]).trim()):null);
            studentM.setNoof(results[2]!=null?((Integer)results[2]):null);
        }
        results=getStudentQuery2(year);
        if(results!=null ){
            studentM.setPercent_undergraduate(results[1]!=null?((BigDecimal)results[1]):null);
            studentM.setNo_of_undergraduate_format(results[2]!=null?(((String)results[2]).trim()):null);
            studentM.setNo_of_undergraduate(results[3]!=null?((Integer)results[3]):null);
        }
        results=getStudentQuery3(year);
        if(results!=null ){
            studentM.setPercent_graduate(results[1]!=null?((BigDecimal) results[1]):null);
            studentM.setNo_of_graduate(results[2]!=null?((Integer)results[2]):null);
            studentM.setNo_of_graduate_format(results[3]!=null?(((String)results[3]).trim()):null);
        }
        results=getStudentQuery4(year);
        if(results!=null ){
            studentM.setPercent_male(results[1]!=null?((BigDecimal)results[1]):null);
            studentM.setPercent_female(results[2]!=null?((BigDecimal)results[2]):null);
        }

        results=getStudentQuery5(year);
        if(results!=null ){
            System.out.println(results[1]+","+results[2]);
            studentM.setNo_of_student(results[1]!=null?(Integer.valueOf((Integer)results[1])):null);
            studentM.setFaculty_ratio(results[2]!=null?(Integer.valueOf((Integer)results[2])):null);
        }

        List<Object[]> results_list=getAcademicQuery1(year);
        if(results_list!=null && results_list.size()>0){
            results=results_list.get(0); // INSTITUTE
            if(results!=null ){
                academicM.setInstitute(results[1]!=null?((Integer)results[1]):null);
            }
            results=results_list.get(1); // FACULTIES / SCHOOLS
            if(results!=null ){
                academicM.setFaculties_schools(results[1]!=null?((Integer)results[1]):null);
            }
        }

        results_list=getProgramQuery1(year);
        if(results_list!=null && results_list.size()>0){
            results=results_list.get(0);// Programs for Undergraduate
            if(results!=null ){
                programM.setPrograms_for_undergraduate(results[1]!=null?((Integer)results[1]):null);
                programM.setPercent_programs_for_undergraduate(results[2]!=null?((BigDecimal) results[2]):null);
            }
            results=results_list.get(1);  // Programs for Graduate
            if(results!=null ){
                programM.setPrograms_for_graduate(results[1]!=null?((Integer)results[1]):null);
                programM.setPercent_programs_for_graduate(results[2]!=null?((BigDecimal)results[2]):null);
            }
        }

        results=getProgramQuery2(year);
        if(results!=null ){
            programM.setInternational_program(results[1]!=null?((Integer)results[1]):null);
        }

        results_list=getProgramQuery3(year);
        if(results_list!=null && results_list.size()>0){
            results=results_list.get(0); // International Program for Graduate
            if(results!=null ){
                programM.setInternational_program_for_graduate(results[1]!=null?((Integer)results[1]):null);
            }
            results=results_list.get(1); // International Program for Undergraduate
            if(results!=null ){
                programM.setInternational_program_for_undergraduate(results[1]!=null?((Integer)results[1]):null);
            }
        }

        results=getEmployeeQuery1(year);
        if(results!=null ){
            employeeM.setPercent_faculty_member_doctor(results[1]!=null?((BigDecimal)results[1]):null);
            employeeM.setFaculty_member_doctor(results[2]!=null?((Integer)results[2]):null);
            employeeM.setFaculty_member_doctor_format(results[3]!=null?(((String)results[3]).trim()):null);
        }

        results=getEmployeeQuery2(year);
        if(results!=null ){
            employeeM.setFaculty_member(results[1]!=null?((Integer)results[1]):null);
            employeeM.setFaculty_member_format(results[2]!=null?(((String)results[2]).trim()):null);
        }

        results=getEmployeeQuery3(year);
        if(results!=null ){
            employeeM.setFull_time(results[1]!=null?((Integer)results[1]):null);
            employeeM.setFull_time_format(results[2]!=null?(((String)results[2]).trim()):null);
        }

        // factory member
        results_list=getEmployeeQuery4(year);
        if(results_list!=null && results_list.size()>0){
            /*
            for (int k=0;k<results_list.size();k++) {

                results = results_list.get(k); // Bachelor's & Lower
                if (results != null) {
                    String label = (String) results[0];
                    if (label.equals("Bachelor's & Lower")) {
                        employeeM.setFactory_member_bachelor_lower_percent(results[2]!=null?((BigDecimal) results[2]):null);
                    } else if (label.equals("Master's")) {
                        employeeM.setFactory_member_master_percent(results[2]!=null?((BigDecimal)results[2]):null);
                    } else if (label.equals("Doctoral")) {
                        employeeM.setFactory_member_doctoral_percent(results[2]!=null?((BigDecimal)results[2]):null);
                    }

                }
            }
            */
            /* */
            results=results_list.get(0); // Bachelor's & Lower
            if(results!=null ){
                employeeM.setFactory_member_bachelor_lower_percent(results[2]!=null?((BigDecimal) results[2]):null);
            }
            results=results_list.get(1); // Master's
            if(results!=null ){
                employeeM.setFactory_member_master_percent(results[2]!=null?((BigDecimal)results[2]):null);
            }
            results=results_list.get(2); // Doctoral's
            if(results!=null ){
                employeeM.setFactory_member_doctoral_percent(results[2]!=null?((BigDecimal)results[2]):null);
            }


        }

        // researcher
        results_list=getEmployeeQuery5(year);
        if(results_list!=null && results_list.size()>0){

                results=results_list.get(0); // Bachelor's & Lower
                if(results!=null ){
                    employeeM.setResearcher_bachelor_lower_percent(results[2]!=null?((BigDecimal)results[2]):null);
                }
                results=results_list.get(1); // Master's
                if(results!=null ){
                    employeeM.setResearcher_master_percent(results[2]!=null?((BigDecimal)results[2]):null);
                }
                results=results_list.get(2); // Doctoral's
                if(results!=null ){
                    employeeM.setResearcher_doctoral_percent(results[2]!=null?((BigDecimal)results[2]):null);
                }

            }


        // fulltime
        results_list=getEmployeeQuery6(year);
        if(results_list!=null && results_list.size()>0){
            results=results_list.get(0); // Bachelor's & Lower
            if(results!=null ){
                employeeM.setFull_time_bachelor_lower_percent(results[2]!=null?((BigDecimal)results[2]):null);
            }
            results=results_list.get(1); // Master's
            if(results!=null ){
                employeeM.setFull_time_master_percent(results[2]!=null?((BigDecimal)results[2]):null);
            }
            results=results_list.get(2); // Doctoral's
            if(results!=null ){
                employeeM.setFull_time_doctoral_percent(results[2]!=null?((BigDecimal)results[2]):null);
            }

        }


        results=getEmployabilityQuery1(year);
        if(results!=null ){
            employabilityM.setUndergraduate(results[1]!=null?((BigDecimal)results[1]):null);
        }

        results=getEmployabilityQuery2(year);
        if(results!=null ){
            employabilityM.setGraduate(results[1]!=null?((BigDecimal)results[1]):null);
        }

        results_list=getBudgetQuery1(year);
        if(results_list!=null && results_list.size()>0){
            List<String[]> incomes=new ArrayList<String[]>();
            for(int i=0;i<results_list.size();i++){
                String[] income=new String[2];
                results=results_list.get(i);
                income[0]=results[0]!=null?((String)results[0]).trim():"";
                income[1]=results[1]!=null?((String)results[1]).trim():"";
                incomes.add(income);
            }
            budgetM.setIncome_list(incomes);
        }

        results=getBudgetQuery2(year);
        if(results!=null ){
            budgetM.setIncome_amount(results[0]!=null?((BigDecimal) results[0]):null);
            budgetM.setIncome_amount_format(results[1]!=null?((String)results[1]).trim():null);
        }

        results_list=getBudgetQuery3(year);
        if(results_list!=null && results_list.size()>0){
            List<String[]> expenses=new ArrayList<String[]>();
            for(int i=0;i<results_list.size();i++){
                String[] expense=new String[2];
                results=results_list.get(i);
                expense[0]=results[0]!=null?((String)results[0]).trim():"";
                expense[1]=results[1]!=null?((String)results[1]).trim():"";
                expenses.add(expense);
            }
            budgetM.setExpense_list(expenses);
        }
        results=getBudgetQuery4(year);
        if(results!=null ){
            budgetM.setExpense_amount(results[0]!=null?((BigDecimal) results[0]):null);
            budgetM.setExpense_amount_format(results[1]!=null?((String)results[1]).trim():null);
        }

        dashboardM.setStudentM(studentM);
        dashboardM.setEmployeeM(employeeM);
        dashboardM.setEmployabilityM(employabilityM);
        dashboardM.setAcademicM(academicM);
        dashboardM.setBudgetM(budgetM);
        dashboardM.setProgramM(programM);
        return dashboardM;
    }


}
