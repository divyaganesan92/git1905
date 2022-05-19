package org.report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reporting {
	public static void genReport(String jsonFile) {
		File file = new File("D:\\Divya\\Eclipse-Workspace\\CucumberGit\\src\\test\\resources\\Feature file\\Reports");
		Configuration c=new Configuration(file,"Fb Login");
		c.addClassifications("OS", "Windows");
		c.addClassifications("Browser", "Chrome");
		c.addClassifications("Version", "100");
		c.addClassifications("Sprint", "22");
		c.addClassifications("Test", "Fb login Test");
		List<String> jsonfiles=new ArrayList<String>();
		jsonfiles.add(jsonFile);
		ReportBuilder builder=new ReportBuilder(jsonfiles, c);
		builder.generateReports();

	}
}
