package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(how = How.NAME, using = "username")
    @CacheLookup
    private WebElement inputUserName;

    @FindBy(name="password")
    @CacheLookup
    private WebElement inputPassWord;

    @FindBy(xpath="//body/div[@id='mainPanel']/div[@id='bodyPanel']/div[@id='leftPanel']/div[@id='loginPanel']/form[1]/div[3]/input[1]")
    @CacheLookup
    private WebElement buttonLogin;

    @FindBy(xpath = "//p[contains(text(),'Experience the difference')]")
    @CacheLookup
    private WebElement title;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void preencheUsuario(String usuario) {
        inputUserName.sendKeys(usuario);
    }

    public void preencheSenha(String senha) {
        inputPassWord.sendKeys(senha);
    }

    public void acionaBotaoLogin() {
        buttonLogin.click();
    }

    public String getTitle() {
        String texto = title.getText();
        return texto;
    }
}
