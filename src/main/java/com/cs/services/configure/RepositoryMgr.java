/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.services.configure;

import com.cs.commons.lang.StringUtils;
import com.cs.commons.lang.doc.XMLUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author lucif
 */
public class RepositoryMgr {

    private static Map<String, File> map;

    public static void initConfigure() {
        try {
            String path = System.getProperty("user.conf");
            File br;
            if (Objects.isNull(path)) {
                br = new File("/conf", "repository.xml");
            } else {
                br = new File(path, "repository.xml");
            }
            Element root = XMLUtils.parser(br).getDocumentElement();
            map = new HashMap<>();
            String repPath = XMLUtils.findTrimValue(root, "localRepository", false);
            if (StringUtils.isEmpty(repPath)) {
                repPath = "repository";
            }
            for (Element e : XMLUtils.findChildrenElements(XMLUtils.findElement(root, "branchs", false), "branch")) {
                String name = e.getAttribute("name");
                map.put(name, new File(repPath, name));
            }
        } catch (IOException | ParserConfigurationException | SAXException ex) {

        }
    }

    public static File getBranchFolder(String branch) {
        return map.get(branch);
    }

}
