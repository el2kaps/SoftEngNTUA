package gr.ntua.ece.softeng2021.backend.api.resource;

import gr.ntua.ece.softeng2021.backend.api.representation.JsonUserRepresentation;
import gr.ntua.ece.softeng2021.backend.api.representation.JsonMapRepresentation;
import gr.ntua.ece.softeng2021.backend.conf.Configuration;
import gr.ntua.ece.softeng2021.backend.data.DataAccess;
import gr.ntua.ece.softeng2021.backend.data.User;
import gr.ntua.ece.softeng2021.backend.data.DataAccessException;
import org.restlet.ext.json.JsonRepresentation;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.resource.Post;
import org.restlet.util.Series;
import org.restlet.data.Header;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.restlet.data.MediaType;
import org.restlet.ext.fileupload.RestletFileUpload;
import org.restlet.representation.StringRepresentation;
//import org.restlet.data.Header;

//import java.util.Map;
//import java.util.HashMap;
//import java.util.Optional;
//import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class SessionsUpdate extends BasicResource{

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation post (Representation entity) throws ResourceException {
        //Create a new restlet form
        Series headers =(Series) getRequestAttributes().get("org.restlet.http.headers");
        String token = headers.getFirstValue("X-OBSERVATORY-AUTH");

        if( token==null ) throw new ResourceException(401,"Not authorized");
        boolean admintoken=dataAccess.TestAdminToken(token);
        if(admintoken == false ) throw new ResourceException(401,"Not authorized");

        try{
          Representation result=null;
          if(entity != null ){
            if (MediaType.MULTIPART_FORM_DATA.equals(entity.getMediaType(), true)) {
              // 1/ Create a factory for disk-based file items
              DiskFileItemFactory factory = new DiskFileItemFactory();
              factory.setSizeThreshold(1000240);

              // 2/ Create a new file upload handler based on the Restlet
              // FileUpload extension that will parse Restlet requests and
              // generates FileItems.
              RestletFileUpload upload = new RestletFileUpload(factory);

              // 3/ Request is parsed by the handler which generates a
              // list of FileItems
              FileItemIterator fileIterator = upload.getItemIterator(entity);

              // Process only the uploaded item called "fileToUpload"
              // and return back
              boolean found = false;
              while (fileIterator.hasNext() && !found) {
                  FileItemStream fi = fileIterator.next();
                  System.out.println(fi.getFieldName());
                  if (fi.getFieldName().equals("file")) {
                      found = true;
                      // consume the stream immediately, otherwise the stream
                      // will be closed.
                      StringBuilder sb = new StringBuilder();
                      BufferedReader br = new BufferedReader(
                              new InputStreamReader(fi.openStream()));
                      BufferedWriter writer = new BufferedWriter(new FileWriter("tmp.csv"));
                      String line = null;
                      int cnt=0;
                      while ((line = br.readLine()) != null) {
                          cnt=cnt+1;
                          String[] newline=line.split(" \\+");
                          //System.out.println(Arrays.toString(newline));
                          line = String.join("+",newline);
                          writer.write(line);
                          writer.write("\r");
                          writer.newLine();
                          writer.flush();
                          sb.append(line);
                          sb.append("\r\n");
                      }
                      System.out.println(cnt);
                      sb.append("\r\n");
                      String tmp = sb.toString();
                      result = new StringRepresentation(sb.toString(), MediaType.TEXT_PLAIN);
                      //BufferedWriter writer = new BufferedWriter(new FileWriter("tmp.csv"));
                      //writer.write(tmp);
                      List<Integer> res = dataAccess.ImportSessions();
                      System.out.println(res.toString());
                      Map<String,Integer> hm = new HashMap<String,Integer>();
                      hm.put("SessionsInUploadedFile",cnt-1);
                      hm.put("SessionsImported,",res.get(0));
                      hm.put("TotalSessionsInDatabase.",res.get(1));
                      return new JsonMapRepresentation(hm);
                    }
                  }
            }else{
              throw new ResourceException(400,"Bad Request");
            }
          }
          return new JsonMapRepresentation(Map.of());
        }catch(Exception e){
          throw new ResourceException(400,"Bad Request");
        }

    }
}
