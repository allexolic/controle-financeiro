package com.controlefinanceiro.dosmoros.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {
	
	private final String UPLOAD_DIR = "C:/docs/";
	
	@GetMapping("/docs")
	public String telaDocs() {
		return "docs";
	}

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attr) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(fileName.isEmpty()) {
			fileName = "NÃO INFORMADO";
			System.out.println(fileName);
			return "redirect:/docs";
		} 
		
		
		try {
			
			Path path = Paths.get(UPLOAD_DIR + "001" + fileName);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		attr.addFlashAttribute("message", "Documento vinculado com sucesso!");
		
		return "redirect:/docs";
	}
	
}
