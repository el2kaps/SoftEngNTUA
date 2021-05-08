package gr.ntua.ece.softeng2021.backend.api.resource;

import gr.ntua.ece.softeng2021.backend.api.Format;
import gr.ntua.ece.softeng2021.backend.conf.Configuration;
import gr.ntua.ece.softeng2021.backend.data.DataAccess;
import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerEV;
import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerProvider;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.util.List;

public class SessionsPerProvider extends BasicResource{
    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {
        /*Authorization*/
        //Form form = new Form(entity);
        //String token = form.getFirstValue("token");
        //Read the mandatory attributes
        String providerID = getMandatoryAttribute("providerID", "ProviderID is missing");
        String  date_from = getMandatoryAttribute("date_from", "Date_from is missing");
        String  date_to = getMandatoryAttribute("date_to", "Date_to is missing");
        //System.out.println(ProviderID);
        //System.out.println(date_from);
        //System.out.println(date_to);
        //Read the format query parameter
        Format format = parseFormat(getQueryValue("format"));

        try {

            List<RecordSessionsPerProvider> result = dataAccess.fetchSessionsPerProvider(
                    providerID,
                    date_from,
                    date_to
            );
            return format.generateRepresentation4(result);
        } catch (Exception e) {
            System.out.println("ERORRRRRRR");
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }

}
