package gr.ntua.ece.softeng2021.cli;

import gr.ntua.ece.softeng2021.client.RestAPI;
import picocli.CommandLine;

import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(
    name="healthcheck"
)

public class healthcheck extends BasicCliArgs implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        CommandLine cli = spec.commandLine();


        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }

        try {
            System.out.println("healthcheck");
            String health = new RestAPI().healthCheck();
            System.out.println(health);
            spec.commandLine().getOut().println(health);
            return 0;
        } catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }
    }
}
