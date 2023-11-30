package pageClass;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseLayer.TestBase;
import utility.GetData;

public class LoginPage extends TestBase {

	TestBase testObj = null;
	GetData dataObj = new GetData();

	@FindBy(id = "unameSignin")
	private WebElement userName;

	@FindBy(id = "pwdSignin")
	private WebElement password;

	@FindBy(id = "registration2")
	private WebElement registrationLink;

	public LoginPage() {
		testObj = new TestBase();
		intialiseElements();
	}

	public void intialiseElements() {
		PageFactory.initElements(TestBase.getDriver(), this);
	}

	public void invalidLogin() {

		userName.sendKeys(dataObj.getValueFromExcel(testObj.getConfigValue("testDataFilePath"), "Data", 2, "UserName"));
		password.sendKeys(dataObj.getValueFromExcel(testObj.getConfigValue("testDataFilePath"), "Data", 2, "Password"));
	}

	public void validLogin() {

		userName.sendKeys(dataObj.getValueFromExcel(testObj.getConfigValue("testDataFilePath"), "Data", 1, "UserName"));
		password.sendKeys(dataObj.getValueFromExcel(testObj.getConfigValue("testDataFilePath"), "Data", 1, "Password"));
	}

	public void clickOnRegistrationLink() {
		new LoginPage().clickOnElement(getElement("id", "registration2"));
	}

}
