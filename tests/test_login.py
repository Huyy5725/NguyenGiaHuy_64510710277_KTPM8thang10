import os

import pytest

from tests.pages.login_page import LoginPage

INVALID_PASSWORD = "invalid-test-password"


def test_tc01_invalid_username_shows_login_error(driver):
    username = os.getenv("UTC_USER")
    if not username:
        pytest.fail("Set UTC_USER before running the login failure tests.")

    login_page = LoginPage(driver).open()
    login_page.login(f"{username}__invalid__", INVALID_PASSWORD)

    assert (
        login_page.wait_for_login_error()
        == LoginPage.INVALID_CREDENTIALS_MESSAGE
    )
