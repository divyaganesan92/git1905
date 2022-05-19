package org.testrunner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.report.Reporting;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resources\\Feature file\\Cucumber.feature",
				 glue="org.stepdef", monochrome =true,
				 plugin= {"pretty","json:src\\test\\resources\\Feature file\\Reports\\Output1.json"})

public class TestRunnerReport {
	@AfterClass
	public static void genJvmReport() {
		Reporting.genReport("D:\\Divya\\Eclipse-Workspace\\CucumberGit\\"
				+ "src\\test\\resources\\Feature file\\Reports\\Output1.json");
	}
}
