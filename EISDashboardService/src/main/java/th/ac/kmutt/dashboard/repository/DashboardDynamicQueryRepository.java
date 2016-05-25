package th.ac.kmutt.dashboard.repository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import th.ac.kmutt.dashboard.constant.ServiceConstant;
import th.ac.kmutt.dashboard.domain.KissLanguage;
import th.ac.kmutt.dashboard.domain.KissQuery;
import th.ac.kmutt.dashboard.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by imake on 01/12/2015.
 */

@Repository("dashboardQueryRepository")
@Transactional
public class DashboardDynamicQueryRepository {
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
    private HashMap<String,KissQuery >getKissQueryDanamic(){
        Query query = kmuttEntityManager.createQuery("select  p from KissQuery p " +
                "", KissQuery.class);
        List<KissQuery> kissQuerys = (List<KissQuery>) query.getResultList();
        HashMap<String,KissQuery> queryMap=new HashMap<String,KissQuery>(kissQuerys.size());
        for (KissQuery kissQuery:kissQuerys){
            queryMap.put(kissQuery.getId(),kissQuery);
        }
        return queryMap;
    }
    private List<Object[]> getResult(HashMap<String,KissQuery > queryMap,String index, Integer year, String lang) {
        Query query = null;
        /*kmuttEntityManager.createQuery("select  p from KissQuery p " +
                "where p.id=:id", KissQuery.class);
        query.setParameter("id", index);
        KissQuery kissQuery = (KissQuery) query.getSingleResult();
        */
        KissQuery kissQuery=queryMap.get(index);
        StringBuffer sb = new StringBuffer(kissQuery.getQueryStatement());
        logger.info("id["+index+"] "+kissQuery.getDesc()+"->"+sb.toString());
        query = entityManager.createNativeQuery(sb.toString());
        if (lang.equals("eng")) {
            year = year + 543;
        }
        query.setParameter("year", year);
        List obj = query.getResultList();
        List<Object[]> results = null;
        if (obj != null) {
            results = (List<Object[]>) obj;
        }
        return results;

    }
    public DashboardM getDashboard(DashboardM param) {
        // TODO Auto-generated method stub
        DashboardM dashboardM=new DashboardM();
        String year=param.getYear();
        if(year==null)
            year="2558";
        // localhost:3000/v1/dashboard
        StudentM studentM =new StudentM();
        AcademicM academicM =new AcademicM();
        ProgramM programM=new ProgramM();
        EmployeeM employeeM=new EmployeeM();
        EmployabilityM employabilityM=new EmployabilityM();
        BudgetM budgetM=new BudgetM();
        Integer yearInt=Integer.valueOf(year);
        String lang=param.getLang();
        HashMap<String,KissQuery > queryMap=getKissQueryDanamic();
        List<Object[]> results=getResult(queryMap,"1",yearInt,lang);
        Object[] result=null;
        if(results!=null && results.size()>0 ){
            result=(Object[])results.get(0);
            studentM.setNoof_format(result[1]!=null?(((String)result[1]).trim()):null);
            studentM.setNoof(result[2]!=null?((Integer)result[2]):null);
        }
        results=getResult(queryMap,"2",yearInt,lang);
        if(results!=null && results.size()>0 ){
            result=(Object[])results.get(0);
            studentM.setPercent_undergraduate(result[1]!=null?((BigDecimal)result[1]):null);
            studentM.setNo_of_undergraduate_format(result[2]!=null?(((String)result[2]).trim()):null);
            studentM.setNo_of_undergraduate(result[3]!=null?((Integer)result[3]):null);
        }
        results=getResult(queryMap,"3",yearInt,lang);
        if(results!=null && results.size()>0 ){
            result=(Object[])results.get(0);
            studentM.setPercent_graduate(result[1]!=null?((BigDecimal) result[1]):null);
            studentM.setNo_of_graduate(result[2]!=null?((Integer)result[2]):null);
            studentM.setNo_of_graduate_format(result[3]!=null?(((String)result[3]).trim()):null);
        }
        results=getResult(queryMap,"4",yearInt,lang);
        if(results!=null && results.size()>0 ){
            result=(Object[])results.get(0);
            studentM.setPercent_male(result[1]!=null?((BigDecimal)result[1]):null);
            studentM.setPercent_female(result[2]!=null?((BigDecimal)result[2]):null);
        }

        results=getResult(queryMap,"5",yearInt,lang);
        if(results!=null && results.size()>0 ){
            result=(Object[])results.get(0);
            System.out.println(result[1]+","+result[2]);
            studentM.setNo_of_student(result[1]!=null?(Integer.valueOf((Integer)result[1])):null);
            studentM.setFaculty_ratio(result[2]!=null?(Integer.valueOf((Integer)result[2])):null);
        }

        results=getResult(queryMap,"6",yearInt,lang);
        if(results!=null && results.size()>0){
            result=results.get(0); // INSTITUTE
            if(results!=null ){
                academicM.setInstitute(result[1]!=null?((Integer)result[1]):null);
            }
            result=results.get(1); // FACULTIES / SCHOOLS
            if(results!=null ){
                academicM.setFaculties_schools(result[1]!=null?((Integer)result[1]):null);
            }
        }

        results=getResult(queryMap,"7",yearInt,lang);//getProgramQuery1(year);
        if(results!=null && results.size()>0){
            result=results.get(0);// Programs for Undergraduate
            if(results!=null ){
                programM.setPrograms_for_undergraduate(result[1]!=null?((Integer)result[1]):null);
                programM.setPercent_programs_for_undergraduate(result[2]!=null?((BigDecimal) result[2]):null);
            }
            result=results.get(1);  // Programs for Graduate
            if(results!=null ){
                programM.setPrograms_for_graduate(result[1]!=null?((Integer)result[1]):null);
                programM.setPercent_programs_for_graduate(result[2]!=null?((BigDecimal)result[2]):null);
            }
        }

        results=getResult(queryMap,"8",yearInt,lang);
        if(results!=null && results.size()>0 ){
            result=(Object[])results.get(0);
            programM.setInternational_program(result[1]!=null?((Integer)result[1]):null);
        }

        results=getResult(queryMap,"9",yearInt,lang);//getProgramQuery3(year);
        if(results!=null && results.size()>0){
            result=results.get(0); // International Program for Graduate
            if(results!=null ){
                programM.setInternational_program_for_graduate(result[1]!=null?((Integer)result[1]):null);
            }
            result=results.get(1); // International Program for Undergraduate
            if(results!=null ){
                programM.setInternational_program_for_undergraduate(result[1]!=null?((Integer)result[1]):null);
            }
        }

        results=getResult(queryMap,"10",yearInt,lang);//results=getEmployeeQuery1(year);
        if(results!=null && results.size()>0 ){
            result=(Object[])results.get(0);
            employeeM.setPercent_faculty_member_doctor(result[1]!=null?((BigDecimal)result[1]):null);
            employeeM.setFaculty_member_doctor(result[2]!=null?((Integer)result[2]):null);
            employeeM.setFaculty_member_doctor_format(result[3]!=null?(((String)result[3]).trim()):null);
        }

        results=getResult(queryMap,"11",yearInt,lang);//results=getEmployeeQuery2(year);
        if(results!=null && results.size()>0 ){
            result=(Object[])results.get(0);
            employeeM.setFaculty_member(result[1]!=null?((Integer)result[1]):null);
            employeeM.setFaculty_member_format(result[2]!=null?(((String)result[2]).trim()):null);
        }

        results=getResult(queryMap,"12",yearInt,lang);//results=getEmployeeQuery3(year);
        if(results!=null && results.size()>0 ){
            result=(Object[])results.get(0);
            employeeM.setFull_time(result[1]!=null?((Integer)result[1]):null);
            employeeM.setFull_time_format(result[2]!=null?(((String)result[2]).trim()):null);
        }

        // factory member
        results=getResult(queryMap,"13",yearInt,lang);//results=getEmployeeQuery4(year);
        if(results!=null && results.size()>0){
            result=results.get(0); // Bachelor's & Lower
            if(results!=null ){
                employeeM.setFactory_member_bachelor_lower_percent(result[2]!=null?((BigDecimal) result[2]):null);
            }
            result=results.get(1); // Master's
            if(results!=null ){
                employeeM.setFactory_member_master_percent(result[2]!=null?((BigDecimal)result[2]):null);
            }
            result=results.get(2); // Doctoral's
            if(results!=null ){
                employeeM.setFactory_member_doctoral_percent(result[2]!=null?((BigDecimal)result[2]):null);
            }

        }

        // researcher
        results=getResult(queryMap,"14",yearInt,lang);//results=getEmployeeQuery5(year);
        if(results!=null && results.size()>0){
            result=results.get(0); // Bachelor's & Lower
            if(result!=null ){
                employeeM.setResearcher_bachelor_lower_percent(result[2]!=null?((BigDecimal)result[2]):null);
            }
            result=results.get(1); // Master's
            if(result!=null ){
                employeeM.setResearcher_master_percent(result[2]!=null?((BigDecimal)result[2]):null);
            }
            result=results.get(2); // Doctoral's
            if(result!=null ){
                employeeM.setResearcher_doctoral_percent(result[2]!=null?((BigDecimal)result[2]):null);
            }

        }


        // fulltime
        results=getResult(queryMap,"15",yearInt,lang);//results=getEmployeeQuery6(year);
        if(results!=null && results.size()>0){
            result=results.get(0); // Bachelor's & Lower
            if(result!=null ){
                employeeM.setFull_time_bachelor_lower_percent(result[2]!=null?((BigDecimal)result[2]):null);
            }
            result=results.get(1); // Master's
            if(result!=null ){
                employeeM.setFull_time_master_percent(result[2]!=null?((BigDecimal)result[2]):null);
            }
            result=results.get(2); // Doctoral's
            if(result!=null ){
                employeeM.setFull_time_doctoral_percent(result[2]!=null?((BigDecimal)result[2]):null);
            }

        }


        results=getResult(queryMap,"16",yearInt,lang);//results=getEmployabilityQuery1(year);
        if(results!=null && results.size()>0 ){
            result=(Object[])results.get(0);
            employabilityM.setUndergraduate(result[1]!=null?((BigDecimal)result[1]):null);
        }

        results=getResult(queryMap,"17",yearInt,lang);//results=getEmployabilityQuery2(year);
        if(results!=null && results.size()>0 ){
            result=(Object[])results.get(0);
            employabilityM.setGraduate(result[1]!=null?((BigDecimal)result[1]):null);
        }

        results=getResult(queryMap,"18",yearInt,lang);//results=getBudgetQuery1(year);
        if(results!=null && results.size()>0){
            List<String[]> incomes=new ArrayList<String[]>();
            for(int i=0;i<results.size();i++){
                String[] income=new String[2];
                result=results.get(i);
                income[0]=result[0]!=null?((String)result[0]).trim():"";
                income[1]=result[1]!=null?((String)result[1]).trim():"";
                incomes.add(income);
            }
            budgetM.setIncome_list(incomes);
        }

        results=getResult(queryMap,"19",yearInt,lang);//results=getBudgetQuery2(year);
        if(results!=null && results.size()>0 ){
            result=(Object[])results.get(0);
            budgetM.setIncome_amount(result[0]!=null?((BigDecimal) result[0]):null);
            budgetM.setIncome_amount_format(result[1]!=null?((String)result[1]).trim():null);
        }

        results=getResult(queryMap,"20",yearInt,lang);//results=getBudgetQuery3(year);
        if(results!=null && results.size()>0){
            List<String[]> expenses=new ArrayList<String[]>();
            for(int i=0;i<results.size();i++){
                String[] expense=new String[2];
                result=results.get(i);
                expense[0]=result[0]!=null?((String)result[0]).trim():"";
                expense[1]=result[1]!=null?((String)result[1]).trim():"";
                expenses.add(expense);
            }
            budgetM.setExpense_list(expenses);
        }
        results=getResult(queryMap,"21",yearInt,lang);//results=getBudgetQuery4(year);
        if(results!=null && results.size()>0 ){
            result=(Object[])results.get(0);
            budgetM.setExpense_amount(result[0]!=null?((BigDecimal) result[0]):null);
            budgetM.setExpense_amount_format(result[1]!=null?((String)result[1]).trim():null);
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
