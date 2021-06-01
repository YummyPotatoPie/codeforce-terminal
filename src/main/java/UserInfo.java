import CodeforcesObjects.Result;
import CodeforcesObjects.User;

public class UserInfo extends CodeforcesMethod<String[], User> implements Handler<String[]> {

    @Override
    public boolean checkArgumentsValidation(String[] args) {
        return true;
    }

    @Override
    public String prepareArguments(String[] args) {
        String usersList = "user.info?handles=";
        for (String users : args) {
            usersList = usersList.concat(users + ';');
        }
        return usersList.substring(0, usersList.length() - 1);
    }

    public void displayMethodResult(Result<User> response) {
        for (User user : response.result) {
            System.out.printf("Handle: %-20s Rank: %-27s VKID: %-13s Email: %-15s\n",
                    user.handle, user.rank,
                    user.vkId != null ? user.vkId : "Hidden",
                    user.email != null ? user.email : "Hidden");
        }
    }

}
