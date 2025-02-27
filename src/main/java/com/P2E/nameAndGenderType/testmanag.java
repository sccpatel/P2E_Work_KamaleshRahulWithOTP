package com.P2E.nameAndGenderType;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testmanag {
	@Test
	public void programe() {
		String str = "Automation";
		char ch = 'A';
		int count = 0;
		for (char c : str.toCharArray()) {
			if (c == ch)
				count++;
		}
		System.out.println(count);

	}

	@Test
	public void testingName1() {

		int  a = 100; 
		int b = 200;
		try {
			Assert.assertEquals(a, b);    /////  it is Hard Assertion 
		}catch(Exception e) {
			System.out.println("Exception Come");
		}
		System.out.println("Virendra");
		
	}

	@Test(dependsOnMethods="", groups={"smoke","regression","Sanity","Validation"})
	public void testingName2() {
		SoftAssert as = new SoftAssert();
		as.assertAll();
	as.assertEquals("Ram", "Aram");
	
	System.out.println("Raja");
	}
	
	@DataProvider
	public Object[][] dataMycom() {
		String[] contry = {"delhi", "Agra", "Hydarabad"};
		String[] city = {"Red Frot", "Tajmahal", "charminar"};
		String doubleArry[][]= {contry, city};
		return  doubleArry;
	}
	
	@Test(dataProvider="dataMycom")
	public void testingName3(String co, String ci) {
System.out.println(co+" Second  "+ci);
	}
	
	
	
	
}
