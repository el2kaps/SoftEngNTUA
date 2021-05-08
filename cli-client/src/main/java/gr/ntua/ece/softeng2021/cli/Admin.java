package gr.ntua.ece.softeng2021.cli;

import gr.ntua.ece.softeng2021.client.ImportResult;
import gr.ntua.ece.softeng2021.client.RestAPI;
import gr.ntua.ece.softeng2021.data.model.User;
import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
 import java.nio.file.*;
import java.util.concurrent.Callable;

import static picocli.CommandLine.*;


@Command(name="Admin")
public class Admin extends BasicCliArgs implements Callable<Integer> {

  public enum Filenames {
    SessionsPerPoint,
    SessionsPerStation,
    SessionsPerEV,
  	SessionsPerProvider
  }

/*

  static class Group1 {
    @Option(names = "--passw", required = true)
    @Option(names = "--usermod", required = true)
    @ArgGroup(exclusive = true, multiplicity = "2")
    //Group1Inner inner1;
    String passw, username;
  }

*/

  static class Group1 {
    @Option(names = "--usermod", required = true, arity = "0") String fwiu;
    @Option(names = "--username", required = true) String username;
    @Option(names = "--passw", required = true) String passw;
  }
/*
  static class Group1Inner {
      @Option(names = "--usermod", required = true) String username;
  }
*/
  static class Group2 {
      @Option(names = "--users", required = true)
      String username;
  }

  static class Group3 {
      @Option(names = "--sessionsupd", required = true)
      Filenames filenames;

      @ArgGroup(exclusive = false, multiplicity = "1")
      Group3Inner inner3;
  }

  static class Group3Inner {
      @Option(names = "--source", required = true) File file;
  }


  static class AllGroups {
      @ArgGroup(exclusive = false, multiplicity = "1") Group1 group1;
      @ArgGroup(exclusive = false, multiplicity = "1") Group2 group2;
      @ArgGroup(exclusive = false, multiplicity = "1") Group3 group3;
  }

  @ArgGroup(exclusive = true, multiplicity = "1")
  AllGroups allGroups;

  @Override
  public Integer call() throws Exception {
    CommandLine cli = spec.commandLine();
    if (usageHelpRequested) {

        cli.usage(cli.getOut());
        return 0;
    }

    Optional<Group1> g1 = Optional.ofNullable(allGroups.group1);
	  Optional<Group2> g2 = Optional.ofNullable(allGroups.group2);
    Optional<Group3> g3 = Optional.ofNullable(allGroups.group3);

    try{
		if(g2.isPresent()){
			//System.out.println(g2.get());
		}

		if(g1.isPresent()){
				//System.out.println("hello there usermod");
				User user = new RestAPI().UserMod(allGroups.group1.username, allGroups.group1.passw);
				//System.out.println(user.getUsername());

				//User temp = new User();
				//temp.setusername(allGroups.group1.inner1.username2);
				//temp.setpassword(allGroups.group1.password);


			}

		else if(g2.isPresent()){
			//System.out.println("hello there showuser");
			User user = new RestAPI().getUser(allGroups.group2.username);
			//System.out.println("hello there after");
			//System.out.println(user.getUsername());
			//System.out.println(user.getpassword());
		}

		else if(g3.isPresent()){
			System.out.println(allGroups.group3.inner3.file.getPath());
			System.out.println(Paths.get(allGroups.group3.inner3.file.getPath()));


			ImportResult importresult = new RestAPI().importFile(allGroups.group3.filenames.name(), Paths.get(allGroups.group3.inner3.file.getPath()));
			System.out.println(importresult.getTotalRecordsInFile());

			System.out.println(importresult.getTotalRecordsImported());

			System.out.println(importresult.getTotalRecordsInDatabase());
		}

	    //System.out.println("hello there");
        return 0;

	}catch (Exception e) {
        cli.getOut().println(e.getMessage());
        e.printStackTrace(cli.getOut());
        return -1;
    }

  }
}
