package com.qualitystream.tutorial;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
//import org.openqa.selenium.JavascriptExecutor;

public class GoogleSearchTest {
	
	private WebDriver driver;

	By videoLinkLocator = By.cssSelector("a[href='https://www.youtube.com/watch?v=R_hh3jAqn8M']");
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
	}

	@Test
	public void testGooglePage() {

		//Aqui desimos encuentra este elemento
	    WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.clear();
		searchBox.sendKeys("quality-stream Introducción a la Automatización de Pruebas de Software");
		searchBox.submit();
		//implicit Wait con la siguiente sintaxis --------------
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//Explicit Wait con la siguiente sintaxis --------------
		//WebDriverWait ewait = new WebDriverWait(driver, 10);
		//Aqui le pasamos la condicion que queremos que espere
		// el ExpectedConditions tiene varios elementos que nos ayuda dependiendo de lo que necesitemos
		// se escogera el elemento titleContains esto dice que espera un string esperado en el titulo "quality-stream"
		//ewait.until(ExpectedConditions.titleContains("quality-stream"));
		//Luego comparamos valores el titulo vs la busqueda esperada en la pagina de google
		//assertEquals("quality-stream Introducción a la Automatización de Pruebas de Software - Buscar con Google",driver.getTitle());

		//Fluent wait

		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
				//Su tiempo de Espera maximo sea de  10 segundos
				.withTimeout(10, TimeUnit.SECONDS)
				//Que realize consultas a la pagina cada 2 segundos
				.pollingEvery(2, TimeUnit.SECONDS)
				//Que ignore el NoSuchElementException en caso de que este sea sacado por el sistema
				.ignoring(NoSuchElementException.class);

		//Aqui le decimos que espere hasta que este elemento video aparesca, el espera 10 seg,
		// haciendo consultas cada
		//2 seg
		WebElement video = fwait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver webDriver) {
				return driver.findElement(videoLinkLocator);
			}
		});
		//Para confirmar que el video que estamos buscando este presente en la pagina
		assertTrue(driver.findElement(videoLinkLocator).isDisplayed());



	}
	
	/*@Test void localizadores() {
		 
		By locator = By.id("id_del_elemento");
		
		By locator_name = By.name("name_elemnt");
		
		By locator_className = By.className("clase_elemento");
		
		By locator_tagName = By.tagName("tag");
		
		By locator_linktext = By.linkText("texto_link");
		
		By locator_partialLinkText = By.partialLinkText("parte_texto");
		
		By locator_cssSelector = By.cssSelector("input[name='q']");
		
		By locator_Xpath = By.xpath("//input[@name='q']");
		
		// JavaScript
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 WebElement searchBox = (WebElement)js.executeScript("return document.getElementsByName('q')[0]");
		
	}*/
	
	@After
	public void tearDown() {
		//driver.quit();
	}

}
