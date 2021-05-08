package gr.ntua.ece.softeng2021.backend.api.resource;

import gr.ntua.ece.softeng2021.backend.api.representation.JsonMapRepresentation;
import gr.ntua.ece.softeng2021.backend.conf.Configuration;
import gr.ntua.ece.softeng2021.backend.data.DataAccess;
import gr.ntua.ece.softeng2021.backend.data.DataAccessException;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class HealthCheck extends BasicResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    protected JsonMapRepresentation get() throws ResourceException {

        try {
            dataAccess.accessDataCheck();
            return new JsonMapRepresentation(Map.of("status", "OK"));
        }
        catch(DataAccessException e) {
            //throw new NoSuchAlgorithmException(Status.SERVER_ERROR_INTERNAL, "Data access exception: " + e.getMessage(), e);
            return new JsonMapRepresentation(Map.of("status", "failed"));
        }


    }
}