package com.card.demo.util;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.card.demo.model.Bankdetail;



public class BankDetailLoader {
	static int totalFields = 3;
	static char defaultDelimiter = ',';

	public Bankdetail[] getBankDetails(byte[] bytes, char delimiter) throws FileValidateException{
        ArrayList<Bankdetail> list = new ArrayList<Bankdetail>();

		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(bis)); 
		StringBuilder sb = new StringBuilder(); 
			
		String line = null;
		int lineNumber=1;
		try { 
			while ((line = reader.readLine()) != null) { 
               String[] lineFields = processLine(line, delimiter); 
			   if(lineFields!=null){
				   Bankdetail bankdetail = new Bankdetail();
				   bankdetail.setBankName(lineFields[0]);
				   bankdetail.setCardNumber(lineFields[1]);
				   bankdetail.setExpiryDate(lineFields[2]);
				   list.add(bankdetail);				   
			   }else{
				   String msg = String.format("invalid record at line %d", lineNumber);
				   throw new FileValidateException(msg);
			   }
			   lineNumber++;

			   sb.append(line + "\n"); 
			} 
		} catch (IOException e) { 
			String msg = "file IOException";
		    throw new FileValidateException(msg);			
		} finally { 
			try { 
				bis.close(); 
			} catch (IOException e) { 
				e.printStackTrace(); 
			} 
		} 
		return list.size()>0?list.toArray(new Bankdetail[list.size()]):null;
	}

	
	private String[] processLine(String lex, char delimiter){
        String token="";
        ArrayList<String> list = new ArrayList<String>();

        StringBuffer sb = new StringBuffer();
        int index = 0;
        while(index<lex.length()){
            char  cTemp= lex.charAt(index);
            if(cTemp==delimiter){
                token = sb.toString().trim();
                sb.delete(0,sb.length());
                list.add(token);
            }
            else{
                sb.append(cTemp);
            }
            index++;
        }
        
        if(index==lex.length()&&index>0){
            token = sb.toString().trim();
            sb.delete(0,sb.length());
            list.add(token);
        }
        
        return list.size()==totalFields?list.toArray(new String[list.size()]):null;
    }
	
	
	public List<Bankdetail> sortBankDetailsByExpiryDate(List<Bankdetail> bankDetails){
		Collections.sort(bankDetails,new Comparator<Bankdetail>() {
		    @Override
			public int compare(Bankdetail p, Bankdetail q) {

	             DateFormat df = new SimpleDateFormat("MMM-yyyy");
	             Date Pdate = null;
	             Date Qdate= null;
	                try {
		                Pdate = df.parse(p.getExpiryDate());
		                Qdate = df.parse(q.getExpiryDate());
	                } catch (Exception e) {
	                	e.printStackTrace();
	                }    
	               return Pdate.compareTo(Qdate)*(-1);
		    }
		});
		
		return bankDetails;
	}
	
}

