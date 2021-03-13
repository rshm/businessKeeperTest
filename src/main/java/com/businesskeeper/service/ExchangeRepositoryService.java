package com.businesskeeper.service;

import com.businesskeeper.model.entity.ExchangeRate;
import com.businesskeeper.model.entity.FinalResponse;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepositoryService extends JpaRepository<ExchangeRate, Long>{

	public static FinalResponse getExchangeRate(){

		return new FinalResponse();
	}
}
