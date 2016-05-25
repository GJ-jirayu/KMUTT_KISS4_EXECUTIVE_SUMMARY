package th.ac.kmutt.dashboard.service;

import org.springframework.dao.DataAccessException;
import th.ac.kmutt.dashboard.model.DashboardM;
import th.ac.kmutt.dashboard.model.Year;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    public DashboardM getDashboard(DashboardM param)throws DataAccessException;
    public DashboardM getDashboardDynamic(DashboardM param)throws DataAccessException;
    public Map<String,String> listKissLanguages(String lang)throws DataAccessException;
    public List<Year> listYear()throws DataAccessException;
}
