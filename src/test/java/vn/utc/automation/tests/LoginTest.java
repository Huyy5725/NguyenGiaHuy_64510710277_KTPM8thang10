package vn.utc.automation.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import vn.utc.automation.base.BaseTest;
import vn.utc.automation.pages.LoginPage;

class LoginTest extends BaseTest {
    @BeforeEach
    void startBrowserWhenRequired(TestInfo testInfo) {
        if (testInfo.getTestMethod().map(method ->
                method.getName().equals("tc1LoginWithValidCredentialsLeavesLoginPage"))
                .orElse(false)) {
            assumeTrue(hasValidCredentials(),
                    "BLOCKED: set UTC_USER and UTC_PASS to run TC1 with a real UTC account.");
        }
        startBrowser();
    }

    @Test
    void tc1LoginWithValidCredentialsLeavesLoginPage() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.submitCredentials(System.getenv("UTC_USER"), System.getenv("UTC_PASS"));

        assertTrue(loginPage.waitForAuthenticatedPage(),
                "Expected valid credentials to navigate away from /Login.");
    }

    private boolean hasValidCredentials() {
        String username = System.getenv("UTC_USER");
        String password = System.getenv("UTC_PASS");
        return username != null && !username.isBlank()
                && password != null && !password.isBlank();
    }
}
