import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

class Terminal {

    public static void main(String[] args) throws IOException {

        final Content getResult = Request.Get("http://jsonplaceholder.typicode.com/posts?_limit=10")
                .execute().returnContent();
        System.out.println(getResult.asString());

    }
}
