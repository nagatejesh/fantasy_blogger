package com.nbc.entity;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class ImageClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String fileName;
	private String caption;
	private String fileType;
	private String fileSize;
	private Date uploadDate;
	@Column(name = "data",columnDefinition = "bytea")
	private byte[] data;
	@Column(name = "compressed_data",columnDefinition = "bytea")
	private byte[] compressedData;
	private String fileDownloadUri;
	private int views;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		if(fileSize<1024)
			this.fileSize = fileSize+"byte";
		else if(fileSize<1024*1024)
			this.fileSize = fileSize/1024+"KB";
		else if(fileSize>1024*1024)
			this.fileSize = fileSize/1024/1024+"MB";
		else
			this.fileSize = fileSize/1024/1024/1024+"GB";
	}
	public byte[] getCompressedData() {
		return compressedData;
	}
	public void setCompressedData(byte[] copressedData) {
		this.compressedData = copressedData;
	}
	
}
