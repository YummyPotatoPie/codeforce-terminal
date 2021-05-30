import CodeforceObjects.Contest;
import CodeforceObjects.Result;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Content;

import java.io.IOException;
import java.lang.reflect.Type;

public class ContestList implements Handler<String[]>{

    public void handle(String[] args) {
        int contentLength = 30;
        boolean isGym = false;

        try {
            contentLength = Integer.parseInt(args[0]);
            isGym = Boolean.parseBoolean(args[1]);
        }
        catch (IndexOutOfBoundsException | NullPointerException ignored) { }

        try {
            final Content contests = Request.Get(TerminalConstants.codeforcesUrl + "contest.list?gym=" + isGym)
                                        .execute()
                                        .returnContent();

            Gson gson = new Gson();
            Type type = new TypeToken<Result<Contest>>() {}.getType();
            Result<Contest> contestList = gson.fromJson(contests.asString(), type);

            for (int i = 0; i < contentLength; i++) {
                Contest contest = contestList.result[i];
                System.out.printf("ID: %-8d Name: %-45s Type: %-8s Phase: %-15s\n",
                        contest.id, contest.name.length() > 40 ? contest.name.substring(0, 40) + "..." : contest.name,
                        contest.type, contest.phase);
            }
        }
        catch (IOException | JsonParseException ex) {
            System.out.println("Something went wrong");
        }

    }

}
