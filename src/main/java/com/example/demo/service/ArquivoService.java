package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.example.demo.config.ArquivoStorageProperties;

import exception.UploadArquivoException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArquivoService {
	
	private final Path fileStorageLocation;
	
	public ArquivoService(ArquivoStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		try { 
			Files.createDirectory(this.fileStorageLocation); 
		} catch (IOException e) {
			throw new UploadArquivoException("Algo deu errado ao tentar criar a pasta ", e);
		}
	}
	
	
	
	public String getContentType(HttpServletRequest request,  Resource resource) {
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception e) {
			log.error("Nao foi possivel determinar o tipo de aquivo");
		}
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		return contentType;
	}
}
