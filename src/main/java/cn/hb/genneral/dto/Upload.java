package cn.hb.genneral.dto;

import java.io.File;
import java.util.Arrays;

import lombok.ToString;


public class Upload {
	//private File[] files;
	private String scope;
	private String fileToken;
	private Integer size;
	private String type;
	private String storeId;
	private String isSingle;
	private String fileName;
	
	
	
	@Override
	public String toString() {
		return "Upload [ scope=" + scope + ", fileToken=" + fileToken + ", size="
				+ size + ", type=" + type + ", storeId=" + storeId + ", isSingle=" + isSingle + ", fileName=" + fileName
				+ "]";
	}

	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getFileToken() {
		return fileToken;
	}
	public void setFileToken(String fileToken) {
		this.fileToken = fileToken;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getIsSingle() {
		return isSingle;
	}
	public void setIsSingle(String isSingle) {
		this.isSingle = isSingle;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
