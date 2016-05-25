package th.ac.kmutt.dashboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import th.ac.kmutt.dashboard.model.DashboardM;
import th.ac.kmutt.dashboard.model.Year;
import th.ac.kmutt.dashboard.repository.DashboardDynamicQueryRepository;
import th.ac.kmutt.dashboard.repository.DashboardRepository;
import th.ac.kmutt.dashboard.service.DashboardService;

import java.util.List;
import java.util.Map;

@Service("dashboardServiceJpaImpl")
public class DashboardServiceJpaImpl implements DashboardService {


    @Autowired
    @Qualifier("dashboardRepository")
    private DashboardRepository dashboardRepository;


    @Autowired
    @Qualifier("dashboardQueryRepository")
    private DashboardDynamicQueryRepository dashboardQueryRepository;

    public DashboardM getDashboard(DashboardM param) throws DataAccessException {
        //List<ChartEntity> list = dashboardRepository.listChart();

        return dashboardRepository.getDashboard(param);
    }

    @Override
    public Map<String, String> listKissLanguages(String lang) throws DataAccessException {
        return dashboardRepository.listKissLanguages(lang);
    }

    @Override
    public List<Year> listYear() throws DataAccessException {
        return dashboardRepository.listYear();
    }

    @Override
    public DashboardM getDashboardDynamic(DashboardM param) throws DataAccessException {
        return dashboardQueryRepository.getDashboard(param);
    }
}
