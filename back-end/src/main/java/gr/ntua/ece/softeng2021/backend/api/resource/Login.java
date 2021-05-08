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

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.Optional;

public class Login extends BasicResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    protected JsonMapRepresentation post (Representation entity) throws ResourceException {
        //Create a new restlet form
        Form form = new Form(entity);
        String username = form.getFirstValue("username");
        String password = form.getFirstValue("password");
        if( username == null || password == null ) throw new ResourceException(400,"Bad request");
        Optional<String> login =dataAccess.loginuser(username,password);
        System.out.println(login);
        if(login == null){
            throw new ResourceException(401,"Not Authorized");
        }else{
            return new JsonMapRepresentation(Map.of("token",login.get()));
        }

    }
}