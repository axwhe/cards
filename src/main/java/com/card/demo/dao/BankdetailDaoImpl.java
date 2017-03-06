package com.card.demo.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Repository;

import com.card.demo.model.Bankdetail;


@Repository("bankdetailDao")
public class BankdetailDaoImpl extends AbstractDao<Integer, Bankdetail> implements BankdetailDao {

	
	public Bankdetail findById(int id){
		Bankdetail bankdetail = this.getByKey(id);
		return bankdetail;
	}
	
	public Bankdetail findByCardNumber(String cardNumber){
		try{
			Bankdetail bankdetail = (Bankdetail) getEntityManager()
					.createQuery("SELECT b FROM Bankdetail b WHERE b.cardNumber = :cardNumber")
					.setParameter("cardNumber", cardNumber)
					.getSingleResult();
			
			return bankdetail; 
		}catch(NoResultException ex){
			return null;
		}
	}
	
	public void save(Bankdetail bankdetail){
		persist(bankdetail);
	}
	
	public void updateBankDetail(Bankdetail bankdetail){
		Bankdetail updateBankdetail =  findByCardNumber(bankdetail.getCardNumber());
		updateBankdetail.setBankName(bankdetail.getBankName());
		updateBankdetail.setExpiryDate(bankdetail.getExpiryDate());
	    update(updateBankdetail);	
	}
	
	public void deleteById(Integer id){
		Bankdetail bankdetail = (Bankdetail) getEntityManager()
				.createQuery("SELECT b FROM Bankdetail b WHERE b.id = :id")
				.setParameter("id", id)
				.getSingleResult();
		delete(bankdetail);
	}
	
	public void deleteAllCardDetails(){
		Query query = getEntityManager().createNativeQuery("DELETE FROM bankdetail");
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Bankdetail> findAllBankdetails(){
		List<Bankdetail> bankdetails =  getEntityManager()
				//.createQuery("SELECT b FROM Bankdetail b  ORDER BY b.expiryDate  desc")
				.createQuery("SELECT b FROM Bankdetail b")
				.getResultList();
		
		
		return sortBankDetailsByExpiryDate(bankdetails);
	}

	
	private List<Bankdetail> sortBankDetailsByExpiryDate(List<Bankdetail> bankDetails){
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
		
		for(Bankdetail db: bankDetails){
			System.out.println(db);
		}
		return bankDetails;
	}
}

/*
class CompareDate implements Comparator<Bankdetail>{

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
               return Pdate.compareTo(Qdate) > 0 ? 1 : 0;
    }
}
*/ 
