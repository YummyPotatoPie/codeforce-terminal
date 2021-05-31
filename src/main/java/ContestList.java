import CodeforceObjects.Contest;
import CodeforceObjects.Result;

public class ContestList extends CodeforcesMethod<String[], Contest> implements Handler<String[]>{

    private int contentLength = 30;
    private boolean isGym = false;

    @Override
    public boolean checkArgumentsValidation(String[] args) {
        if (args.length == 0) {
            return true;
        }
        else {
            try {
                contentLength = Integer.parseInt(args[0]);
                isGym = Boolean.parseBoolean(args[1]);
            }
            catch (NumberFormatException ex) {
                super.invalidArgumentsMessage = "--contest.list [length: int] [gym: bool]";
                return false;
            }
            catch (IndexOutOfBoundsException ignored) { }
        }
        return true;
    }

    @Override
    public String prepareArguments(String[] args) {
        return "contests.list?gym=" + isGym;
    }

    @Override
    public void displayMethodResult(Result<Contest> response) {
        for (int i = 0; i < contentLength && i < response.result.length; i++) {
            Contest contest = response.result[i];
            System.out.printf("ID: %-8d Name: %-45s Type: %-8s Phase: %-15s\n",
                    contest.id, contest.name.length() > 40 ? contest.name.substring(0, 40) + "..." : contest.name,
                    contest.type, contest.phase);
        }
    }

}
