/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.services.configure.api;

import com.cs.services.configure.RepositoryMgr;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author lucif
 */
@Path("/resources")
public class Resources {

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response get(@QueryParam("branch") String branch, @QueryParam("path") String path) {
        File file = new File(RepositoryMgr.getBranchFolder(branch), path);
        if (!file.exists()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(file).build();
    }

    @GET
    @Path("/listFiles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map> listFiles(@QueryParam("branch") String branch, @QueryParam("path") String path) {
        List<Map> list = new LinkedList<>();
        File directory = new File(RepositoryMgr.getBranchFolder(branch), path);
        if (!directory.exists() || !directory.isDirectory()) {
            return list;
        }
        for (File f : directory.listFiles()) {
            Map map = new LinkedHashMap();
            map.put("name", f.getName());
            map.put("isDirectory", f.isDirectory());
            map.put("lastModified", f.lastModified());
            list.add(map);
        }
        return list;
    }
}
