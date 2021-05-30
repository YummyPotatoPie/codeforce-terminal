import java.io.IOException;

public interface Handler<T> {

    void handle(T request) throws IOException;

}
