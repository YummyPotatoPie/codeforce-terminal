import CodeforceObjects.Result;
import CodeforceObjects.User;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.lang.reflect.Type;

public class UserInfo implements Handler<String[]> {

    public void handle(String[] args) {
        if (args.length == 0) {
            System.out.println("Users list must be not empty");
            return;
        }

        String usersList = "";
        for (String users : args) {
            usersList = usersList.concat(users + ';');
        }
        usersList = usersList.substring(0, usersList.length() - 1);

        try {
            final Content users = Request.Get(TerminalConstants.codeforcesUrl + "user.info?handles=" + usersList)
                                    .execute()
                                    .returnContent();

            Gson gson = new Gson();
            Type type = new TypeToken<Result<User>>() {}.getType();
            Result<User> usersInfoList = gson.fromJson(users.asString(), type);

            for (User user : usersInfoList.result) {
                System.out.printf("Handle: %-8s Rank: %-5s VKID: %-8s Email: %-15s\n",
                        user.handle, user.rank,
                        user.vkId != null ? user.vkId : "Hidden",
                        user.email != null ? user.email : "Hidden");
            }
        }
        catch (IOException | JsonParseException e) {
            System.out.println("Something went wrong");
        }
    }

}
