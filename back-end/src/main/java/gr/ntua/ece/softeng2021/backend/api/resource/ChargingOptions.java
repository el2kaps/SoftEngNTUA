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
import gr.ntua.ece.softeng2021.backend.api.Format;
import gr.ntua.ece.softeng2021.backend.data.model.ChargingOptionsList;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.Optional;
import java.util.List;

public class ChargingOptions extends BasicResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {
        /*Authorization*/
        //Form form = new Form(entity);
        //String token = form.getFirstValue("token");


        //Read the mandatory attributes
        String username = getMandatoryAttribute("username", "Username is required!");
        //String brandName = getMandatoryAttribute("brandName", "Provider's brand name is missing");

        //Read the format query parameter
        Format format = parseFormat(getQueryValue("format"));

        try {

            List<ChargingOptionsList> result = dataAccess.fetchChargingOptions(username);
            return format.generateRepresentation5(result);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }
}