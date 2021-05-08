package gr.ntua.ece.softeng2021;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import static org.junit.Assert.*;
import org.junit.Test;
import picocli.CommandLine;

import java.util.Optional;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.util.List;
import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import com.opencsv.CSVWriter;

import gr.ntua.ece.softeng2021.cli.*;
import gr.ntua.ece.softeng2021.client.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;  // JUnit 5
import org.junit.jupiter.api.BeforeEach;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
import picocli.CommandLine;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;


public class FunctionalTests{

  @Test
  public void testHealthcCheck() {
      App app = new App();
      CommandLine cmd = new CommandLine(app);

      StringWriter sw = new StringWriter();
      cmd.setOut(new PrintWriter(sw));

      int exitCode = cmd.execute("healthcheck");
      assertEquals(0, exitCode);
      assertEquals("OK", sw.toString().replace("\n", ""));
  }

  @Test
  public void testMainInvalid() {
    App app = new App();
    CommandLine cmd = new CommandLine(app);
    int exitCode = cmd.execute("--bad");
    assertEquals(2, exitCode);
  }

  @Test
  public void testWrongLogin() {
      login mylog = new login();
      //CommandLine cmd = new CommandLine(app);
      String[] args = "-u hacker -p petrol4ever".split(" ");

      CommandLine cmd = new CommandLine(mylog);
      cmd.parse(args);
      int exitCode =cmd.execute();
      assertEquals(2, exitCode);
  }

  @Test
  public void testResetSessions() {
    App app = new App();
    CommandLine cmd = new CommandLine(app);

    StringWriter sw = new StringWriter();
    cmd.setOut(new PrintWriter(sw));

    int exitCode = cmd.execute("resetsessions");
    assertEquals(0, exitCode);
  }

}
