package com.card.demo.model;

import org.springframework.web.multipart.MultipartFile;

public class BankDetailFile {

	MultipartFile file;
	
	String fileType;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


}