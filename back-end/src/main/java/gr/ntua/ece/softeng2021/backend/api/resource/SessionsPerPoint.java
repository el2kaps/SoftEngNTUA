package gr.ntua.ece.softeng2021.backend.api.resource;

import gr.ntua.ece.softeng2021.backend.api.Format;
import gr.ntua.ece.softeng2021.backend.conf.Configuration;
import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerPoint;

import gr.ntua.ece.softeng2021.backend.data.DataAccess;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.util.List;

/**
 *
 */
public class SessionsPerPoint extends BasicResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {
        /*Authorization*/

        //Read the mandatory attributes
        String pointID = getMandatoryAttribute("pointID", "pointID is missing");
        String  date_from = getMandatoryAttribute("date_from", "Date_from is missing");
        String  date_to = getMandatoryAttribute("date_to", "Date_to is missing");

        //Read the format query parameter
        Format format = parseFormat(getQueryValue("format"));

        try {

            List<RecordSessionsPerPoint> result = dataAccess.fetchSessionsPerPoint(
                    pointID,
                    date_from,
                    date_to
            );
            return format.generateRepresentation1(result);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }


}