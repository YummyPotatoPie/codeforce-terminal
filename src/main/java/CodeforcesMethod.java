import CodeforceObjects.Result;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.apache.http.ConnectionClosedException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import java.io.IOException;
import java.lang.reflect.Type;

public abstract class CodeforcesMethod<T, V> implements Handler<T> {

    protected String invalidArgumentsMessage;

    public abstract boolean checkArgumentsValidation(T args);

    public abstract String prepareArguments(T args);

    public abstract void displayMethodResult(Result<V> response);

    public void handle(T args) {
        if (!checkArgumentsValidation(args)) {
            System.out.println(this.invalidArgumentsMessage);
            return;
        }

        String preparedArguments = prepareArguments(args);

        try {
            final Content content = Request.Get(TerminalConstants.codeforcesUrl + preparedArguments)
                    .execute()
                    .returnContent();

            Gson gson = new Gson();
            Type type = new TypeToken<Result<V>>() {}.getType();
            Result<V> deserializedContent = gson.fromJson(content.asString(), type);

            displayMethodResult(deserializedContent);

        }
        catch (ConnectionClosedException ex) {
            System.out.println("Cannot send request: connection closed");
        }
        catch (IOException | JsonParseException ex) {
            System.out.println("Cannot get response: status \"FAILED\"");
        }

    }

}
