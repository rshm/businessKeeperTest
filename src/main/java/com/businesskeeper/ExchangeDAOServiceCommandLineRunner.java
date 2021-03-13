package com.businesskeeper;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.businesskeeper.model.entity.ExchangeRate;
import com.businesskeeper.service.ExchangeDAOService;

@Component
public class ExchangeDAOServiceCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(ExchangeDAOServiceCommandLineRunner.class);
	@Autowired
	private ExchangeDAOService exchangeDAOService;
	@Override
	public void run(String... args) throws Exception {
		//save the exchange rate
		ExchangeRate exc = new ExchangeRate(LocalDate.of(2020, 1, 8),"INR","EUR",75.67 );
		long insert= exchangeDAOService.insert(exc);
		log.info("New Exchange rate is saved and inserted"+exc);
	}
}
