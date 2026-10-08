package vn.utc.automation.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import vn.utc.automation.base.BaseTest;
import vn.utc.automation.pages.LoginPage;

class LoginTest extends BaseTest {
    private static final String BLANK_PASSWORD_MESSAGE = "Bạn chưa nhập mật khẩu";

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

    @Test
    void tc2BlankPasswordShowsValidationMessage() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.submitCredentials("blank.password.validation.test", "");

        assertEquals(BLANK_PASSWORD_MESSAGE,
                loginPage.waitForBlankPasswordValidationMessage());
    }
    private void assertInvalidCredentials(String username, String password) {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.submitCredentials(username, password);
        assertEquals("Tài khoản hoặc mật khẩu không đúng.",
                loginPage.waitForInvalidCredentialsMessage());
    }
    @Test
    void tc3InvalidUsernameAndPassword() {
        assertInvalidCredentials("utc.invalid.user", "invalid-password");
    }
    @Test
    void tc4UsernameWithLeadingWhitespace() {
        assertInvalidCredentials(" utc.invalid.user", "invalid-password");
    }
    @Test
    void tc5UsernameWithTrailingWhitespace() {
        assertInvalidCredentials("utc.invalid.user ", "invalid-password");
    }
    @Test
    void tc6UsernameWithMixedCase() {
        assertInvalidCredentials("UtC.InVaLiD.UsEr", "invalid-password");
    }
    @Test
    void tc7UsernameWithSpecialCharacters() {
        assertInvalidCredentials("utc.invalid+test@example.com", "invalid-password");
    }
    @Test
    void tc8VeryLongUsername() {
        assertInvalidCredentials("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu", "invalid-password");
    }
    @Test
    void tc9PasswordWithSpecialCharacters() {
        assertInvalidCredentials("utc.invalid.user", "!@#$%^&*()_+-=[]{}");
    }
    @Test
    void tc10VeryLongPassword() {
        assertInvalidCredentials("utc.invalid.user", "pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
    }
    @Test
    void tc11PasswordWithUnicode() {
        assertInvalidCredentials("utc.invalid.user", "mật-khẩu-不正");
    }
}
