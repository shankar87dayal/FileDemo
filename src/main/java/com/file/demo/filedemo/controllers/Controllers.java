package com.file.demo.filedemo.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.file.demo.filedemo.playload.FileResponse;
import com.file.demo.filedemo.services.FileService;



@RestController
@RequestMapping("/file")
public class Controllers {

	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(
			 @RequestParam("image") MultipartFile image
			)
	{
		try {
			String fileName = this.fileService.uploadImage(path, image);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new FileResponse(fileName null, message: "image is not uploaded due to error on  server  !!"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		return new ResponseEntity<>(new FileResponse(fileName, message: "Image is successfully uploaded !!"),HttpStatus.OK);
	
	}
	
}
