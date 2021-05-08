package gr.ntua.ece.softeng2021.cli;

import picocli.CommandLine;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.http.HttpClient;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.Callable;
import java.util.function.Function;
import static picocli.CommandLine.*;


@Command(
    name="evehicle_67",
    mixinStandardHelpOptions = true,
    version = "evehicle_TEAM 1.0",
    subcommands = {
        healthcheck.class,
        login.class,
        logout.class,
        resetsessions.class,
        SessionsPerStationCli.class,
        SessionsPerEVCli.class,
        SessionsPerPointCli.class,
        SessionsPerProviderCli.class,
        Admin.class
    }
)
public class App implements Callable<Integer> {



    static final String BASE_URL = "https://localhost:8765/evcharge/api";

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new App());
        commandLine.setCaseInsensitiveEnumValuesAllowed(true);
        commandLine.setStopAtUnmatched(true);
        int exitCode = commandLine.execute(args);

        //If there's no sub-command, show the usage
        if (commandLine.getParseResult().subcommand() == null) {
            commandLine.usage(System.out);
        }
        System.exit(exitCode);
    }

    //Helper method to create a new http client that can tolerate self-signed or improper ssl certificates
    static HttpClient newHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());
        return HttpClient.newBuilder().sslContext(sslContext).build();
    }

    private static TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
            }
        }
    };


    @Override
    public Integer call() throws Exception {
        return 0;
    }
}
