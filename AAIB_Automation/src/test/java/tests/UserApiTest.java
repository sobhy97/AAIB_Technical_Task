package tests;
import com.aventstack.extentreports.*;
import models.ExtentReportManager;
import base.TestBase;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserApiTest extends TestBase {

    private static String userId;
    private final String userName = "Ahmed";
    private final String initialJob = "QA";
    private final String updatedJob = "Automation";
    ExtentReports extent;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentReportManager.getInstance();
    }

    @Test(priority = 1)
    public void createUser() {
        ExtentTest test = extent.createTest("createUser", "Test to create a new user");
        try {
            User user = new User(userName, initialJob);

            Response response = given()
                    .contentType("application/json")
                    .header("x-api-key","reqres-free-v1")
                    .body(user)
                    .when()
                    .post("/users")
                    .then()
                    .extract().response();

            userId = response.jsonPath().getString("id");
            test.log(Status.INFO, "User created with ID: " + userId);
            Assert.assertNotNull(userId);
            test.pass("User created successfully");
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 2, dependsOnMethods = "createUser")
    public void updateUser() {
        ExtentTest test = extent.createTest("updateUser", "Test to update an existing user");
        try {
            User updatedUser = new User(userName, updatedJob);

            given()
                    .contentType("application/json")
                    .header("x-api-key","reqres-free-v1")
                    .body(updatedUser)
                    .when()
                    .put("/users/" + userId)
                    .then()
                    .statusCode(200)
                    .body("job", equalTo(updatedJob));

            test.pass("User updated successfully");
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 3, dependsOnMethods = "updateUser")
    public void getUser() {
        ExtentTest test = extent.createTest("getUser", "Test to get user details");
        try {
            given()
                    .header("x-api-key","reqres-free-v1")
                    .when()
                    .get("/users/" + userId);

            test.pass("User retrieved successfully");
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 4, dependsOnMethods = "getUser")
    public void deleteUser() {
        ExtentTest test = extent.createTest("deleteUser", "Test to delete a user");
        try {
            given()
                    .header("x-api-key","reqres-free-v1")
                    .when()
                    .delete("/users/" + userId)
                    .then()
                    .statusCode(204);

            test.pass("User deleted successfully");
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 5, dependsOnMethods = "deleteUser")
    public void getUserAfterDelete() {
        ExtentTest test = extent.createTest("getUserAfterDelete", "Test to verify user is deleted");
        try {
            given()
                    .header("x-api-key","reqres-free-v1")
                    .when()
                    .get("/users/" + userId)
                    .then()
                    .statusCode(404);

            test.pass("User not found as expected after deletion");
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
        // Open the report automatically
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
        ReportUtils.openReport(reportPath);
    }
}