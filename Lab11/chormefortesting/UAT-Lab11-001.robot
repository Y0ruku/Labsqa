*** Settings ***
Library    SeleniumLibrary


*** Variables ***
${CHROME_BROWSER_PATH}    ${EXECDIR}/chrome-win64/chrome.exe
${URL}                    http://sampleapp.tricentis.com/


*** Test Cases ***
UAT-Lab11-001
    [Documentation]    การขอใบเสนอราคาผลิตภัณฑ์สำหรับรถยนต์ได้สำเร็จ
    [Tags]    UAT    Lab11    Pass

    # ==================================================
    # เปิด Chrome
    # ==================================================
    ${chrome_options}=    Evaluate    sys.modules["selenium.webdriver"].ChromeOptions()    sys
    Evaluate    setattr($chrome_options, "binary_location", r"${CHROME_BROWSER_PATH}")
    Call Method    ${chrome_options}    add_argument    --start-maximized

    Open Browser    ${URL}    Chrome    options=${chrome_options}
    Maximize Browser Window

    Wait Until Page Contains    Tricentis Vehicle Insurance    20s


    # ==================================================
    # เลือก Automobile
    # ==================================================
    Wait Until Page Contains Element
    ...    xpath=//a[contains(normalize-space(.),"Automobile")]
    ...    20s

    Click Element
    ...    xpath=//a[contains(normalize-space(.),"Automobile")]

    Wait Until Page Contains    Enter Vehicle Data    20s
    Wait Until Page Contains Element    id=make    20s


    # ==================================================
    # Vehicle Data
    # ==================================================
    Select From List By Label    id=make    BMW
    Input Text    id=engineperformance    110
    Input Text    id=dateofmanufacture    09/09/2020
    Select From List By Label    id=numberofseats    5
    Select From List By Label    id=fuel    Electric Power
    Input Text    id=listprice    30000
    Input Text    id=licenseplatenumber    CKK1234
    Input Text    id=annualmileage    10000


    # ==================================================
    # Next -> Insurant Data
    # ==================================================
    Click Element    id=nextenterinsurantdata

    Wait Until Page Contains    Enter Insurant Data    20s
    Wait Until Page Contains Element    id=firstname    20s


    # ==================================================
    # Insurant Data
    # ==================================================
    Input Text    id=firstname    Wichai
    Input Text    id=lastname    Sandee
    Input Text    id=birthdate    01/31/1990

    # Gender = Male
    Execute Javascript
    ...    document.getElementById("gendermale").click();

    Input Text    id=streetaddress    KKU
    Select From List By Label    id=country    Thailand
    Input Text    id=zipcode    40002
    Input Text    id=city    Khon Kaen
    Select From List By Label    id=occupation    Employee

    # Hobbies = Other
    Execute Javascript
    ...    document.getElementById("other").click();


    # ==================================================
    # Next -> Product Data
    # ==================================================
    Click Element    id=nextenterproductdata

    Wait Until Page Contains    Enter Product Data    20s
    Wait Until Page Contains Element    id=startdate    20s


    # ==================================================
    # Product Data
    # ==================================================
    # Start Date = 11/14/2026
    Input Text    id=startdate    11/14/2026

    Select From List By Label
    ...    id=insurancesum
    ...    7.000.000,00

    Select From List By Label
    ...    id=meritrating
    ...    Bonus 1

    Select From List By Label
    ...    id=damageinsurance
    ...    No Coverage

    # Euro Protection
    Execute Javascript
    ...    document.getElementById("EuroProtection").click();

    Select From List By Label    id=courtesycar    Yes


    # ==================================================
    # Next -> Price Option
    # ==================================================
    Click Element    id=nextselectpriceoption

    Wait Until Page Contains    Select Price Option    20s


    # ==================================================
    # เลือก Silver
    # ==================================================
    Wait Until Page Contains    Silver    20s

    Execute Javascript
    ...    const radio = document.querySelector('input[value="Silver"]');
    ...    radio.checked = true;
    ...    radio.dispatchEvent(new Event('change', {bubbles:true}));
    ...    radio.dispatchEvent(new Event('click', {bubbles:true}));

    Sleep    1s


    # ==================================================
    # Next -> Send Quote
    # ==================================================
    Execute Javascript
    ...    const btn = document.getElementById("nextsendquote");
    ...    btn.scrollIntoView({block:"center"});
    ...    btn.click();

    Wait Until Page Contains    Send Quote    20s
    Wait Until Page Contains Element    id=email    20s


    # ==================================================
    # Send Quote
    # ==================================================

    # Email
    Execute Javascript
    ...    const email = document.getElementById("email");
    ...    email.scrollIntoView({block:"center"});
    ...    email.focus();
    ...    email.value = "darunphop.s@kkumail.com";
    ...    email.dispatchEvent(new Event('input', {bubbles:true}));
    ...    email.dispatchEvent(new Event('change', {bubbles:true}));

    # Phone
    Execute Javascript
    ...    const phone = document.getElementById("phone");
    ...    phone.value = "0049201123456";
    ...    phone.dispatchEvent(new Event('input', {bubbles:true}));
    ...    phone.dispatchEvent(new Event('change', {bubbles:true}));

    # Username
    Execute Javascript
    ...    const username = document.getElementById("username");
    ...    username.value = "wichai.sandee";
    ...    username.dispatchEvent(new Event('input', {bubbles:true}));
    ...    username.dispatchEvent(new Event('change', {bubbles:true}));

    # Password
    Execute Javascript
    ...    const password = document.getElementById("password");
    ...    password.value = "SecretPassword123!";
    ...    password.dispatchEvent(new Event('input', {bubbles:true}));
    ...    password.dispatchEvent(new Event('change', {bubbles:true}));

    # Confirm Password
    Execute Javascript
    ...    const confirm = document.getElementById("confirmpassword");
    ...    confirm.value = "SecretPassword123!";
    ...    confirm.dispatchEvent(new Event('input', {bubbles:true}));
    ...    confirm.dispatchEvent(new Event('change', {bubbles:true}));

    # Comments
    Execute Javascript
    ...    const comments = document.getElementById("Comments");
    ...    comments.value = "Please contact via email only";
    ...    comments.dispatchEvent(new Event('input', {bubbles:true}));
    ...    comments.dispatchEvent(new Event('change', {bubbles:true}));


    # ==================================================
    # Send Email
    # ==================================================
    Execute Javascript
    ...    const send = document.getElementById("sendemail");
    ...    send.scrollIntoView({block:"center"});
    ...    send.click();


    # ==================================================
    # ตรวจสอบผลลัพธ์
    # ==================================================
    Wait Until Page Contains    Sending e-mail success!    30s

    Page Should Contain    Sending e-mail success!


    # ==================================================
    # Result
    # ==================================================
    Log To Console    ========================================
    Log To Console    UAT-Lab11-001 PASS
    Log To Console    ขอใบเสนอราคาผลิตภัณฑ์สำหรับรถยนต์ได้สำเร็จ
    Log To Console    Email: darunphop.s@kkumail.com
    Log To Console    ========================================


    # ==================================================
    # ปิด Browser
    # ==================================================
    [Teardown]    Close All Browsers