/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.services.configure;

import com.cs.commons.errorhandling.ErrorHandling;
import com.cs.commons.lang.StringUtils;
import com.cs.commons.lang.doc.XMLUtils;
import com.cs.commons.model.errorhandling.EHException;
import com.cs.services.configure.log.LogMgr;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;
import org.os.commons.errorhandling.resources.EHSystem;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author lucif
 */
public class RepositoryMgr {

    private static File repFolder;
    private static File commonsFolder;
    private static Map<String, File> map;

    public static void initConfigure(ServletContext context) {
        String path = System.getProperty("user.conf");
        File setFile;
        if (Objects.isNull(path)) {
            path = context.getRealPath("conf");
        }
        setFile = new File(path, "setting.xml");
        try {
            Element root = XMLUtils.parser(setFile).getDocumentElement();
            map = new HashMap<>();
            String repPath = XMLUtils.findTrimValue(root, "localRepository", false);
            if (StringUtils.isBlank(repPath)) {
                repFolder = new File(path, "repository");
            } else {
                repFolder = new File(repPath);
            }
            commonsFolder = new File(repFolder, "commons");
            for (Element e : XMLUtils.findChildrenElements(XMLUtils.findElement(root, "branchs", false), "branch")) {
                String name = e.getAttribute("name");
                map.put(name, new File(repPath, name));
            }
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            LogMgr.getSystem().write(new EHException(ex, ErrorHandling.getErrorInfo(EHSystem.NAME, EHSystem.CODE_ST000003, setFile.getPath())));
        }
    }

    public static File getRepositoryFolder() {
        return repFolder;
    }

    public static File getBranchFolder(String branch) {
        return map.get(branch);
    }

    public static File getCommonsFolder() {
        return commonsFolder;
    }

}
