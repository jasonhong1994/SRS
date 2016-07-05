package com.srs.dao;

public class PlanDaoSqliteFactory implements IDaoFactory {


	@Override
	public PlanDaoSqlite createPlanDao() {
		return new PlanDaoSqlite();
	}

}
