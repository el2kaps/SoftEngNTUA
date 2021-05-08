package gr.ntua.ece.softeng2021.client;

import java.io.Reader;
import java.util.List;

import gr.ntua.ece.softeng2021.data.model.SessionsPerEV;
import gr.ntua.ece.softeng2021.data.model.SessionsPerPoint;
import gr.ntua.ece.softeng2021.data.model.SessionsPerProvider;
import gr.ntua.ece.softeng2021.data.model.SessionsPerStation;

public interface ResponseBodyProcessor {
    List<SessionsPerEV> consumeSessionsPerEV(Reader reader);

    List<SessionsPerPoint> consumeSessionsPerPoint(Reader reader);

    List<SessionsPerProvider> consumeSessionsPerProvider(Reader reader);

    List<SessionsPerStation> consumeSessionsPerStation(Reader reader);
}
