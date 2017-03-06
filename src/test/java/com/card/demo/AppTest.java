package com.card.demo;

import java.util.Arrays;
import java.util.List;

import com.card.demo.model.Bankdetail;
import com.card.demo.util.BankDetailLoader;
import com.card.demo.util.FileValidateException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.card.demo.util.BankDetailLoader;
/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
 	   String inputText = "HSBC Canada,5601-2345-3446-5678,Nov-2017\nRoyal Bank of Canada,4519-4532-4524-2456,May-2017\nAmerican Express,3786-7334-8965-345,Dec-2018\nHSBC Canada,5601-2345-3446-1111,Jul-2017";
 	   try{
 		   BankDetailLoader bankDetailLoader = new BankDetailLoader();
 		   Bankdetail [] bankdetails = bankDetailLoader.getBankDetails(inputText.getBytes(), ',');
 		   List<Bankdetail> bds = Arrays.asList(bankdetails);
 		   System.out.println("Unsorted:");
 		   for(Bankdetail bankdetail: bankdetails ){
 			   System.out.println(bankdetail);
 		   }

 		   bankDetailLoader.sortBankDetailsByExpiryDate(bds);
 		  System.out.println("Sorted:");
 		   for(Bankdetail bankdetail: bds ){
 			   System.out.println(bankdetail);
 		   }
 		   
 	   }catch(FileValidateException e){
 		   System.out.println("throw FileValidateException!");
 	   }
    	
       assertTrue( true );
    }
}
