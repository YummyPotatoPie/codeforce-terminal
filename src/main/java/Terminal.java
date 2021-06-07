import CodeforcesObjects.Contest;
import CodeforcesObjects.RatingChange;
import CodeforcesObjects.Result;
import CodeforcesObjects.User;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

class Terminal implements Handler<CommandLine> {

    public static void getSettings() {
        try {
            FileReader reader = new FileReader(new File(System.getProperty("user.dir") + "\\settings"));
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                String[] setting = scanner.nextLine().split("=");

                switch (setting[0]) {
                    case "content-output" -> TerminalSettings.contentOutput = Boolean.parseBoolean(setting[1]);
                    case "wide-output" -> TerminalSettings.wideContent = Boolean.parseBoolean(setting[1]);
                }
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("Settings file does not exist. Settings set to default");
            TerminalSettings.contentOutput = true;
            TerminalSettings.wideContent = true;
        }
    }

    public static void main(String[] args) {
        getSettings();

        Options terminalOptions = TerminalOptions.getTerminalOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(terminalOptions, args);
            Terminal terminal = new Terminal();
            terminal.handle(cmd);
        }
        catch (ParseException ex) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("codeforces-terminal", terminalOptions);
        }

    }

    public void handle(CommandLine cmd) {
        if (cmd.hasOption("content.output")) {

        }

        if (cmd.hasOption("contest.list")) {
            ContestList contestList = new ContestList();
            contestList.setContentType(new Result<Contest>() {}.getClass().getGenericSuperclass());
            contestList.handle(cmd.getOptionValues("contest.list"));
        }

        if (cmd.hasOption("user.info")) {
            UserInfo userInfo = new UserInfo();
            userInfo.setContentType(new Result<User>() {}.getClass().getGenericSuperclass());
            userInfo.handle(cmd.getOptionValues("user.info"));
        }

        if (cmd.hasOption("user.rating")) {
            UserRating userRating = new UserRating();
            userRating.setContentType(new Result<RatingChange>() {}.getClass().getGenericSuperclass());
            userRating.handle(cmd.getOptionValue("user.rating"));
        }
    }
}
