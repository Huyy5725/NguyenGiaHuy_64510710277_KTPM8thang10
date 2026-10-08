from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait


class LoginPage:
    URL = "https://vanphongdientu.utc.edu.vn/Login"
    USERNAME_INPUT = (By.NAME, "username")
    PASSWORD_INPUT = (By.NAME, "userpwd")
    SUBMIT_BUTTON = (By.CSS_SELECTOR, "input.submit_login")
    PAGE_BODY = (By.TAG_NAME, "body")
    INVALID_CREDENTIALS_MESSAGE = "Tài khoản hoặc mật khẩu không đúng."

    def __init__(self, driver, timeout=10):
        self.driver = driver
        self.wait = WebDriverWait(driver, timeout)

    def open(self):
        self.driver.get(self.URL)
        self.wait.until(EC.visibility_of_element_located(self.USERNAME_INPUT))
        return self

    def login(self, username, password):
        username_input = self.wait.until(
            EC.visibility_of_element_located(self.USERNAME_INPUT)
        )
        password_input = self.wait.until(
            EC.visibility_of_element_located(self.PASSWORD_INPUT)
        )
        username_input.clear()
        username_input.send_keys(username)
        password_input.clear()
        password_input.send_keys(password)
        self.wait.until(EC.element_to_be_clickable(self.SUBMIT_BUTTON)).click()

    def wait_for_login_error(self):
        return self.wait.until(
            lambda driver: (
                self.INVALID_CREDENTIALS_MESSAGE
                if self.INVALID_CREDENTIALS_MESSAGE
                in driver.find_element(*self.PAGE_BODY).text
                else False
            )
        )
