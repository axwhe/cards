package com.card.demo.service;

import java.util.List;

import com.card.demo.dao.BankdetailDao;
import com.card.demo.model.Bankdetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("bankdetailService")
@Transactional
public class BankdetailServiceImpl implements BankdetailService{

	@Autowired
	private BankdetailDao bankdetailDao;
	
	public Bankdetail findById(int id){
		return bankdetailDao.findById(id);
	}

	public Bankdetail findByCardNumber(String cardNumber){
		return bankdetailDao.findByCardNumber(cardNumber);
	}

	public void saveBankdetail(Bankdetail bankdetail){
		bankdetailDao.save(bankdetail);
	}

	public void updateBankdetail(Bankdetail bankdetail){
		bankdetailDao.updateBankDetail(bankdetail);
	}

  
	public void deleteBankdetailById(Integer id){
		bankdetailDao.deleteById(id);
	}
	
	public void deleteAllBankdetails(){
		bankdetailDao.deleteAllCardDetails();
	}

	public List<Bankdetail> findAllBankdetails(){
		return bankdetailDao.findAllBankdetails();
	}

	public boolean isBankdetailExist(Bankdetail bankdetail){
		return bankdetailDao.findByCardNumber(bankdetail.getCardNumber()) != null; 
	}

}
