import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class TerminalOptions {

    public static Option contestList = Option.builder()
            .longOpt("contest.list")
            .optionalArg(true)
            .numberOfArgs(2)
            .desc("Display contest info")
            .build();


    public static Option userInfo = Option.builder()
            .longOpt("user.info")
            .hasArgs()
            .numberOfArgs(Option.UNLIMITED_VALUES)
            .desc("Display users info")
            .build();

    public static Option userRating = Option.builder()
            .longOpt("user.rating")
            .hasArg()
            .desc("Display user rating changes")
            .build();

    public static Options getTerminalOptions() {
        Options options = new Options();
        options.addOption(contestList);
        options.addOption(userInfo);
        options.addOption(userRating);
        return options;
    }

}
