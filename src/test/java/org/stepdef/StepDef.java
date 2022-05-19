package org.stepdef;

import org.baseclass.BaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDef extends BaseClass{
	
	@Given("User is on the facebook login page")
	public void user_is_on_the_facebook_login_page() {
	   browserLaunch("https://www.facebook.com/");
	    System.out.println("browser launch");
	}
	@When("User should enter the username and password")
	public void user_should_enter_the_username_and_password() {
	   findElementId("email").sendKeys("anand");
	   findElementId("pass").sendKeys("anand@123");
	   System.out.println("username and pasword");
	}

	@When("User should click the login button")
	public void user_should_click_the_login_button() {
		findElementName("login").click();
	   System.out.println("login button click");
	}

	@Then("User should verify the success message")
	public void user_should_verify_the_success_message() {
	   System.out.println("susccessfully entered");
	}

}
