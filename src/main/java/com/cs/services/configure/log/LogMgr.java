/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs.services.configure.log;

import com.cs.commons.logging.Logger;

/**
 *
 * @author lucif
 */
public class LogMgr {

    private final static Logger SYSTEM = Logger.getLogger("SYSTEM");

    private LogMgr() {
    }

    /**
     * @return the sysLog
     */
    public static Logger getSystem() {
        return SYSTEM;
    }
}
