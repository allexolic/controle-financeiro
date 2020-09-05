package com.controlefinanceiro.dosmoros.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.controlefinanceiro.dosmoros.service.AWSS3Service;

@Service
public class AWSS3ServiceImpl implements AWSS3Service{

	private static final Logger LOGGER = LoggerFactory.getLogger(AWSS3ServiceImpl.class);
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("${aws.s3.bucket}")
	private String bucketName;
	
	@Value("${aws.s3.endpointUrl}")
	private String endpointUrl;
	
	public void uploadFile(final MultipartFile multipartFile) {
		LOGGER.info("Upload do arquivo em andamento.");
		
		try {
			final File file = convertMultiPartToFile(multipartFile);
			uploadFileToS3Bucket(bucketName, file);
			LOGGER.info("Upload concluído.");
			file.delete();
		} catch(final AmazonServiceException ex) {
			LOGGER.info("Falha no upload do arquivo");
			LOGGER.error("Erro = {} durante upload do arquivo.", ex.getMessage());
		}
	}
	
	private File convertMultiPartToFile(final MultipartFile multipartFile) {
		final File file = new File(multipartFile.getOriginalFilename());
		
		try (final FileOutputStream outputStream = new FileOutputStream(file)){
			outputStream.write(multipartFile.getBytes());
		} catch (final IOException ex){
			LOGGER.error("Erro ao converter arquivo para = ", ex.getMessage());
		}
		return file;
	}
	
	private void uploadFileToS3Bucket(final String bucketName, final File file) {
		final String uniqueFileName = file.getName();
		LOGGER.info("Upload do arquivo = " + uniqueFileName);
		final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file);
		amazonS3.putObject(putObjectRequest);
	}
	
	
	public void deleteFile(String fileName) {		
		try {
			amazonS3.deleteObject(new DeleteObjectRequest(bucketName, fileName));
		} catch (AmazonServiceException ex) {
			LOGGER.error("Erro = {} durante a exclusão do arquivo.", ex.getMessage());
		}
	}
	/*
	public void deleteFileFromS3Bucket(String fileUrl) {
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName +"/", fileName);
		amazonS3.deleteObject(deleteObjectRequest);
	}
	*/
		
	@Async
	public byte[] downloadFile(final String keyName) {
		byte[] content = null;
		LOGGER.info("Downloading of file..." + keyName);
		final S3Object s3Object = amazonS3.getObject(bucketName, keyName);
		final S3ObjectInputStream s3Stream = s3Object.getObjectContent();
		
		try {
			content = IOUtils.toByteArray(s3Stream);
			LOGGER.info("Arquivo baixado com sucesso!");
			s3Object.close();
		}catch(final IOException e) {
			LOGGER.info("Erro = " + e.getMessage());
		}
		return content;
	}
}
