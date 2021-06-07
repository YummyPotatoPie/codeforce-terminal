import java.io.File;

public class ContentOutput extends SettingsChanger implements Handler<String> {

    public void handle(String request) {
        if (!new File(System.getProperty("user.dir") + "\\settings").exists()) {
            System.out.println("Settings file corrupted or does not exist");
            return;
        }

        if (request.equals("console")) {
            changeSetting("content-output", "console");
        }
        else {
            if (request.matches("file=+.")) {
                String path = request.split("=")[1];
                if (!new File(path).exists()) {
                    System.out.println("File does not exist");
                }
                else {
                    changeSetting("content-output", path);
                }
                return;
            }
            System.out.println("Invalid argument");
        }
    }
}
