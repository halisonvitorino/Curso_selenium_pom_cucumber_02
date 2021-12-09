package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.LoginPage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            String cenario = scenario.getName();
            String erro = scenario.getStatus().toString();
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/screenshots/" + cenario + " - " + erro + " - " +".png"));
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Dado("o usuario na pagina de login da loja")
    public void o_usuario_na_pagina_de_login_da_loja() {
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Quando("insere seu usuario {string}")
    public void insere_seu_usuario(String string) {
        loginPage = new LoginPage(driver);
        loginPage.preencheUsuario(string);
    }

    @Quando("insere sua senha {string}")
    public void insere_sua_senha(String string) {
        loginPage.preencheSenha(string);
    }

    @Quando("aciona o botao de login")
    public void aciona_o_botao_de_login() {
        loginPage.acionaBotaoLogin();
    }

    @Entao("o usuario ve o texto {string} na pagina")
    public void o_usuario_ve_o_texto_na_pagina(String string) {
        String textoEsperado = string;
        String textoAtual = loginPage.getTitle().toString();
        Assertions.assertEquals(textoEsperado,textoAtual);
        System.out.println("************* O texto esperado é    : " + textoEsperado + " ******************");
        System.out.println("************* O texto encontrado foi: " + textoAtual + " ******************");
    }
}
