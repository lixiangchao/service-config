package com.cs.config.api;

import java.io.File;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cs.config.utils.PathLoader;
import javax.ws.rs.PathParam;

@Path("getFile")
public class GetFile {
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFileByGet(@QueryParam("version") String version, @QueryParam("filePath") String filePath) {

		File file = new File(PathLoader.getPath(version, filePath));
		if (!file.exists()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(file).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFileByPost(@QueryParam("version") String version, @FormParam("filePath") String filePath) {
		File file = new File(PathLoader.getPath(version, filePath));
		if (!file.exists()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(file).build();
	}
}
