import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileProcessor {
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        // Set the directory path where the files are located
        String directoryPath = "/path/to/files";

        // Create a file filter to only include XML files
        FileFilter xmlFileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isFile() && file.getName().toLowerCase().endsWith(".xml");
            }
        };

        // Get all the XML files in the directory
        File directory = new File(directoryPath);
        File[] xmlFiles = directory.listFiles(xmlFileFilter);

        // Loop through each XML file and read its contents
        for (File xmlFile : xmlFiles) {
            executor.execute(() -> {
                try {
                    // Use NIO to read the file contents
                    Path path = Paths.get(xmlFile.getAbsolutePath());
                    String fileContent = new String(Files.readAllBytes(path));

                    // Process the XML file content here
                    // ...

                    System.out.println("Processed file: " + xmlFile.getName());
                } catch (Exception e) {
                    System.err.println("Error processing file: " + xmlFile.getName());
                }
            });
        }

        executor.shutdown();
    }
}
