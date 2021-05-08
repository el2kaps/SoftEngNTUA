package gr.ntua.ece.softeng2021.backend.api;

import gr.ntua.ece.softeng2021.backend.api.resource.*;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import org.restlet.routing.Template;
import org.restlet.service.CorsService;
import org.restlet.engine.application.CorsFilter;
import org.restlet.data.Method;
import java.util.*;
import static java.security.AccessController.getContext;
//import org.springframework.boot.web.servlet.server.Session;
public class RestfulApp extends Application {

    @Override
    public synchronized Restlet createInboundRoot() {

        Router router = new Router(getContext());

        //Login and Logout
        router.attach("/login",Login.class);
        router.attach("/logout",Logout.class);

        //Helper endpoints
        router.attach("/admin/resetsessions",ResetSessions.class);
        router.attach("/admin/healthcheck",HealthCheck.class);


        //Administrative endpoints
        router.attach("/admin/usermod/{username}/{password}",AddChangeUser.class);
        router.attach("/admin/users/{username}",UserInfo.class);
        router.attach("/admin/system/sessionsupd",SessionsUpdate.class);


        //Functional endpoints
        router.attach("/SessionsPerPoint/{pointID}/{date_from}/{date_to}", SessionsPerPoint.class);
        router.attach("/SessionsPerStation/{stationID}/{date_from}/{date_to}", SessionsPerStation.class);
        router.attach("/SessionsPerEV/{vehicleID}/{date_from}/{date_to}", SessionsPerEV.class);
        router.attach("/SessionsPerProvider/{providerID}/{date_from}/{date_to}", SessionsPerProvider.class);

        //Use cases
        router.attach("/options/{username}", ChargingOptions.class);
        router.attach("/userbilling/{username}", UserBilling.class);
        router.attach("/duration/{username}/{session}", Duration.class);
        router.attach("/evinfo/{username}/{evid}", evinfo.class);
        router.attach("/chhistory/{username}/{evid}",History.class);
        router.attach("/providerbilling/{username}",ProviderBilling.class);

       //return router

       CorsFilter corsFilter = new CorsFilter(getContext(), router);
       corsFilter.setAllowedOrigins(Set.of("*"));
       corsFilter.setAllowedCredentials(true);
       corsFilter.setDefaultAllowedMethods(Set.of(Method.GET, Method.PUT, Method.POST, Method.DELETE));
       corsFilter.setAllowingAllRequestedHeaders(true);
       corsFilter.setSkippingResourceForCorsOptions(true);
       corsFilter.setMaxAge(10);

   return corsFilter;

    }

}
