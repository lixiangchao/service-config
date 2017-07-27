package com.cs.services.configure;

import com.cs.commons.json.jaxrs.interceptor.JsonProvider;
import com.cs.services.configure.api.Resources;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {

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
