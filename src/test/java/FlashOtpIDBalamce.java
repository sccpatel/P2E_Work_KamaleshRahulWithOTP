import org.testng.annotations.Test;

import com.P2E.utils.WebUtil;


public class FlashOtpIDBalamce extends FlashOtpIDBalamceOr{
	private WebUtil util;

	public FlashOtpIDBalamce(WebUtil util) {
		super(util);
		this.util = util;
	}

@Test
  public void f() {
	  System.out.println("Good");
//	   WebUtil ut = WebUtil.getInstance();;
	   util.launchBrowser("chrome");
	   util.openUrl("https://www.flashotp.in");
	   
	   util.sendKeys(getUserName(), "SCC1@gmail.com", "Login ID");
	   util.sendKeys(getUserPassword(), "Bhai@123", "Login Passowrd");
	  
	  
	  
  }


public static void main(String[] args) {
	new FlashOtpIDBalamce(null).f();;
}

}
