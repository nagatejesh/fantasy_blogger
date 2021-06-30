package com.nbc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nbc.entity.ImageClass;
import com.nbc.exception.FileStorageException;
import com.nbc.service.ImageStorageService;

@org.springframework.stereotype.Controller
public class uploadController {
	
	@Autowired
	private ImageStorageService imgService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<ImageClass> allImages = imgService.getAllImages();
		int x = allImages.size();
		if(x>10) x=10;
		model.addAttribute("images", allImages.subList(0, x));
		return "home";
	}
	
	@GetMapping("/upload")
	public String hello() {
		return "upload";
	}

	@PostMapping("/upload")
	public String uploadImage(@RequestParam("title") String caption,@RequestParam("file") MultipartFile file,Model model) throws FileStorageException {
		ImageClass img = imgService.storeFile(file);
		img = imgService.storeFile(img, caption);
		System.out.println(img);
		model.addAttribute("image",img);
			
		return "image";
	}
	
	@GetMapping("/x")
	public String x(Model model) {
		List<ImageClass> x = new ArrayList<>();
		x.add(imgService.getImage(1));
		x.add(imgService.getImage(2));
		model.addAttribute("images", x);
		return "home";
	}
	
	@GetMapping("/view/{id}")
	public String view(@PathVariable long id,Model model) {
		ImageClass image = imgService.getImage(id);
		if(image==null)
			return "redirect:/error";
		model.addAttribute("image", imgService.getImage(id));
		return "image";
	}
	
	@GetMapping("/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable long id) {
		imgService.deleteImage(id);
		return "Image has been deleted successfully";
	}
	
	@GetMapping("/error")
	@ResponseBody
	public String err() {
		return "Cannot process your request";
	}
	
	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable long fileId){
		ImageClass img = imgService.getImage(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(img.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+img.getFileName()+"\"")
				.body(new ByteArrayResource(img.getData()));
	}
	
}
