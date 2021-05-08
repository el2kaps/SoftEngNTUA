package gr.ntua.ece.softeng2021.backend.api.resource;

import gr.ntua.ece.softeng2021.backend.api.representation.JsonUserRepresentation;
import gr.ntua.ece.softeng2021.backend.api.representation.JsonMapRepresentation;
import gr.ntua.ece.softeng2021.backend.data.DataAccess;
import gr.ntua.ece.softeng2021.backend.conf.Configuration;
import gr.ntua.ece.softeng2021.backend.data.User;
import gr.ntua.ece.softeng2021.backend.api.Format;
import org.restlet.data.Status;
import org.restlet.data.Form;
import org.restlet.resource.ResourceException;
import gr.ntua.ece.softeng2021.backend.data.DataAccessException;
import org.restlet.representation.Representation;
import org.restlet.util.Series;

import java.util.*;
public class AddChangeUser extends BasicResource{
    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    protected JsonMapRepresentation post (Representation entity) throws ResourceException{
        System.out.println("AddChangeUser begins!!!!");
        Series headers =(Series)getRequestAttributes().get("org.restlet.http.headers");
        String token = headers.getFirstValue("X-OBSERVATORY-AUTH");

        if( token==null ) throw new ResourceException(401,"Not authorized");
        boolean admintoken=dataAccess.TestAdminToken(token);

        if(admintoken == false ) throw new ResourceException(401,"Not authorized");
        
        Form form = new Form(entity);
        String username = form.getFirstValue("username");
        String password = form.getFirstValue("password");
        
        if(username == null || password == null) throw new ResourceException(400,"Bad request");

        try {
            dataAccess.AddUser(username, password);
            return new JsonMapRepresentation(Map.of("status", "OK"));
        }
        catch(DataAccessException e) {
            //throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "Data access exception: " +e);
            return new JsonMapRepresentation(Map.of("status", "failed"));
        }
    }
}
