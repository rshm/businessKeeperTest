package com.businesskeeper.service;

import java.net.URI;

public class ExchangeRateService {
	public static final String ASCENDING ="ascending";
	public static final String DECENDING ="descending";
	public static final String UNDEFINED ="undefined";
	public static String exchangeRatetrend ;

	public static void calculateExchangeRateTrend(int[] exchangeRate){
		int i =0;
		String result;
		while(i<4 && exchangeRate[i]<exchangeRate[i+1]){
			i++;
		}
		if(i==4)
			exchangeRatetrend= ASCENDING;
		else{
			while(i<4 && exchangeRate[i]>exchangeRate[i+1]){
				i++;
			}
			if(i==4)
				exchangeRatetrend= DECENDING;
		}

		exchangeRatetrend= UNDEFINED;
	}
}
