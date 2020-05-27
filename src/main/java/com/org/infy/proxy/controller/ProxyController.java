package com.org.infy.proxy.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.org.infy.proxy.model.ICountStore;
import com.org.infy.proxy.service.ProxyService;
import com.org.infy.proxy.util.ResponseHelper;
import com.org.infy.proxy.util.Utility;

@RestController
public class ProxyController {
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ProxyService proxyService;
	int index = 0;

	@PostMapping("/proxy/download/report")
	public ResponseEntity reportDownload(@RequestParam String emailId) {

		List<ICountStore> objRet = proxyService.downloadReport(emailId);
		byte[] arr=null;
		ByteArrayResource resource = null;
		String reportName =  null;
		for(ICountStore obj : objRet) {
			for (Binary binary : obj.getAppreciation().get(0).getFile()) {
				arr = binary.getData();
				resource = new ByteArrayResource(arr);
			}
			for (String fileName : obj.getAppreciation().get(0).getFileName()) {
				reportName = fileName;
			}
		}
		System.out.println("Report name is :"+reportName);
		//byte[] arr =null;
		//ByteArrayResource resource = new ByteArrayResource(arr);
		/*if (objRet != null) {
			for (ICountStore obj : objRet) {
				List<Binary> document = obj.getAppreciation().get(0).getFile();
				if (document != null) {
					document.stream().forEach(doc -> {
						try {
							FileOutputStream fileOuputStream = null;
							// fileOuputStream = new FileOutputStream("D:\\samrat.basu01\\D-Drive\\eclipse
							// workspace\\proxy-service\\input-file");
							// fileOuputStream.write(doc.getData());
							System.out.println(doc.getType() + "   Print Doc  " + doc.getData());
						} catch (Exception e) {
							e.printStackTrace();
						}
					});

				}
			}

		}*/

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType("application/octet-stream"))
				.header("Content-Disposition", "attachment; filename="+reportName)
				.body(resource);
	//	return (ResponseEntity) ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType("application/octet-stream"))
			//	.header("Content-Disposition", "attachment; filename=");
	}
}
