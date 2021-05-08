package gr.ntua.ece.softeng2021.backend.api.resource;

import gr.ntua.ece.softeng2021.backend.api.representation.JsonMapRepresentation;
import gr.ntua.ece.softeng2021.backend.conf.Configuration;
import gr.ntua.ece.softeng2021.backend.data.DataAccess;
import gr.ntua.ece.softeng2021.backend.data.DataAccessException;
import org.restlet.data.Status;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.resource.Post;

import java.util.Map;

public class ResetSessions extends BasicResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    protected JsonMapRepresentation post(Representation entity) throws ResourceException {

        try {
            dataAccess.reset();
            return new JsonMapRepresentation(Map.of("status", "OK"));
        }
        catch(DataAccessException e) {
            //throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "Data access exception: " +
            return new JsonMapRepresentation(Map.of("status", "failed"));
        }


    }
}
