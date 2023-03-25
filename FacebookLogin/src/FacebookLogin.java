import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class FacebookLogin {

	public static void main(String[] args) throws AWTException {
		Robot robot = new Robot();
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Admin\\Desktop\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("user-data-dir=C:\\Users\\Admin\\AppData\\Local\\Google\\Chrome\\User Data");
		options.addArguments("profile-directory=Profile 1");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.facebook.com/");
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(new File("C:\\Users\\Admin\\Desktop\\Test.xlsx"));
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			DataFormatter formatter = new DataFormatter();
			for (int i = 1; i <= rowCount; i++) {
				robot.mouseMove(1770, 67);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				driver.get("https://www.facebook.com/");
				String email = formatter.formatCellValue(sheet.getRow(i).getCell(0));
				String password = formatter.formatCellValue(sheet.getRow(i).getCell(1));
				System.out.println("Login :" + email + " " + password);
				WebElement emailInput = driver.findElement(By.id("email"));
				emailInput.clear();
				emailInput.sendKeys(email);
				WebElement passwordInput = driver.findElement(By.id("pass"));
				passwordInput.clear();
				passwordInput.sendKeys(password);
				WebElement loginButton = driver.findElement(By.name("login"));
				loginButton.click();
				Thread.sleep(3000);
				if (driver.getCurrentUrl().contains("checkpoint")) {
					FileWriter writer = new FileWriter(System.getProperty("user.home") + "/Desktop/checkPoint.txt",
							true);
					writer.write(email + "|" + password + "|");
					writer.write("\n");
					writer.close();
					System.out.println("Login failed for " + email + " <CheckPoint> ");
					driver.get("https://www.facebook.com/");
					System.out.println("-----------------");
					continue;
				}
				try {
					driver.get("https://mbasic.facebook.com/");
					WebElement chatTextarea = driver.findElement(By.name("xc_message"));
					if (chatTextarea.isDisplayed()) {
						try {
							driver.get("https://facebook.com/");
							Thread.sleep(5000);
							FileWriter writer = new FileWriter(System.getProperty("user.home") + "/Desktop/cookies.txt",
									true);
							writer.write(email + "|" + password + "|");
							for (Cookie cookie : driver.manage().getCookies()) {
								System.out.println(cookie);
								writer.write(cookie.getName() + "=" + cookie.getValue() + ";");
							}
							writer.write("\n");
							writer.close();
							System.out.println("Login successful for " + email + "!");
							System.out.println("-----------------");
						} catch (IOException e) {
							e.printStackTrace();
						}

					} else {
						System.out.println("Login failed for " + email + ".");
					}
				} catch (Exception e) {
					System.out.println("Login failed for " + email + ".");
					driver.get("https://www.facebook.com/");
					System.out.println("-----------------");
					continue;
				}
				robot.mouseMove(1048, 96);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				driver.get("https://www.facebook.com/");

//				driver.get("javascript:void(function(){ function deleteAllCookiesFromCurrentDomain() { var cookies = document.cookie.split(\"; \"); for (var c = 0; c < cookies.length; c++) { var d = window.location.hostname.split(\".\"); while (d.length > 0) { var cookieBase = encodeURIComponent(cookies[c].split(\";\")[0].split(\"=\")[0]) + '=; expires=Thu, 01-Jan-1970 00:00:01 GMT; domain=' + d.join('.') + ' ;path='; var p = location.pathname.split('/'); document.cookie = cookieBase + '/'; while (p.length > 0) { document.cookie = cookieBase + p.join('/'); p.pop(); }; d.shift(); } } } deleteAllCookiesFromCurrentDomain(); location.href = 'https://facebook.com'; })();");
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.quit();
	}

}