package com.controlefinanceiro.dosmoros.service;

import org.springframework.web.multipart.MultipartFile;

public interface AWSS3Service {

	void uploadFile(MultipartFile multipartFile);
	
	byte[] downloadFile(String keyName);
	//void downloadFile(String keyName, String downloadFilePath);
	void deleteFile(String FileName);
}
