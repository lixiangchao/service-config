package com.cs.services.configure;

import com.cs.commons.errorhandling.ErrorHandling;
import com.cs.commons.json.jaxrs.interceptor.JsonProvider;
import com.cs.services.configure.api.Resources;
import com.cs.services.configure.log.LogMgr;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

@ApplicationPath("/api")
public class RestApplication extends Application {

    public RestApplication(@Context ServletContext context) {
        ErrorHandling.init(LogMgr.getSystem());
        RepositoryMgr.initConfigure(context);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<>();
        //Filter

        //Interceptor
        set.add(JsonProvider.class);

        //api
        set.add(Resources.class);
        return set;
    }

}
