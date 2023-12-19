package DataProviders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONReader {

    public static <T> T readJSONFile(String filePath, Class<T> type) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> readMultipleJSONFiles(String folderPath, Class<T> type) {
        List<T> data = new ArrayList<>();
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    T jsonData = readJSONFile(file.getAbsolutePath(), type);
                    if (jsonData != null) {
                        data.add(jsonData);
                    }
                }
            }
        }

        return data;
    }
}

//usage
//single file
//public class ExampleTest {
//    public static void main(String[] args) {
//        String filePath = "path/to/your/json/file.json";
//
//        YourDataType jsonData = JSONReader.readJSONFile(filePath, YourDataType.class);
//
//        // Process the jsonData as per your requirement
//        // ...
//    }
//}

//multiple file
//public class ExampleTest {
//    public static void main(String[] args) {
//        String folderPath = "path/to/your/json/folder";
//
//        List<YourDataType> jsonList = JSONReader.readMultipleJSONFiles(folderPath, YourDataType.class);
//
//        // Process the jsonList as per your requirement
//        // ...
//    }
//}


