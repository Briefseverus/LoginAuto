This code is a Java program that logs into Facebook using a list of email addresses and passwords stored in an Excel file. It uses the Selenium WebDriver library to automate the login process in a Chrome browser.

The program reads the email and password values from the Excel file and enters them into the respective input fields on the Facebook login page. It then clicks the login button and waits for the page to load.

If the login is successful, the program navigates to the Facebook homepage and saves the user's cookies to a text file. If the login fails due to a checkpoint challenge, the program saves the email and password to another text file for later review.

The program uses the Java AWT Robot class to simulate mouse clicks at specific screen coordinates to bypass Facebook's automated detection of bot activity.

To deploy the project :
1: Install all the lib in the generated .setting folder.
2: Replace chromeDriver path in your computer.
3: Replace the Profile Chrome path with the ChromeProfile you want the program use
4: Replace the path of exel file, cookie.txt and checkPoint.txt file.
5: Identify the screen coordinates of 2 element not in the web page to simulator click to trigger it :D (1st place is Clear Cache Extension, 2st place is logout js function store as a bookmark, it kinda such because i haven't made this feature work in 100% code yet, so I still have to use an extension.It brings some inconvenience when you can't do other things while running the program) 
>>PLEASE MAKE SURE THE LINK OF CHROME PROFILE, YOU CAN CHECK IT BY GO TO "CHROME://VERSION", IT ANY EXCEPTION HAPPEN, KEEP MAKE SURE YOU IMPORT ALL THE LIB IN THE .SETTING FOLDER<<
GOODLUCK ^^

