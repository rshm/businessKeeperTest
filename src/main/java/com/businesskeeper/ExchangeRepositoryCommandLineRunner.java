package com.businesskeeper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.businesskeeper.service.ExchangeRepositoryService;

import com.businesskeeper.model.entity.ExchangeRate;

public class ExchangeRepositoryCommandLineRunner implements CommandLineRunner{
	private static final Logger log =
			LoggerFactory.getLogger(ExchangeRepositoryCommandLineRunner.class);

	@Autowired
	private ExchangeRepositoryService exchangeRepository;

	@Override
	public void run(String... arg0) throws Exception {
		ExchangeRate exchangeRate =  new ExchangeRate(LocalDate.of(2020, 1, 8),"INR","EUR",75.6 );

		exchangeRepository.save(exchangeRate);
		log.info("New exchangeRate is created : " + exchangeRate);

		Optional<ExchangeRate> exchangeWithIdOne = exchangeRepository.findById(1L);
		log.info("exchangeWithIdOne is retrived : " + exchangeWithIdOne);

		List<ExchangeRate> exchange = exchangeRepository.findAll();
		log.info("All exchange rates : " + exchange);
	}

}
