package com.card.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.card.demo.model.Bankdetail;
import com.card.demo.service.BankdetailService;
import com.card.demo.model.BankDetailFile;
import com.card.demo.util.FileValidateException;
import com.card.demo.util.FileValidator;
import com.card.demo.util.BankDetailLoader;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;



@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	private static Validator validator;
	
	@Autowired
	BankdetailService bankdetailService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	FileValidator fileValidator;


	
	@InitBinder("bankDetailFile")
	protected void initBinder(WebDataBinder binder) {
	   binder.setValidator(fileValidator);
	}

	
	/**
	 * This method will list all existing bankdetails.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listBankDetails(ModelMap model) {

		List<Bankdetail> bankdetails = bankdetailService.findAllBankdetails();
		for(Bankdetail bankdetail: bankdetails){
			String cardNumber = bankdetail.getCardNumber();
			cardNumber = maskCardNumber(cardNumber);
			bankdetail.setCardNumber(cardNumber);
		}
		model.addAttribute("bankdetails", bankdetails);
		return "bankdetailslist";
	}

	private String maskCardNumber(String cardNumber){
		String maskedCardNumber = cardNumber.substring(4);
		maskedCardNumber = maskedCardNumber.replaceAll("[0-9]","x");
		maskedCardNumber = cardNumber.substring(0, 4) + maskedCardNumber; 
		return maskedCardNumber;
	}
	
	/**
	 * This method will provide the medium to add a new bankdetail.
	 */
	@RequestMapping(value = { "/newbankdetail" }, method = RequestMethod.GET)
	public String newBankDetail(ModelMap model) {
		Bankdetail bankdetail = new Bankdetail();
		model.addAttribute("bankdetail", bankdetail);
		model.addAttribute("edit", false);
		return "add";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving bankdetail in database. It also validates the bankdetail input
	 */
	@RequestMapping(value = { "/newbankdetail" }, method = RequestMethod.POST)
	public String saveBankDetail(@Valid Bankdetail bankdetail, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "add";
		}

    	if(bankdetailService.isBankdetailExist(bankdetail)){
    		bankdetailService.updateBankdetail(bankdetail);
    		model.addAttribute("success", "bankdetail " + bankdetail.getBankName() + " "+ bankdetail.getCardNumber() + " updated successfully");
    		
    	}else{
    		bankdetailService.saveBankdetail(bankdetail);
    		model.addAttribute("success", "bankdetail " + bankdetail.getBankName() + " "+ bankdetail.getCardNumber() + " registered successfully");
    	}


		return "addsuccess";
	}


	/**
	 * This method will provide the medium to update an existing bankdetail.
	 */
	@RequestMapping(value = { "/edit-bankdetail-{id}" }, method = RequestMethod.GET)
	public String editBankDetail(@PathVariable int id, ModelMap model) {
		Bankdetail bankdetail = bankdetailService.findById(id);
		model.addAttribute("bankdetail", bankdetail);
		model.addAttribute("edit", true);
		return "add";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating bankdetail in database. It also validates the bankdetail input
	 */
	@RequestMapping(value = { "/edit-bankdetail-{id}" }, method = RequestMethod.POST)
	public String updateBankdetail(@Valid Bankdetail bankDetail, BindingResult result,
			ModelMap model, @PathVariable String id) {

		if (result.hasErrors()) {
			return "add";
		}

		bankdetailService.updateBankdetail(bankDetail);

		model.addAttribute("success", "Bankdetail " + bankDetail.getBankName() + " "+ bankDetail.getCardNumber() + " updated successfully");
		return "addsuccess";
	}

	
	/**
	 * This method will delete a bankdetail by it's card number value.
	 */
	@RequestMapping(value = { "/delete-bankdetail-{id}" }, method = RequestMethod.GET)
	public String deleteBankDetail(@PathVariable int id) {
		bankdetailService.deleteBankdetailById(id);
		return "redirect:/list";
	}
	

	/**
	 * This method will provide the medium to upload new bankdetails.
	 */
	@RequestMapping(value = { "/add-document" }, method = RequestMethod.GET)
	public String uploadFile(ModelMap model) {
		BankDetailFile bankDetailFile = new BankDetailFile();
		model.addAttribute("bankDetailFile", bankDetailFile);
		return "upload";
	}

	

	@RequestMapping(value = { "/add-document" }, method = RequestMethod.POST)
	public String uploadDocument(@Valid BankDetailFile bankDetailFile, BindingResult result, ModelMap model){
		MultipartFile multipartFile = bankDetailFile.getFile();
		
		String fileName = multipartFile.getOriginalFilename();
		String fileType = bankDetailFile.getFileType();
		
		if (result.hasErrors()||!fileType.equalsIgnoreCase("CSV")) {

			model.addAttribute("failure", "The file " + fileName + " with the file format "+ fileType + " failed to be uploaded!");
			
			return "uploadfailure";
		} else {
			
			System.out.println("Fetching file");
			try{
				bandDetailWebLoader(bankDetailFile);
			}catch(FileValidateException e){
				model.addAttribute("failure", "The file " + fileName + " with file format "+ fileType + " failed to be validated!");
				return "uploadfailure";
			}
			model.addAttribute("success", "The file " + fileName + " with file format "+ fileType + " has been validated and processed successfully!");

			return "uploadsuccess";
		}
	}
	
	private void bandDetailWebLoader(BankDetailFile bankDetailFile) throws FileValidateException{
		
		MultipartFile multipartFile = bankDetailFile.getFile();
		
		byte[]  bytes = null;
		try{
			bytes = multipartFile.getBytes();
		}catch(IOException e){
			String msg = "file IOException";
		    throw new FileValidateException(msg);			
		}

		BankDetailLoader bankDetailLoader = new BankDetailLoader(); 
		Bankdetail[] bankdetails = bankDetailLoader.getBankDetails(bytes, ',');

       
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
		
        for(Bankdetail bkdetail: bankdetails){
            Set<ConstraintViolation<Bankdetail>> constraintViolations
        		= validator.validate(bkdetail);
            if(constraintViolations.size()>0){
    			String msg = "file record validation error!";
    		    throw new FileValidateException(msg);			
            }

        	if(bankdetailService.isBankdetailExist(bkdetail)){
        		
        		bankdetailService.updateBankdetail(bkdetail);
        	}else{
        		
        		bankdetailService.saveBankdetail(bkdetail);
        	}
        }
		
	}
	
}
