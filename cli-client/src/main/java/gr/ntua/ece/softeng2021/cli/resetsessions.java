package gr.ntua.ece.softeng2021.cli;

import gr.ntua.ece.softeng2021.client.RestAPI;
import gr.ntua.ece.softeng2021.data.model.User;
import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.Callable;

//import static picocli.CommandLine.Command;
import static picocli.CommandLine.*;

@Command(
    name="resetsessions"
)
public class resetsessions extends BasicCliArgs implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }

        try {
            new RestAPI().resetDatabase();
	    System.out.println("Reset sessions was successful");
            return 0;
        } catch (RuntimeException e) {
		 System.out.println("Error in reset sessions");
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }
    }
}
