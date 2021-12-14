import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private static final String FILE_LOGGER_NAME = "StudentFileOutput.txt";

    static {
        File fileLogger = new File(FILE_LOGGER_NAME);
        try {
            if (fileLogger.exists()) {
                fileLogger.delete();
                fileLogger.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("This file does not exist.");
        }
    }

    @Override
    public void log(String message) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_LOGGER_NAME, true);
            fileWriter.write(message+"\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("This file does not exist.");
        }
    }
}
