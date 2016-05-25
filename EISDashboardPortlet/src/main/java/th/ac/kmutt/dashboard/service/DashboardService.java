package th.ac.kmutt.dashboard.service;


import th.ac.kmutt.dashboard.model.DashboardM;
import th.ac.kmutt.dashboard.model.KissLanguageM;
import th.ac.kmutt.dashboard.model.YearM;

import java.util.List;


public interface DashboardService {

	public DashboardM getDashboard(DashboardM param);
	public KissLanguageM getKissLanguageM(KissLanguageM param);
	public YearM listYears(YearM param);
}
