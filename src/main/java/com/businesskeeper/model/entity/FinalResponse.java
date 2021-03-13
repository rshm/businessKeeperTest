package com.businesskeeper.model.entity;

public class FinalResponse {

	Double rate;
	Double fiveDaysAverage;
	String exchangeRateTrend;

	public FinalResponse(Double rate, Double fiveDaysAverage, String exchangeRateTrend) {
		this.rate = rate;
		this.fiveDaysAverage = fiveDaysAverage;
		this.exchangeRateTrend = exchangeRateTrend;
	}

	public FinalResponse() {

	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getFiveDaysAverage() {
		return fiveDaysAverage;
	}

	public  void setFiveDaysAverage(Double fiveDaysAverage) {
		this.fiveDaysAverage = fiveDaysAverage;
	}

	public String getExchangeRateTrend() {
		return exchangeRateTrend;
	}

	public void setExchangeRateTrend(String exchangeRateTrend) {
		this.exchangeRateTrend = exchangeRateTrend;
	}

	@Override
	public String toString() {
		return "FinalResponse{" +
				"rate=" + rate +
				", fiveDaysAverage=" + fiveDaysAverage +
				", exchangeRateTrend='" + exchangeRateTrend + '\'' +
				'}';
	}
}
