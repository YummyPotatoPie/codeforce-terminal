import CodeforcesObjects.RatingChange;
import CodeforcesObjects.Result;

public class UserRating  extends CodeforcesMethod<String, RatingChange> implements Handler<String> {

    @Override
    public boolean checkArgumentsValidation(String userHandle) {
        return true;
    }

    @Override
    public String prepareArguments(String userHandle) {
        return "user.rating?handle=" + userHandle;
    }

    @Override
    public void displayMethodResult(Result<RatingChange> response) {
        RatingChange userRating = response.result[0];
        System.out.printf("ContestID: %-5d ContestName: %-40s Handle: %-5s Rank: %-10d\n",
                userRating.contestId, userRating.contestName, userRating.handle, userRating.rank);
    }
}
