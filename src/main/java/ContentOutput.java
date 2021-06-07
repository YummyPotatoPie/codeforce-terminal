import java.io.File;

public class ContentOutput extends SettingsChanger implements Handler<String> {

    public void handle(String request) throws Exception {
        if (!new File(System.getProperty("user.dir") + "\\settings").exists()) {
            System.out.println("Settings file corrupted or does not exist");
            return;
        }

        if (request.equals("console")) {

        }
        else {
            if (request.matches("file=+.")) {
                String path = request.split("=")[1];
                if (!new File(path).exists()) {
                    System.out.println("File does not exist");
                }
                else {

                }
                return;
            }
            System.out.println("Invalid argument");
        }
    }
}
