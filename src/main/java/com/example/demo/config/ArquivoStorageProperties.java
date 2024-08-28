package com.example.demo.config;

import org.springframework.stereotype.Component;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;

 @Getter
@Component
public class ArquivoStorageProperties {
	
	@Value("${arquivo.uploadDir}")
	private String uploadDir;
}
