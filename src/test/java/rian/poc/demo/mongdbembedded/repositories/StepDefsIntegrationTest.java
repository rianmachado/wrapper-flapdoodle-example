package rian.poc.demo.mongdbembedded.repositories;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.springframework.http.HttpStatus;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefsIntegrationTest extends SpringIntegrationTest {

	@Given("^the client calls /hello$")
	public void the_client_issues_GET_hello() throws Throwable {
		executeGet("http://localhost:8080/hello");
	}

	@When("^the client calls /version$")
	public void the_client_issues_GET_version() throws Throwable {
		executeGet("http://localhost:8080/version");
	}

	@Then("^the client receives status code of (\\d+)$")
	public void the_client_receives_status_code_of(int statusCode) throws Throwable {
		final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
		assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
	}

	@And("^the client receives server version (.+)$")
	public void the_client_receives_server_version_body(String version) throws Throwable {
		assertThat(latestResponse.getBody(), is(version));
	}
	
	@When("^the client calls /product$")
	public void the_client_issues_POST_product() throws Throwable {
		executePost();
	}
	
	@Then("^the client receives product status code of (\\d+)$")
	public void the_client_receives_product_status_code_of(int statusCode) throws Throwable {
		final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
		assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
	}
	
	
}