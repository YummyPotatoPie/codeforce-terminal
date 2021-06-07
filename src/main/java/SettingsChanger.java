import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class SettingsChanger {

    public void changeSetting(String settingName, String value) {
        try {
            File settingsFile = new File(System.getProperty("user.dir") + "\\settings");
            Scanner scanner = new Scanner(new FileReader(settingsFile));
            ArrayList<String> settings = new ArrayList<>();

            while (scanner.hasNextLine()) {
                settings.add(scanner.nextLine());
            }

            for (int i = 0; i < settings.size(); i++) {
                String currentSettingName = settings.get(i).split("=")[0];
                if (currentSettingName.equals(settingName)) {
                    settings.set(i, currentSettingName + "=" + value);
                }
            }

            if (settingsFile.delete()) {
                FileWriter writer = new FileWriter(settingsFile, true);
                for (String setting :settings) {
                    writer.append(setting);
                }
            }
            else {
                System.out.println("Settings rewrite was interrupted");
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("Settings file corrupted or does not exist");
        }
        catch (IOException ex) {
            System.out.println("Cannot rewrite settings file");
        }
    }

}
