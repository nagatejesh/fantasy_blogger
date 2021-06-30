package com.nbc.service;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nbc.entity.ImageClass;
import com.nbc.exception.FileStorageException;
import com.nbc.repository.ImageRepository;

@Service
public class ImageStorageService {

	@Autowired
	private ImageRepository imgRepo;
	
	public ImageClass storeFile(MultipartFile file) throws FileStorageException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if(fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence "+ fileName);
			}
			ImageClass img = new ImageClass();
			img.setFileName(fileName);
			img.setFileType(file.getContentType());
			img.setData(file.getBytes());
			img.setFileSize(file.getSize());
			img.setCompressedData(getCompressedImage(file));
			return imgRepo.save(img);
		} catch (IOException e) {
			throw new FileStorageException("Could not store file "+fileName+". Please try again!",e);
		}
	}
	
	public byte[] getCompressedImage(MultipartFile file){
		
		
		try {
			BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
			Graphics2D gra = bufferedImage.createGraphics();
			gra.setComposite(AlphaComposite.Src);
	        gra.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        gra.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
	        gra.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	        gra.drawImage(bufferedImage, null, bufferedImage.getHeight(), bufferedImage.getWidth());
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(bufferedImage, "jpg", baos);
	        return baos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ImageClass storeFile(ImageClass img, String caption) {
		img.setCaption(caption);
		String[] split = img.getFileType().split("_");
		img.setFileType(split[split.length-1]);
		img.setUploadDate(Date.valueOf(LocalDate.now()));
		System.out.println(Base64.getEncoder().encodeToString(img.getData()).length());
		img.setFileDownloadUri(this.generateFileDownloadUri(img));
		return imgRepo.save(img);
	}
	
	public String generateFileDownloadUri(ImageClass img) {
		return ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFile/").path(String.valueOf(img.getId())).toUriString();
	}
	
	public ImageClass saveImage(ImageClass img) {
		return imgRepo.save(img);
	}
	
	public ImageClass getImage(long imgId) {
		return imgRepo.findById(imgId).orElse(null);
	}
	
	public List<ImageClass> getAllImages(){
		return imgRepo.findAll();
	}
	
	public void deleteImage(long imgId) {
		imgRepo.deleteById(imgId);
	}
	
}
