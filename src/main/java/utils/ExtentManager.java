package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = "src/test/resources/reports/ExtentReport.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
}
