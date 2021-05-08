package gr.ntua.ece.softeng2021.cli;

import gr.ntua.ece.softeng2021.client.Format;

import static picocli.CommandLine.*;

@Command
public class EVCliArgs extends BasicCliArgs {


    @Option(
        names = "--datefrom",
        required = true,
        description = "Starting date"
    )
    protected String datefrom;

    @Option(
        names = "--dateto",
        required = true,
        description = "Ending date"
    )
    protected String dateto;

    @Option(
        names = "--format",
        required = true,
        description = "the format of the response (csv or json)"
    )
    protected Format format;

	  @Option(
        names = "--apikey",
        required = true,
        description = "API key"
    )
    protected String apikey;


}
