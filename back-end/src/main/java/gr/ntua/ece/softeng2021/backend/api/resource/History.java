package gr.ntua.ece.softeng2021.backend.api.resource;

import gr.ntua.ece.softeng2021.backend.api.Format;
import gr.ntua.ece.softeng2021.backend.conf.Configuration;
import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerEV;

import gr.ntua.ece.softeng2021.backend.data.DataAccess;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import gr.ntua.ece.softeng2021.backend.data.model.RecordHistory;

import java.util.List;

/**
 *
 */
public class History extends BasicResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {
        /*Authorization*/
        //Form form = new Form(entity);
        //String token = form.getFirstValue("token");


        //Read the mandatory attributes
        String username = getMandatoryAttribute("username", "Username is missing");
        String evid = getMandatoryAttribute("evid", "evid is missing");
        //Read the format query parameter
        Format format = parseFormat(getQueryValue("format"));

        try {

            List<RecordHistory> result = dataAccess.EVHistory(
                    username,
                    evid
            );
            return format.generateRepresentation9(result);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }
}