package rian.poc.demo.mongdbembedded.repositories;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import io.cucumber.spring.CucumberContextConfiguration;
import rian.poc.demo.mongdbembedded.SpringBootMongodbApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = SpringBootMongodbApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
	static ResponseResults latestResponse = null;

	@Autowired
	protected RestTemplate restTemplate;

	void executeGet(String url) throws IOException {
		final Map<String, String> headers = new HashMap<>();
		headers.put("Accept", "application/json");
		final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
		final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();
		restTemplate.setErrorHandler(errorHandler);
		latestResponse = restTemplate.execute(url, HttpMethod.GET, requestCallback, response -> {
			if (errorHandler.hadError) {
				return (errorHandler.getResults());
			} else {
				return (new ResponseResults(response));
			}
		});
	}

	void executePost() throws IOException {
		final Map<String, String> headers = new HashMap<>();
		headers.put("Accept", "application/json");
		final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
		final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();
		requestCallback.setBody(
				"{\"description\" : \"Description-Rian Maria Agoradsd\", \"price\" : 42.13, \"imageUrl\" :  \"http://rian.com.br/imagem11.jpeg\"}");
		requestCallback.requestHeaders.put("Content-Type", "application/json");
		
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
		}

		restTemplate.setErrorHandler(errorHandler);
		latestResponse = restTemplate.execute("http://localhost:8080/product", HttpMethod.POST, requestCallback,
				response -> {
					if (errorHandler.hadError) {
						return (errorHandler.getResults());
					} else {
						return (new ResponseResults(response));
					}
				});
	}

	private class ResponseResultErrorHandler implements ResponseErrorHandler {
		private ResponseResults results = null;
		private Boolean hadError = false;

		private ResponseResults getResults() {
			return results;
		}

		@Override
		public boolean hasError(ClientHttpResponse response) throws IOException {
			hadError = response.getRawStatusCode() >= 400;
			return hadError;
		}

		@Override
		public void handleError(ClientHttpResponse response) throws IOException {
			results = new ResponseResults(response);
		}
	}
}