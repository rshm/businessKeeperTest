package com.businesskeeper.model.entity;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class Rate {

	public Map<String, Map<String, Double>> getDateExchangeRate() {
		return dateExchangeRate;
	}

	@SerializedName("rates")
	Map<String, Map<String,Double>>  dateExchangeRate ;

	@Override
	public String toString() {
		return "Rate{" +
				"dateExchangeRate=" + dateExchangeRate +
				'}';
	}
}
