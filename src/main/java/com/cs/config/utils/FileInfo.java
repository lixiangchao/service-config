package com.cs.config.utils;

import java.util.ArrayList;
import java.util.List;

public class FileInfo {
	public String path;
	public long lastModefi;
	public boolean isFolder;
	public String name;
	public List<FileInfo> chaildFiles=new ArrayList<FileInfo>();

	public FileInfo() {

	}

	// public FileInfo(File file) {
	// this.isFolder = file.isDirectory();
	// this.lastModefi = new Date(file.lastModified()).toString();
	// this.path = file.getPath();
	// this.name = file.getName();
	// if (this.isFolder && file.listFiles().length > 0) {
	// for (File child : file.listFiles()) {
	// this.chaildFiles.add(new FileInfo(child));
	// }
	// }
	// }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getLastModefi() {
		return lastModefi;
	}

	public void setLastModefi(long lastModefi) {
		this.lastModefi = lastModefi;
	}

	public boolean isFolder() {
		return isFolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FileInfo> getChaildFiles() {
		return chaildFiles;
	}

	public void setChaildFiles(List<FileInfo> chaildFiles) {
		this.chaildFiles = chaildFiles;
	}

}
