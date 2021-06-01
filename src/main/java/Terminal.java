import CodeforcesObjects.Contest;
import CodeforcesObjects.RatingChange;
import CodeforcesObjects.Result;
import CodeforcesObjects.User;
import org.apache.commons.cli.*;

class Terminal implements Handler<CommandLine> {

    public static void main(String[] args) {
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
