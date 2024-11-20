import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class ConvertToTxt {
    public static void main(String[] args) {

        String inputPath = args[0];
        File inputDirectory = new File(inputPath);

        if (!inputDirectory.exists() || !inputDirectory.isDirectory()) {
            System.out.println("Oops, forgot the path...");
            return;
        }

        File documentationDirectory = new File(inputDirectory.getParent(), "Documentation");
        // Find Documentation dir or make it if not there
        if (!documentationDirectory.exists()) {
            if (!documentationDirectory.mkdirs()) {
                System.out.println("Failed to create the Documentation directory.");
                return;
            }
        }

        for (File file : Objects.requireNonNull(inputDirectory.listFiles())) {
            if (file.isFile() && file.getName().endsWith(".java")) {
                try {
                    String newFileName = file.getName().replace(".java", ".txt");
                    Path targetPath = new File(documentationDirectory, newFileName).toPath();
                    Files.copy(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Copied: " + file.getName() + " to Documentation folder as " + newFileName);
                } catch (IOException e) {
                    System.out.println("Failed to copy file: " + file.getName());
                    e.printStackTrace();
                }
            }
        }
    }
}
