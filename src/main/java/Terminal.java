import org.apache.commons.cli.*;

import java.io.IOException;

class Terminal implements Handler<CommandLine> {

    public static void main(String[] args) {
        Options terminalOptions = TerminalOptions.getTerminalOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(terminalOptions, args);
        }
        catch (ParseException ex) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("codeforce-terminal", terminalOptions);
        }

        Terminal terminal = new Terminal();
        terminal.handle(cmd);

    }

    public void handle(CommandLine cmd) {
        if (cmd.hasOption("contest-list")) {
            ContestList contestList = new ContestList();

            try {
                contestList.handle(cmd.getOptionValues("contest-list"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
