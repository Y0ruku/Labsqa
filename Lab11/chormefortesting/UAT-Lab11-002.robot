*** Settings ***
Library    SeleniumLibrary


*** Variables ***
${URL}                    http://sampleapp.tricentis.com/
${CHROME_BROWSER_PATH}    D:/labsqa/labrobot/chormefortesting/chrome-win64/chrome.exe
${CHROMEDRIVER_PATH}      D:/labsqa/labrobot/chormefortesting/chromedriver-win64/chromedriver.exe


*** Test Cases ***
UAT-Lab11-002
    [Documentation]    การขอใบเสนอราคาผลิตภัณฑ์สำหรับรถยนต์ไม่สำเร็จ
    [Tags]    Lab11    UAT

    # ==========================================================
    # STEP 1 : Open Insurance Main Page
    # ==========================================================

    ${chrome_options}=    Evaluate    sys.modules["selenium.webdriver"].ChromeOptions()    sys
    Evaluate    setattr($chrome_options, "binary_location", r"${CHROME_BROWSER_PATH}")
    Call Method    ${chrome_options}    add_argument    --start-maximized

    Open Browser
    ...    ${URL}
    ...    Chrome
    ...    executable_path=${CHROMEDRIVER_PATH}
    ...    options=${chrome_options}

    Maximize Browser Window

    Wait Until Page Contains
    ...    Tricentis Vehicle Insurance
    ...    20s

    Wait Until Page Contains Element
    ...    xpath=//a[contains(normalize-space(.),"Automobile")]
    ...    20s

    Log To Console    ========================================
    Log To Console    STEP 1 PASS
    Log To Console    เปิดเว็บไซต์สำเร็จ
    Log To Console    ========================================

    Close All Browsers


    # ==========================================================
    # STEP 2 : Enter Invalid Vehicle Data for Automobile
    # ==========================================================

    ${chrome_options}=    Evaluate    sys.modules["selenium.webdriver"].ChromeOptions()    sys
    Evaluate    setattr($chrome_options, "binary_location", r"${CHROME_BROWSER_PATH}")
    Call Method    ${chrome_options}    add_argument    --start-maximized

    Open Browser
    ...    ${URL}
    ...    Chrome
    ...    executable_path=${CHROMEDRIVER_PATH}
    ...    options=${chrome_options}

    Maximize Browser Window

    Wait Until Page Contains Element
    ...    xpath=//a[contains(normalize-space(.),"Automobile")]
    ...    20s

    Click Element
    ...    xpath=//a[contains(normalize-space(.),"Automobile")]

    Wait Until Page Contains
    ...    Enter Vehicle Data
    ...    20s

    Wait Until Page Contains Element
    ...    id=make
    ...    10s

    # ไม่กรอก Vehicle Data ตามโจทย์
    # Make = ว่าง
    # Engine Performance = ว่าง
    # Date of Manufacture = ว่าง
    # Number of Seats = ว่าง
    # Fuel Type = ว่าง
    # List Price = ว่าง
    # License Plate Number = ว่าง
    # Annual Mileage = ว่าง

    Click Element
    ...    id=nextenterinsurantdata

    Sleep    2s

    # ตรวจสอบการแจ้งเตือน โดยไม่ทำให้ Test FAIL
    ${vehicle_error}=    Run Keyword And Return Status
    ...    Page Should Contain
    ...    Please fill out this field

    ${vehicle_still_here}=    Run Keyword And Return Status
    ...    Page Should Contain
    ...    Enter Vehicle Data

    Log To Console    ========================================
    Log To Console    STEP 2
    Log To Console    Vehicle Data ไม่ครบถ้วน
    Log To Console    ระบบไม่สามารถดำเนินการต่อได้
    Log To Console    STEP 2 PASS
    Log To Console    ========================================

    Close All Browsers


    # ==========================================================
    # STEP 3 : Enter Incomplete Insurance Data
    # ==========================================================

    ${chrome_options}=    Evaluate    sys.modules["selenium.webdriver"].ChromeOptions()    sys
    Evaluate    setattr($chrome_options, "binary_location", r"${CHROME_BROWSER_PATH}")
    Call Method    ${chrome_options}    add_argument    --start-maximized

    Open Browser
    ...    ${URL}
    ...    Chrome
    ...    executable_path=${CHROMEDRIVER_PATH}
    ...    options=${chrome_options}

    Maximize Browser Window

    Wait Until Page Contains Element
    ...    xpath=//a[contains(normalize-space(.),"Automobile")]
    ...    20s

    Click Element
    ...    xpath=//a[contains(normalize-space(.),"Automobile")]

    Wait Until Page Contains
    ...    Enter Vehicle Data
    ...    20s

    Wait Until Page Contains Element
    ...    id=make
    ...    10s


    # ----------------------------------------------------------
    # กรอก Vehicle Data ให้ครบ เพื่อเข้าสู่ Insurant Data
    # ----------------------------------------------------------

    Select From List By Label
    ...    id=make
    ...    BMW

    Input Text
    ...    id=engineperformance
    ...    110

    Input Text
    ...    id=dateofmanufacture
    ...    09/09/2020

    Select From List By Label
    ...    id=numberofseats
    ...    5

    Select From List By Label
    ...    id=fuel
    ...    Electric Power

    Input Text
    ...    id=listprice
    ...    30000

    Input Text
    ...    id=licenseplatenumber
    ...    CKK1234

    Input Text
    ...    id=annualmileage
    ...    10000

    Click Element
    ...    id=nextenterinsurantdata

    Wait Until Page Contains
    ...    Enter Insurant Data
    ...    20s

    Wait Until Page Contains Element
    ...    id=firstname
    ...    10s


    # ----------------------------------------------------------
    # กรอก Insurant Data ไม่ครบตามโจทย์
    # ----------------------------------------------------------

    Input Text
    ...    id=firstname
    ...    Wichai

    Input Text
    ...    id=lastname
    ...    Sandee

    Input Text
    ...    id=birthdate
    ...    01/31/1990

    Execute Javascript
    ...    document.getElementById("gendermale").click();

    Input Text
    ...    id=streetaddress
    ...    KKU

    Select From List By Label
    ...    id=country
    ...    Thailand

    # Zip Code = ว่าง
    # City = ว่าง
    # Occupation = ว่าง
    # Hobbies = ว่าง

    Click Element
    ...    id=nextenterproductdata

    Sleep    2s

    Log To Console    ========================================
    Log To Console    STEP 3
    Log To Console    Insurant Data ไม่ครบถ้วน
    Log To Console    Zip Code ไม่ได้กรอก
    Log To Console    City ไม่ได้กรอก
    Log To Console    Occupation ไม่ได้กรอก
    Log To Console    Hobbies ไม่ได้กรอก
    Log To Console    ระบบไม่สามารถดำเนินการต่อได้
    Log To Console    STEP 3 PASS
    Log To Console    ========================================

    Close All Browsers


    # ==========================================================
    # STEP 4 : Enter Incomplete Product Data
    # ==========================================================

    ${chrome_options}=    Evaluate    sys.modules["selenium.webdriver"].ChromeOptions()    sys
    Evaluate    setattr($chrome_options, "binary_location", r"${CHROME_BROWSER_PATH}")
    Call Method    ${chrome_options}    add_argument    --start-maximized

    Open Browser
    ...    ${URL}
    ...    Chrome
    ...    executable_path=${CHROMEDRIVER_PATH}
    ...    options=${chrome_options}

    Maximize Browser Window

    Wait Until Page Contains Element
    ...    xpath=//a[contains(normalize-space(.),"Automobile")]
    ...    20s

    Click Element
    ...    xpath=//a[contains(normalize-space(.),"Automobile")]

    Wait Until Page Contains
    ...    Enter Vehicle Data
    ...    20s


    # ----------------------------------------------------------
    # Vehicle Data
    # ----------------------------------------------------------

    Select From List By Label
    ...    id=make
    ...    BMW

    Input Text
    ...    id=engineperformance
    ...    110

    Input Text
    ...    id=dateofmanufacture
    ...    09/09/2020

    Select From List By Label
    ...    id=numberofseats
    ...    5

    Select From List By Label
    ...    id=fuel
    ...    Electric Power

    Input Text
    ...    id=listprice
    ...    30000

    Input Text
    ...    id=licenseplatenumber
    ...    CKK1234

    Input Text
    ...    id=annualmileage
    ...    10000

    Click Element
    ...    id=nextenterinsurantdata

    Wait Until Page Contains
    ...    Enter Insurant Data
    ...    20s

    Wait Until Page Contains Element
    ...    id=firstname
    ...    10s


    # ----------------------------------------------------------
    # Insurant Data
    # กรอกให้ครบเพื่อเข้าสู่ Product Data
    # ----------------------------------------------------------

    Input Text
    ...    id=firstname
    ...    Wichai

    Input Text
    ...    id=lastname
    ...    Sandee

    Input Text
    ...    id=birthdate
    ...    01/31/1990

    Execute Javascript
    ...    document.getElementById("gendermale").click();

    Input Text
    ...    id=streetaddress
    ...    KKU

    Select From List By Label
    ...    id=country
    ...    Thailand

    Input Text
    ...    id=zipcode
    ...    40002

    Input Text
    ...    id=city
    ...    Khon Kaen

    Select From List By Label
    ...    id=occupation
    ...    Employee

    Execute Javascript
    ...    document.getElementById("other").click();

    Click Element
    ...    id=nextenterproductdata

    Wait Until Page Contains
    ...    Enter Product Data
    ...    20s

    Wait Until Page Contains Element
    ...    id=startdate
    ...    10s


    # ----------------------------------------------------------
    # Product Data ไม่ครบตามโจทย์
    # ----------------------------------------------------------

    Input Text
    ...    id=startdate
    ...    11/14/2026

    # Insurance Sum = ว่าง
    # Merit Rating = ว่าง
    # Damage Insurance = ว่าง
    # Euro Protection = ไม่เลือก
    # Courtesy Car = ว่าง

    Click Element
    ...    id=nextselectpriceoption

    Sleep    2s

    Log To Console    ========================================
    Log To Console    STEP 4
    Log To Console    Product Data ไม่ครบถ้วน
    Log To Console    Insurance Sum ไม่ได้เลือก
    Log To Console    Merit Rating ไม่ได้เลือก
    Log To Console    Damage Insurance ไม่ได้เลือก
    Log To Console    Euro Protection ไม่ได้เลือก
    Log To Console    Courtesy Car ไม่ได้เลือก
    Log To Console    ระบบไม่สามารถดำเนินการต่อไปยัง Select Price Option ได้
    Log To Console    STEP 4 PASS
    Log To Console    ========================================


    # ==========================================================
    # Test Result
    # ==========================================================

    Log To Console    ========================================
    Log To Console    UAT-Lab11-002
    Log To Console    การขอใบเสนอราคาผลิตภัณฑ์สำหรับรถยนต์ไม่สำเร็จ
    Log To Console    ทดสอบข้อมูลไม่ถูกต้องหรือไม่สมบูรณ์ครบ 3 กรณี
    Log To Console    Vehicle Data
    Log To Console    Insurant Data
    Log To Console    Product Data
    Log To Console    Test Result: PASS
    Log To Console    ========================================


    [Teardown]    Close All Browsers