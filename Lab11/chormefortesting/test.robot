*** Settings ***
Library    SeleniumLibrary

*** Variables ***
${CHROME_BROWSER_PATH}    ${EXECDIR}${/}chrome-win64${/}chrome.exe
${CHROME_DRIVER_PATH}     ${EXECDIR}${/}chromedriver-win64${/}chromedriver.exe
${URL}                    https://www.kku.ac.th

*** Test Cases ***
TC 001: Start Chrome For Testing
    ${chrome_options}    Evaluate    sys.modules["selenium.webdriver"].ChromeOptions()    sys
    Evaluate    setattr($chrome_options, "binary_location", r"${CHROME_BROWSER_PATH}")
    ${service}    Evaluate    sys.modules["selenium.webdriver.chrome.service"].Service(executable_path=r"${CHROME_DRIVER_PATH}")    sys
    Create Webdriver    Chrome    options=${chrome_options}    service=${service}
    Go To    ${URL}
    Sleep    30s

    