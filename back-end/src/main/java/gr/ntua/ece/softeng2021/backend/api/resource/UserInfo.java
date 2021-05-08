package gr.ntua.ece.softeng2021.backend.api.resource;

import gr.ntua.ece.softeng2021.backend.api.representation.JsonUserRepresentation;
import gr.ntua.ece.softeng2021.backend.data.DataAccess;
import gr.ntua.ece.softeng2021.backend.conf.Configuration;
import gr.ntua.ece.softeng2021.backend.data.User;
import gr.ntua.ece.softeng2021.backend.api.Format;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.restlet.representation.Representation;
import org.restlet.util.Series;

import java.util.*;
public class UserInfo extends BasicResource{
    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get () throws ResourceException {
        Series headers =(Series)getRequestAttributes().get("org.restlet.http.headers");
        String token = headers.getFirstValue("X-OBSERVATORY-AUTH");

        if( token==null ) throw new ResourceException(401,"Not authorized");
        boolean admintoken=dataAccess.TestAdminToken(token);

        if(admintoken == false ) throw new ResourceException(401,"Not authorized");

        String username = getMandatoryAttribute("username","username missing");

        Format format = parseFormat(getQueryValue("format"));

        try {
            List<User> result = dataAccess.ReadUser(username);
            //System.out.println(result);
            return format.generateRepresentation10(result);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }
    }
}
