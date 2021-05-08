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
import gr.ntua.ece.softeng2021.backend.data.model.Bill;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.Optional;
import java.util.List;

public class ProviderBilling extends BasicResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {
        /*Authorization*/
        //Form form = new Form(entity);
        //String token = form.getFirstValue("token");

        System.out.println("Provider Billig!!!!!");

        //Read the mandatory attributes
        String username = getMandatoryAttribute("username", "Username is required!");
        //Integer monthbill = getMandatoryAttribute("monthbill", "Select current month");

        //Read the format query parameter
        Format format = parseFormat(getQueryValue("format"));

        try {

            List<Bill> result = dataAccess.ProviderBilling(username);
            //System.out.println(result);
            return format.generateRepresentation6(result);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }
}