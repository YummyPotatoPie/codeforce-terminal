import CodeforcesObjects.Result;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.apache.http.ConnectionClosedException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import java.io.IOException;
import java.lang.reflect.Type;

public abstract class CodeforcesMethod<T, V> implements Handler<T> {

    protected Type contentType;

    protected String invalidArgumentsMessage;

    public abstract boolean checkArgumentsValidation(T args);

    public abstract String prepareArguments(T args);

    public abstract void displayMethodResult(Result<V> response);

    public void setContentType(Type contentType) {
        this.contentType = contentType;
    }

    public void handle(T args) {
        if (!checkArgumentsValidation(args)) {
            System.out.println(this.invalidArgumentsMessage);
            return;
        }

        String preparedArguments = prepareArguments(args);

        try {
            final Content content = Request.Get(TerminalSettings.codeforcesUrl + preparedArguments)
                    .execute()
                    .returnContent();

            Gson gson = new Gson();
            Result<V> deserializedContent = gson.fromJson(content.asString(), this.contentType);

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
