import pytest
from selenium import webdriver


@pytest.fixture
def driver():
    options = webdriver.ChromeOptions()
    options.add_argument("--headless=new")
    options.add_argument("--window-size=1920,1080")

    browser = webdriver.Chrome(options=options)
    browser.implicitly_wait(0)
    yield browser
    browser.quit()
