import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.P2E.utils.WebUtil;

import lombok.Getter;
@Getter
public class FlashOtpIDBalamceOr {

	
	protected FlashOtpIDBalamceOr(WebUtil util) {
		PageFactory.initElements(util.getDriver(), this);
	}

	@FindBy(xpath = "//input[@placeholder='Enter Email']")
	private WebElement userName;

	@FindBy(xpath = "//input[@placeholder='Enter Password']")
	private WebElement userPassword;
	
	
	
	
}
