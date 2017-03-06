package com.card.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.card.demo.model.BankDetailFile;



@Component
public class FileValidator implements Validator {
		
	public boolean supports(Class<?> clazz) {
		return BankDetailFile.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		BankDetailFile file = (BankDetailFile) obj;
			
		if(file.getFile()!=null){
			if (file.getFile().getSize() == 0) {
				errors.rejectValue("file", "missing.file");
			}
		}
	}
}

