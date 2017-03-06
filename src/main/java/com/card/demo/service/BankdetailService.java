package com.card.demo.service;


import com.card.demo.model.Bankdetail;

import java.util.List;

public interface BankdetailService {
	
	Bankdetail findById(int id);

	Bankdetail findByCardNumber(String cardNumber);

	void saveBankdetail(Bankdetail bankdetail);

	void updateBankdetail(Bankdetail bankdetail);

	void deleteBankdetailById(Integer id);

	void deleteAllBankdetails();

	List<Bankdetail> findAllBankdetails();

	boolean isBankdetailExist(Bankdetail bankdetail);
	
}