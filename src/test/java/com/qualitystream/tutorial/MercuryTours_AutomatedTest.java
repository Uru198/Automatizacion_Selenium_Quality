package com.qualitystream.tutorial;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercuryTours_AutomatedTest {
	
	private WebDriver driver;
	By registerLinkLocator = By.linkText("REGISTER");
	//en este localizador le estamos diciendo que se encuentra en la vista correcta
	By registerPageLocator = By.xpath("//img[@src='/logo.png']");
	
	By usernameLocator = By.id("email");
	By passwordLocator = By.name("password");
	By confirmPassworLocator = By.cssSelector("input[name='confirmPassword']");

	By registerBtnLocator = By.name("submit");
	
	By userLocator = By.name("userName");
	By passLocator = By.name("password");
	By signInBtnLocator = By.name("submit");
	
	By homePageLocator = By.xpath("//td//h3[text()='Login Successfully']");
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/test/newtours/index.php");
	}

	@After
	public void tearDown() throws Exception {
		//driver.quit();
	}

	@Test
	//Aqui se coloca cada uno de los pasos que quisieramos hacer en una pagina

	//Primer paso registrar usuario
	public void registerUser() throws InterruptedException {
		driver.findElement(registerLinkLocator).click();
		//Temporizador de cuanto se va demorar en cargar la accion
		Thread.sleep(2000);
		//Aqui desimo si encuentra el logo de registerPageLocator puede seguir el proceso
		//nos devolvera true o false (Boolean)
		if(driver.findElement(registerPageLocator).isDisplayed()) {
			//Aqui le decimos como claves los valores que se van a llenar en el input
			driver.findElement(usernameLocator).sendKeys("qualityadmin");
			driver.findElement(passwordLocator).sendKeys("pass1");
			driver.findElement(confirmPassworLocator).sendKeys("pass1");
			
			driver.findElement(registerBtnLocator).click();
		}
		else {
			System.out.print("Register pages was not found");
		}
		
		List<WebElement> fonts = driver.findElements(By.tagName("font"));

		//Aca se compara con el texto que se coloca a qui vs el texo de la pagina web
		assertEquals("Note: Your user name is qualityadmin.",fonts.get(5).getText());
		
	}
	
	@Test
	//Sgundo paso iniciar sesion
	public void signIn() throws InterruptedException {
		if(driver.findElement(userLocator).isDisplayed()){
			driver.findElement(userLocator).sendKeys("qualityadmin");
			driver.findElement(passLocator).sendKeys("pass1");
			driver.findElement(signInBtnLocator).click();
			Thread.sleep(2000);
			assertTrue(driver.findElement(homePageLocator).isDisplayed());
		}
		else
			System.out.println("username textbox was not present");
		
	}

}
