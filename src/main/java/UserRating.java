import CodeforceObjects.RatingChange;
import CodeforceObjects.Result;

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

    }
}
