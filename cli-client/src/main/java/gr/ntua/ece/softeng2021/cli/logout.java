package gr.ntua.ece.softeng2021.cli;

import gr.ntua.ece.softeng2021.client.RestAPI;
import gr.ntua.ece.softeng2021.data.model.User;
import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(
    name="logout"
)
public class logout extends BasicCliArgs implements Callable<Integer> {
/*
@Option(
      names = {"-t", "--token"},
      required = true,
      description = "each user's token"
  )
*/
  protected String token;
  @Override
  public Integer call() throws Exception {
    CommandLine cli = spec.commandLine();
    if (usageHelpRequested) {

        cli.usage(cli.getOut());
        return 0;
    }

    try{
      new RestAPI().logout();
        return 0;
    }catch (RuntimeException e) {
        cli.getOut().println(e.getMessage());
        e.printStackTrace(cli.getOut());
        return -1;
    }

    }
}
