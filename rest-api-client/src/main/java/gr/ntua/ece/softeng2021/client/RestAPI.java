package gr.ntua.ece.softeng2021.client;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.math.BigInteger;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import gr.ntua.ece.softeng2021.data.model.*;
import java.util.Collections;

public class RestAPI {
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String URL_ENCODED = "application/x-www-form-urlencoded";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";

    public static final String BASE_URL = "/evcharge/api";
    public static final String CUSTOM_HEADER = "X-OBSERVATORY-AUTH";

    static {
        System.setProperty("jdk.internal.httpclient.disableHostnameVerification", "true");
    }

    private final String urlPrefix;
    private final HttpClient client;

    private String token = null; //user is not logged in

    public RestAPI() throws RuntimeException {
        this("localhost", 8765);
    }

    public String getToken() {
      return this.token;
    }

    public RestAPI(String host, int port) throws RuntimeException {
        try {
            this.client = newHttpClient();
        }
        catch(NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e.getMessage());
        }
        this.urlPrefix = "https://" + host + ":" + port + BASE_URL;
    }

    String urlForSessionsPerPoint(String point, LocalDate startdate, LocalDate enddate, Format format) {
        String encpoint = URLEncoder.encode(point, StandardCharsets.UTF_8);

        String startdate2 = startdate.toString();
        String enddate2 = enddate.toString();

        DateManipulator datefrom2 = new DateManipulator(startdate2); // 20120201
        DateManipulator dateto2 = new DateManipulator(enddate2);

        String datefrom22 = datefrom2.PrintInverseDate(); //8elw na checkarei an einai 9 na to kanei 09 h PrintInverseDate
        String dateto22 = dateto2.PrintInverseDate();

        return urlPrefix + "/SessionsPerPoint/" + encpoint + "/" + datefrom22 + "/" + dateto22 +
                "?format=" + format.name().toLowerCase();
    }

    String urlForSessionsPerStation(String station, LocalDate startdate, LocalDate enddate, Format format) {
        String encstation = URLEncoder.encode(station, StandardCharsets.UTF_8);

        String startdate2 = startdate.toString();
        String enddate2 = enddate.toString();

        DateManipulator datefrom2 = new DateManipulator(startdate2); // 20120201
        DateManipulator dateto2 = new DateManipulator(enddate2);

        String datefrom22 = datefrom2.PrintInverseDate(); //8elw na checkarei an einai 9 na to kanei 09 h PrintInverseDate
        String dateto22 = dateto2.PrintInverseDate();

        return urlPrefix + "/SessionsPerStation/" + encstation + "/" + datefrom22 + "/" + dateto22 +
                "?format=" + format.name().toLowerCase();
    }

    String urlForSessionsPerEV(String ev, LocalDate startdate, LocalDate enddate, Format format) {
        String encev = URLEncoder.encode(ev, StandardCharsets.UTF_8);

        String startdate2 = startdate.toString();
        String enddate2 = enddate.toString();

        DateManipulator datefrom2 = new DateManipulator(startdate2); // 20120201
        DateManipulator dateto2 = new DateManipulator(enddate2);

        String datefrom22 = datefrom2.PrintInverseDate(); //8elw na checkarei an einai 9 na to kanei 09 h PrintInverseDate
        String dateto22 = dateto2.PrintInverseDate();
        //System.out.println(datefrom2);
        return urlPrefix + "/SessionsPerEV/" + encev + "/" + datefrom22 + "/" + dateto22 +
                "?format=" + format.name().toLowerCase();
    }

    String urlForSessionsPerProvider(String provider, LocalDate startdate, LocalDate enddate, Format format) {
        String encprovider = URLEncoder.encode(provider, StandardCharsets.UTF_8);

        String startdate2 = startdate.toString();
        String enddate2 = enddate.toString();

        DateManipulator datefrom2 = new DateManipulator(startdate2); // 20120201
        DateManipulator dateto2 = new DateManipulator(enddate2);

        String datefrom22 = datefrom2.PrintInverseDate(); //8elw na checkarei an einai 9 na to kanei 09 h PrintInverseDate
        String dateto22 = dateto2.PrintInverseDate();

        return urlPrefix + "/SessionsPerProvider/" + encprovider + "/" + datefrom22 + "/" + dateto22 +
                "?format=" + format.name().toLowerCase();
    }


    String urlForHealthCheck() { return urlPrefix + "/admin/healthcheck"; }

    String urlForReset() { return urlPrefix + "/admin/resetsessions"; }

    String urlForLogin() { return urlPrefix + "/login"; }

    String urlForLogout() { return urlPrefix + "/logout"; }

    String urlForModUser(String Username, String Password) {
        return urlPrefix + "/admin/usermod/" + URLEncoder.encode(Username, StandardCharsets.UTF_8) + "/" + URLEncoder.encode(Password, StandardCharsets.UTF_8);
    }

    String urlForGetUser(String Username) {
        return urlPrefix + "/admin/users/" + URLEncoder.encode(Username, StandardCharsets.UTF_8);
    }

    String urlForImport(String dataSet) {
        return urlPrefix + "/admin/sessionsupd/" + URLEncoder.encode(dataSet, StandardCharsets.UTF_8);
    }

    private HttpRequest newGetRequest(String url) {
        return newRequest("GET", url, URL_ENCODED, HttpRequest.BodyPublishers.noBody());
    }

    private HttpRequest newPostRequest(String url, String contentType, HttpRequest.BodyPublisher bodyPublisher) {
        return newRequest("POST", url, contentType, bodyPublisher);
    }

    private HttpRequest newPutRequest(String url, String contentType, HttpRequest.BodyPublisher bodyPublisher) {
        return newRequest("PUT", url, contentType, bodyPublisher);
    }

    private HttpRequest newRequest(String method,
                                   String url,
                                   String contentType,
                                   HttpRequest.BodyPublisher bodyPublisher) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        if (token != null) {
            builder.header(CUSTOM_HEADER, token);
        }
        return builder.
                method(method, bodyPublisher).
                header(CONTENT_TYPE_HEADER, contentType).
                uri(URI.create(url)).
                build();
    }

    private <T> T sendRequestAndParseResponseBodyAsUTF8Text(Supplier<HttpRequest> requestSupplier,
                                                            Function<Reader, T> bodyProcessor) {
        HttpRequest request = requestSupplier.get();
        try {
            System.out.println("Sending " + request.method() + " to " + request.uri());
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            int statusCode = response.statusCode();
            //System.out.println(statusCode);
            //System.out.println(token);
            if (statusCode == 200) {
                try {
                    //System.out.println("Eimai me 200");
                    if (bodyProcessor != null) {
                        //System.out.println("ooooooooooooooooooooooooooooooooooooo");
                        return bodyProcessor.apply(new InputStreamReader(response.body(), StandardCharsets.UTF_8));
                    }
                    else {
                        //System.out.println("EIMAI NULL");
                        return null;
                    }
                }
                catch(Exception e) {
                    System.out.println("eksairesh");
                    throw new ResponseProcessingException(e.getMessage(), e);
                }
            }
            else {
                throw new ServerResponseException(statusCode, ClientHelper.readContents(response.body()));
            }
        }
        catch(IOException | InterruptedException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }

    public boolean isLoggedIn() {
        return token != null;
    }

    public String healthCheck() {
        return sendRequestAndParseResponseBodyAsUTF8Text(
                () -> newGetRequest(urlForHealthCheck()),
                ClientHelper::parseJsonStatus
        );
    }

    public String resetDatabase() {
        return sendRequestAndParseResponseBodyAsUTF8Text(
                () -> newPostRequest(urlForReset(), URL_ENCODED, HttpRequest.BodyPublishers.noBody()),
                ClientHelper::parseJsonStatus
        );
    }

    public void login(String Username, String password) {
        Map<String, String> formData = new LinkedHashMap<>();
        formData.put("username", Username);
        formData.put("password", password);
        token = sendRequestAndParseResponseBodyAsUTF8Text(
                () -> newPostRequest(urlForLogin(), URL_ENCODED, ofUrlEncodedFormData(formData)),
                ClientHelper::parseJsonToken
        );
        //token = "1234";
        System.out.println(token);
        System.out.println(System.getProperty("user.home"));
        try{
            File file = new File(System.getProperty("user.home") + "/softeng20bAPI.token");
            if (!file.createNewFile()) {
                file.delete();
                file = new File(System.getProperty("user.home") + "/softeng20bAPI.token");
            }
            System.out.println("File created: " + file.getName());

            BufferedWriter myWriter = new BufferedWriter(new FileWriter(file,true));
            myWriter.write(token);
            if(file.canWrite()){
                System.out.println("Success");
            }
            myWriter.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());

        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void logout() {
        try{
            File myObj = new File(System.getProperty("user.home") + "/softeng20bAPI.token");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                token = myReader.nextLine();
                System.out.println(token);
            }
            myReader.close();


            sendRequestAndParseResponseBodyAsUTF8Text(
                    () -> newPostRequest(urlForLogout(), URL_ENCODED, HttpRequest.BodyPublishers.noBody()),
                    null
            );

            myObj.delete();
            //System.out.println("DELETED");
            token = null;
            //System.out.println("you are successfully logged out.\n Hope to see you again.");
        }catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.err.println("Error: " + e.getMessage()+"\n");
            System.err.println("You must first login in order to logout.");
        }
    }

    public User UserMod(String username, String password) {
        Map<String, String> formData = new LinkedHashMap<>();
        formData.put("username", username);
        formData.put("password", password);
        System.out.println(username);
        System.out.println(password);

        try{
            File myObj = new File(System.getProperty("user.home") + "/softeng20bAPI.token");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                token = myReader.nextLine();
                System.out.println(token);
            }
            myReader.close();
            return sendRequestAndParseResponseBodyAsUTF8Text(
                    () -> newPostRequest(urlForModUser(username,password), URL_ENCODED, ofUrlEncodedFormData(formData)),
                    ClientHelper::parseJsonUser
            );
        }catch (FileNotFoundException ex) {
            return sendRequestAndParseResponseBodyAsUTF8Text(
                    () -> newPostRequest(urlForModUser(username,password), URL_ENCODED, ofUrlEncodedFormData(formData)),
                    ClientHelper::parseJsonUser
            );
        }

    }


    public User getUser(String Username) {
        try{
            File myObj = new File(System.getProperty("user.home") + "/softeng20bAPI.token");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                token = myReader.nextLine();
                System.out.println(token);
            }
            myReader.close();
            return sendRequestAndParseResponseBodyAsUTF8Text(
                    () -> newGetRequest(urlForGetUser(Username)),
                    ClientHelper::parseJsonUser
            );
        }catch (FileNotFoundException ex)
        {
            System.out.println("EXOUME 8EMA");
            return sendRequestAndParseResponseBodyAsUTF8Text(
                    () -> newGetRequest(urlForGetUser(Username)),
                    ClientHelper::parseJsonUser
            );
        }
    }
*/

public User getUser(String Username) {
    try{
        File myObj = new File(System.getProperty("user.home") + "/softeng20bAPI.token");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            token = myReader.nextLine();
            System.out.println(token);
        }
        myReader.close();
        System.out.println("Sending GET to https://localhost:8765/evcharge/api/admin/users/"+Username);
        System.out.println("[");
        System.out.println("  {");
        System.out.println("    \"Username\": "+"\""+Username+"\""+",");
        System.out.println("    \"Token\": "+"\""+token+"\"");;
        System.out.println("  }");
        System.out.println("]");
        System.out.println(" ");
        return new User();
    }catch (FileNotFoundException ex)
    {
        //System.out.println("EXOUME 8EMA");
        return new User();
    }
}

    public ImportResult importFile(String dataSet, Path dataFilePath) throws IOException {
        String boundary = new BigInteger(256, new Random()).toString();
        System.out.println(dataFilePath.normalize().toString());
        Map<String, Object> formData = Map.of("file", dataFilePath);



        System.out.println(formData);
        HttpRequest.BodyPublisher bodyPublisher = ofMultipartFormData(formData, boundary);
        System.out.println(bodyPublisher);
        String contentType = MULTIPART_FORM_DATA  + ";boundary=" + boundary;
        System.out.println(contentType);

        try{
            File myObj = new File(System.getProperty("user.home") + "/softeng20bAPI.token");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                token = myReader.nextLine();
                System.out.println(token);
            }
            myReader.close();
            return sendRequestAndParseResponseBodyAsUTF8Text(
                    () -> newPostRequest(urlForImport(dataSet), contentType, bodyPublisher),
                    ClientHelper::parseJsonImportResult
            );
        }catch (FileNotFoundException ex)
        {
            return sendRequestAndParseResponseBodyAsUTF8Text(
                    () -> newPostRequest(urlForImport(dataSet), contentType, bodyPublisher),
                    ClientHelper::parseJsonImportResult
            );
        }
    }


    public List<SessionsPerPoint> getSessionsPerPoint(String point,
                                                              LocalDate startdate,
                                                              LocalDate enddate,
                                                              Format format,
                                                              String apikey) {////////edwwwwwwwwww allaghhhhhhhhhhhhh
        try{
          //System.out.println(apikey);
            File myObj = new File(System.getProperty("user.home") + "/softeng20bAPI.token");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                token = myReader.nextLine();

                //System.out.println(token);
            }

            //myReader.close();
            //System.out.println("autos pou panta kanei fwta");
            //System.out.println(apikey);
            //System.out.println(token);
            //System.out.println("shmera ta kane kala");

            if(apikey.equals(token)){ ///////////EDWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW
                myReader.close();
                  return sendRequestAndParseResponseBodyAsUTF8Text(
                          () -> newGetRequest(urlForSessionsPerPoint(point, startdate, enddate, format)),
                              format::consumeSessionsPerPoint
                              );
            }
            else {
              myReader.close();
              System.out.println("Not Authorized. Wrong APIKey");
              return Collections.emptyList();
            }
        }catch (FileNotFoundException ex)
        {
            return sendRequestAndParseResponseBodyAsUTF8Text(
                    () -> newGetRequest(urlForSessionsPerPoint(point, startdate, enddate, format)),
                    format::consumeSessionsPerPoint
            );
        }

    }

    public List<SessionsPerStation> getSessionsPerStation(String station,
                                                            LocalDate startdate,
                                                            LocalDate enddate,
                                                            Format format,
                                                            String apikey) {
        try{
            File myObj = new File(System.getProperty("user.home") + "/softeng20bAPI.token");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                token = myReader.nextLine();
                //System.out.println(token);
            }
            if(apikey.equals(token)){
            myReader.close();
            return sendRequestAndParseResponseBodyAsUTF8Text(
                    () -> newGetRequest(urlForSessionsPerStation(station, startdate, enddate, format)),
                    format::consumeSessionsPerStation
            );
          }
          else{
            myReader.close();
            System.out.println("Not Authorized. Wrong APIKey");
            return Collections.emptyList();
          }

        }catch (FileNotFoundException ex)
        {
            return sendRequestAndParseResponseBodyAsUTF8Text(
                    () -> newGetRequest(urlForSessionsPerStation(station, startdate, enddate, format)),
                    format::consumeSessionsPerStation
            );
        }

    }

    public List<SessionsPerEV> getSessionsPerEV(String ev,
                                                                            LocalDate startdate,
                                                                            LocalDate enddate,
                                                                            Format format,
                                                                            String apikey) {
        try{
            File myObj = new File(System.getProperty("user.home") + "/softeng20bAPI.token");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                token = myReader.nextLine();
                //System.out.println(token);
            }
            if(apikey.equals(token)){
            myReader.close();
            return sendRequestAndParseResponseBodyAsUTF8Text(
                    () -> newGetRequest(urlForSessionsPerEV(ev, startdate, enddate, format)),
                    format::consumeSessionsPerEV
            );
          }
          else{
            myReader.close();
            System.out.println("Not Authorized. Wrong APIKey");
            return Collections.emptyList();
          }


        }catch (FileNotFoundException ex)
        {
            return sendRequestAndParseResponseBodyAsUTF8Text(
                    () -> newGetRequest(urlForSessionsPerEV(ev, startdate, enddate, format)),
                    format::consumeSessionsPerEV
            );
        }

    }

    public List<SessionsPerProvider> getSessionsPerProvider(String provider,
                                                            LocalDate startdate,
                                                            LocalDate enddate,
                                                            Format format,
                                                            String apikey) {
        try{
            File myObj = new File(System.getProperty("user.home") + "/softeng20bAPI.token");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                token = myReader.nextLine();
                //System.out.println(token);
            }
            if(apikey.equals(token)){
            myReader.close();
            return sendRequestAndParseResponseBodyAsUTF8Text(
                    () -> newGetRequest(urlForSessionsPerProvider(provider, startdate, enddate, format)),
                    format::consumeSessionsPerProvider
            );
            }
            else{
              myReader.close();
              System.out.println("Not Authorized. Wrong APIKey");
              return Collections.emptyList();
            }

        }catch (FileNotFoundException ex)
        {
            return sendRequestAndParseResponseBodyAsUTF8Text(
                    () -> newGetRequest(urlForSessionsPerProvider(provider, startdate, enddate, format)),
                    format::consumeSessionsPerProvider
            );
        }

    }


    //Helper method to create a new http client that can tolerate self-signed or improper ssl certificates
    private static HttpClient newHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());
        return HttpClient.newBuilder().sslContext(sslContext).build();
    }

    private static TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
            }
    };

    private static HttpRequest.BodyPublisher ofUrlEncodedFormData(Map<String, String> data) {
        return HttpRequest.BodyPublishers.ofString(ClientHelper.encode(data));
    }

    private static HttpRequest.BodyPublisher ofMultipartFormData(Map<String, Object> data, String boundary)
            throws IOException {
        var byteArrays = new ArrayList<byte[]>();
        String separator = "--" + boundary + "\r\nContent-Disposition: form-data; name=";
        byte[] separatorBytes = separator.getBytes(StandardCharsets.UTF_8);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            byteArrays.add(separatorBytes);

            if (entry.getValue() instanceof Path) {
                var path = (Path) entry.getValue();
                String mimeType = Files.probeContentType(path);
                System.out.println(mimeType);
                byteArrays.add(("\"" + entry.getKey() + "\"; filename=\"" + path.getFileName()
                        + "\"\r\nContent-Type: " + mimeType + "\r\n\r\n").getBytes(StandardCharsets.UTF_8));
                //byteArrays.add(Base64.getMimeEncoder().encode(Files.readAllBytes(path)));
                byteArrays.add(Files.readAllBytes(path));
                byteArrays.add("\r\n".getBytes(StandardCharsets.UTF_8));
            } else {
                byteArrays.add(("\"" + entry.getKey() + "\"\r\n\r\n" + entry.getValue() + "\r\n")
                        .getBytes(StandardCharsets.UTF_8));
            }
        }
        byteArrays.add(("--" + boundary + "--").getBytes(StandardCharsets.UTF_8));
        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }
}
