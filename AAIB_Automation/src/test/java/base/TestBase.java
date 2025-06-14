package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class TestBase {
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    public class ReportUtils {
        public static void openReport(String filePath) {
            try {
                File reportFile = new File(filePath);
                if (reportFile.exists()) {
                    Desktop.getDesktop().browse(reportFile.toURI());
                } else {
                    System.out.println("Report file not found at: " + filePath);
                }
            } catch (IOException e) {
                System.out.println("Failed to open report: " + e.getMessage());
            }
        }
    }
}