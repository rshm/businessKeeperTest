package com.businesskeeper.model.entity;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class ExchangeRateTest {
	@SerializedName("base")
	private String base;
	@SerializedName("date")
	private String date;
	@SerializedName("rates")
	Map<String,Double> rates = new HashMap<>();

	@Override
	public String toString() {
		return "ExchangeRateTest{" +
				"base='" + base + '\'' +
				", date='" + date + '\'' +
				", rates='" + rates + '\'' +
				'}';
	}

	public String getBase() {
		return base;
	}

	public String getDate() {
		return date;
	}

	public Map<String,Double> getRates() {
		return rates;
	}
}
