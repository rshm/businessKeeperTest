package com.businesskeeper.model.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.businesskeeper.exception.ResourceNotFoundException;
import com.businesskeeper.service.ExchangeRateService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/api/exchange-rate")
public class ExchangeRateController {

	//	http://localhost:8080/api/exchange-rate/2020-03-03/EUR/INR
	private static final String template = "Hello, %s!";
	public String base_uri="https://api.exchangeratesapi.io";
	Gson gson = new Gson();

	BufferedReader in;
	@GetMapping(path = "/{date}/{baseCurrency}/{targetCurrency}")
	public FinalResponse getExchangeRate(
			@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
			@PathVariable("baseCurrency") String baseCurrency,
			@PathVariable("targetCurrency") String targetCurrency) throws IOException {


		String GET_URL = base_uri+"/latest?base="+baseCurrency+"&symbols=" + baseCurrency + "," + targetCurrency;
		InputStreamReader responseStream = executeHTTPRequest(GET_URL);

		String response="";

			try{
				response = getResponse(responseStream);
				Double exchangeRate = parseResponse(response,targetCurrency);
				Double lastFiveDays=calculateLastFiveDaysAverage(date,baseCurrency,targetCurrency);
				String trend = ExchangeRateService.exchangeRatetrend;
				return new FinalResponse(exchangeRate,lastFiveDays,trend);
			}catch(Exception ie){
				System.out.println("response:"+response);
				response = getResponse(responseStream);
				ResourceNotFoundException rexc =  parseExceptionResponse(response);
				System.out.println("error obj: " + rexc);
			}

		return new FinalResponse();
	}



	private Double calculateLastFiveDaysAverage(LocalDate date ,String baseCurrency,String targetCurrency)
			throws IOException {
		date = LocalDate.now();
		String url = base_uri+"/history?base="+baseCurrency+"&start_at="+date.minusDays(5)+"&end_at="+date+"&symbols="+ baseCurrency + "," + targetCurrency;
		System.out.println("URI is:"+url);
		InputStreamReader responseStream = executeHTTPRequest(url);

		String response = getResponse(responseStream);
		JsonObject obj = JsonParser.parseString(response).getAsJsonObject();
		List<Double> exchangerateValueArray= new ArrayList<>();
		System.out.println("obj:" + obj);
		try {
			Rate lastFiveDaysObject = gson.fromJson(response.toString(), Rate.class);
			System.out.println("gson to json"+lastFiveDaysObject);
			double sum = 0.0;
			for (Map.Entry<String, Map<String, Double>> entry : lastFiveDaysObject.getDateExchangeRate().entrySet()) {
				Map<String, Double> childMap = entry.getValue();

				for (Map.Entry<String, Double> entry2 : childMap.entrySet()) {
					exchangerateValueArray.add(entry2.getValue());
					sum=sum+ entry2.getValue();
				}
			}


			return sum/5;
		}catch(Exception e) {
			System.out.println("Exception occurred:"+e);
		}

		return 0.0;
	}

	private Double parseResponse(String response, String targetCurrency) throws IOException {

		JsonObject obj = JsonParser.parseString(response).getAsJsonObject();
		Double exchangerate=0.0;
		try {
			ExchangeRateTest user = gson.fromJson(response.toString(), ExchangeRateTest.class);
			System.out.println("gson to json"+user.getRates());
			exchangerate=user.getRates().get(targetCurrency);

		}catch(Exception e) {
			System.out.println("Exception occurred:"+e);
		}

		return exchangerate;
	}

	private InputStreamReader executeHTTPRequest(String getUrl) throws IOException {
		URL url = new URL(getUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");
		int responseCode=httpURLConnection.getResponseCode();;
		if (responseCode == HttpURLConnection.HTTP_OK) {
			return new InputStreamReader(httpURLConnection.getInputStream());
		}
		else
			return new InputStreamReader(httpURLConnection.getErrorStream());
	}

	private String getResponse(InputStreamReader ins) throws IOException {
		in = new BufferedReader(ins);
		String inputline;
		StringBuffer response = new StringBuffer();
		while ((inputline = in.readLine()) != null) {
			response.append(inputline);
		}
		in.close();
		return response.toString();
	}



	private ResourceNotFoundException parseExceptionResponse(String response) throws IOException {

		JsonObject obj = JsonParser.parseString(response).getAsJsonObject();
		System.out.println("Exception object is:" + obj);

		return new ResourceNotFoundException();
	}

	@GetMapping(path = "/{history/daily/{yyyy}/{MM}/{dd}")
	public ExchangeRate getDailyExchangeRate(
			@PathVariable("yyyy") String year,
			@PathVariable("MM") String month,
			@PathVariable("dd") String date
	) {
		return new ExchangeRate();
	}

	@GetMapping(path = "/{history/monthly/{yyyy}/{MM}/{dd}")
	public ExchangeRate getMonthlyExchangeRate(
			@PathVariable("yyyy") String year,
			@PathVariable("MM") String month,
			@PathVariable("dd") String date
	) {
		return new ExchangeRate();
	}
}
