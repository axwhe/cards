package com.card.demo.dao;

import java.util.List;

import com.card.demo.model.Bankdetail;


public interface BankdetailDao {

	Bankdetail findById(int id);
	
	Bankdetail findByCardNumber(String cardNumber);
	
	void save(Bankdetail bankdetail);

	void updateBankDetail(Bankdetail bankdetail);

	void deleteById(Integer id);
	
	void deleteAllCardDetails();
	
	List<Bankdetail> findAllBankdetails();

}

