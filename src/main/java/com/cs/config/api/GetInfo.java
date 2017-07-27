package com.cs.config.api;

import java.io.File;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.cs.config.utils.FileInfo;
import com.cs.config.utils.PathLoader;

@Path("/getFileInfo")
public class GetInfo {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public FileInfo getFile(@QueryParam("version") String version, @QueryParam("filePath") String filePath) {
		File file = new File(PathLoader.getPath(version, filePath));
		if (!file.exists()) {
			return null;
		}
		FileInfo fileInfo = new FileInfo();
		genFileInfo(fileInfo, file,file.getPath());
		return fileInfo;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public FileInfo getFilePost(@FormParam("version") String version, @FormParam("filePath") String filePath) {
		File file = new File(PathLoader.getPath(version, filePath));
		if (!file.exists()) {
			return null;
		}
		FileInfo fileInfo = new FileInfo();
		genFileInfo(fileInfo, file,file.getPath());
		return fileInfo;
	}

	private void genFileInfo(FileInfo fileInfo, File file,String parentPath) {
		fileInfo.isFolder = file.isDirectory();
		fileInfo.lastModefi =file.lastModified();
		fileInfo.path = file.getPath().substring(parentPath.length());
		fileInfo.name = file.getName();
		if (fileInfo.isFolder && file.listFiles().length > 0) {
			for (File child : file.listFiles()) {
				FileInfo info = new FileInfo();
				genFileInfo(info, child,parentPath);
				fileInfo.chaildFiles.add(info);
			}
		}
	}
}
