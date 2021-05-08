package gr.ntua.ece.softeng2021.backend.api.representation;

import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerEV;
import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerPoint;
import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerProvider;
import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerStation;
import gr.ntua.ece.softeng2021.backend.data.model.ChargingOptionsList;
import gr.ntua.ece.softeng2021.backend.data.model.RecordHistory;
import gr.ntua.ece.softeng2021.backend.data.model.Bill;
import gr.ntua.ece.softeng2021.backend.data.model.EVInfo;
import gr.ntua.ece.softeng2021.backend.data.User;
import gr.ntua.ece.softeng2021.backend.data.model.RecordDuration;
import org.restlet.representation.Representation;

import java.util.List;

public interface RepresentationGenerator {

    public Representation generateRepresentation1(List<RecordSessionsPerPoint> result);
    public Representation generateRepresentation2(List<RecordSessionsPerStation> result);
    public Representation generateRepresentation3(List<RecordSessionsPerEV> result);
    public Representation generateRepresentation4(List<RecordSessionsPerProvider> result);
    public Representation generateRepresentation5(List<ChargingOptionsList> result);
    public Representation generateRepresentation6(List<Bill> result);
    public Representation generateRepresentation7(List<RecordDuration> result);
    public Representation generateRepresentation8(List<EVInfo> result);
    public Representation generateRepresentation9(List<RecordHistory> result);
    public Representation generateRepresentation10(List<User> result);
}
