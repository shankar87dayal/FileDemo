package com.file.demo.filedemo.controllers;

import java.awt.PageAttributes.MediaType;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.file.demo.filedemo.playload.FileResponse;
import com.file.demo.filedemo.services.FileService;



@RestController
@RequestMapping("/file")
public class FileControllers {

	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(
			 @RequestParam("image") MultipartFile image
			)
	{
		String fileName = null;
		try {
			 fileName = this.fileService.uploadImage(path, image);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new FileResponse(null, "image is not uploaded due to error on  server  !!"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		return new ResponseEntity<>(new FileResponse(fileName, "Image is successfully uploaded !!"),HttpStatus.OK);
	
	}
	
	// methods to serve files
	@GetMapping( value ="/profiles/{imageName}",produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response
			) throws IOException 
	{
	 
		InputStream resource =this.fileService.getResource(path, imageName);
		response.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
		
	}
	
	
	//localhost:8080/images/abc.png
	
}











