package com.file.demo.filedemo.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.file.demo.filedemo.services.FileService;

@Service
public class FileServicesImpl implements FileService{
	
 @Override
	public String uploadImage(String path, MultipartFile file) throws IOException
	{
	 
	 //File name
	 String name = file.getOriginalFilename();
	 
	 //abc.png
	 
	 //random name generate file
	 
	 String randomID = UUID.randomUUID().toString();
	 String fileName1 =  randomID.concat(name.substring(name.lastIndexOf(".")));
	 
	 
	 //Fullpath
	 String filePath = path + File.separator +fileName1;
	 
	 
	 
	 //create folder if not created
	 File f = new File(path);
	 if(!f.exists())
	 {
		 f.mkdir();
	 }
	 
     //	file copy
	 Files.copy(file.getInputStream(), Paths.get(filePath));
	 
	 return name;
	}
 
}
 
 
 
 
 
 

