package gr.ntua.ece.softeng2021.backend.api.resource;

import gr.ntua.ece.softeng2021.backend.api.representation.JsonMapRepresentation;
import gr.ntua.ece.softeng2021.backend.conf.Configuration;
import gr.ntua.ece.softeng2021.backend.data.DataAccess;
import org.restlet.ext.json.JsonRepresentation;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.resource.Post;
import org.restlet.util.Series;
import org.restlet.data.Header;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class Logout extends BasicResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    protected JsonMapRepresentation post (Representation entity) throws ResourceException{
        //Create a new restlet form
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Series headers =(Series) getRequestAttributes().get("org.restlet.http.headers");
        String token = headers.getFirstValue("X-OBSERVATORY-AUTH");
        //Form form = new Form(entity);
        //String token = form.getFirstValue("token");
        if( token==null ) System.out.println("System is NUUUUUUUUUUUUUUll");
        if( token==null ) throw new ResourceException(401,"Not authorized");
        Optional<String> logout=dataAccess.logoutuser(token);
        if( logout==null ) throw new ResourceException(401,"Not authorized");
        return new JsonMapRepresentation(Collections.emptyMap());

    }
}
