package com.org.infy.proxy.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.org.infy.proxy.model.FileInfo;
import com.org.infy.proxy.model.ICountStore;
import com.org.infy.proxy.service.FileStorageService;
import com.org.infy.proxy.service.ProxyService;
import com.org.infy.proxy.util.ResponseHelper;
import com.org.infy.proxy.util.Utility;
import org.springframework.core.io.Resource;

@RestController
public class ProxyController {
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ProxyService proxyService;
	@Autowired
	private FileStorageService fileStorageService;

	@GetMapping("/proxy/get/user/details")
	public ResponseEntity<?> getUserDetails(@RequestParam String emailId) {

		long startTime = System.currentTimeMillis();

		List<ICountStore> icountStore = proxyService.getUserDetails(emailId);

		long endTime = System.currentTimeMillis();

		logger.info("Total processing time for step " + (endTime - startTime));


		return new ResponseEntity<>(icountStore, HttpStatus.OK);

	}	
	
	@PostMapping("/proxy/download/appreciation")
	public ResponseEntity<?> appreciationDownload(@RequestParam String emailId, @RequestParam String fileName, HttpServletRequest request) {
		Resource resource = null;
		String contentType = null;
		ICountStore objStore = proxyService.downloadAppreciation(emailId.trim(),fileName.trim());
		if (null!=objStore) {
			 for (FileInfo fileInfo : objStore.getAppreciation().get(0).getFileInfo()) 
			 { 
				 resource = fileStorageService.loadFileAsResource(fileInfo.getFilePath());
			 }  
			 
		        try {
		            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		        } catch (IOException ex) {
		            logger.info("Could not determine file type.");
		        }

		        // Fallback to the default content type if type could not be determined
		        if(contentType == null) {
		            contentType = "application/octet-stream";
		        }
		} else {
			return new ResponseEntity<>("Bad request, pls check request payload!", HttpStatus.BAD_REQUEST);
		}
		
		logger.info("File Name is :"+fileName);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(resource);
	}
	
	@PostMapping("/proxy/download/course")
	public ResponseEntity<?> courseDownload(@RequestParam String emailId, @RequestParam String fileName, HttpServletRequest request) {
		Resource resource = null;
		String contentType = null;
		ICountStore objStore = proxyService.downloadCourse(emailId.trim(),fileName.trim());
		if (null!=objStore) {
			 for (FileInfo fileInfo : objStore.getCourse().get(0).getFileInfo()) 
			 { 
				 resource = fileStorageService.loadFileAsResource(fileInfo.getFilePath());
			 }  
			 
		        try {
		            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		        } catch (IOException ex) {
		            logger.info("Could not determine file type.");
		        }

		        // Fallback to the default content type if type could not be determined
		        if(contentType == null) {
		            contentType = "application/octet-stream";
		        }
		} else {
			return new ResponseEntity<>("Bad request, pls check request payload!", HttpStatus.BAD_REQUEST);
		}
		
		logger.info("File Name is :"+fileName);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(resource);
	}
	
	@PostMapping("/proxy/download/feedback")
	public ResponseEntity<?> feedbackDownload(@RequestParam String emailId, @RequestParam String fileName, HttpServletRequest request) {
		Resource resource = null;
		String contentType = null;
		ICountStore objStore = proxyService.downloadFeedback(emailId.trim(),fileName.trim());
		if (null!=objStore) {
			 for (FileInfo fileInfo : objStore.getFeedback().get(0).getFileInfo()) 
			 { 
				 resource = fileStorageService.loadFileAsResource(fileInfo.getFilePath());
			 }  
			 
		        try {
		            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		        } catch (IOException ex) {
		            logger.info("Could not determine file type.");
		        }

		        // Fallback to the default content type if type could not be determined
		        if(contentType == null) {
		            contentType = "application/octet-stream";
		        }
		} else {
			return new ResponseEntity<>("Bad request, pls check request payload!", HttpStatus.BAD_REQUEST);
		}
		
		logger.info("File Name is :"+fileName);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(resource);
	}
	
	@PostMapping("/proxy/download/task")
	public ResponseEntity<?> taskDownload(@RequestParam String emailId, @RequestParam String fileName, HttpServletRequest request) {
		Resource resource = null;
		String contentType = null;
		ICountStore objStore = proxyService.downloadTask(emailId.trim(),fileName.trim());
		if (null!=objStore) {
			 for (FileInfo fileInfo : objStore.getTask().get(0).getFileInfo()) 
			 { 
				 resource = fileStorageService.loadFileAsResource(fileInfo.getFilePath());
			 }  
			 
		        try {
		            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		        } catch (IOException ex) {
		            logger.info("Could not determine file type.");
		        }

		        // Fallback to the default content type if type could not be determined
		        if(contentType == null) {
		            contentType = "application/octet-stream";
		        }
		} else {
			return new ResponseEntity<>("Bad request, pls check request payload!", HttpStatus.BAD_REQUEST);
		}
		
		logger.info("File Name is :"+fileName);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(resource);
	}
}
