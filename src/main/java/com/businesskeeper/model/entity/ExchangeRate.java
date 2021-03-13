package com.businesskeeper.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class ExchangeRate {

	LocalDate date;
	String base;

	public Double getRate() {
		return rate;
	}

	Double rate;
	String targetCurrency;
	@Id
	@GeneratedValue
	private long id;

	public ExchangeRate(LocalDate date, String base, String targetCurrency, Double rate) {
		this.date = date;
		this.base = base;
		this.targetCurrency = targetCurrency;
		this.rate = rate;
	}

	public ExchangeRate(Double rate) {

		System.out.println("Incremental constructor:"+rate);
	}

	public ExchangeRate() {
		System.out.println("Empty test constructor called");
	}

	public long getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getBase() {
		return base;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	@Override
	public String toString() {
		System.out.println("Printing object:");
		return "ExchangeRate{" +
				"date=" + date +
				", base='" + base + '\'' +
				", rate=" + rate +
				", targetCurrency='" + targetCurrency + '\'' +
				", id=" + id +
				'}';
	}
}
