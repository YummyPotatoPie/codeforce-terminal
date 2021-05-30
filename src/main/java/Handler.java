public interface Handler<T> {

    void handle(T request) throws Exception;

}
