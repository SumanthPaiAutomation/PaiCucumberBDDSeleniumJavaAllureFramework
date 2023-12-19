package DataProviders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<List<String>> readCSV(String filePath) {
        List<List<String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<String> rowData = new ArrayList<>();

                for (String value : values) {
                    rowData.add(value.trim());
                }

                data.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}

//usage
//public class ExampleTest {
//    public static void main(String[] args) {
//        String filePath = "path/to/your/csv/file.csv";
//
//        List<List<String>> csvData = CSVReader.readCSV(filePath);
//
//        // Process the csvData as per your requirement
//        for (List<String> rowData : csvData) {
//            for (String cellValue : rowData) {
//                System.out.print(cellValue + "\t");
//            }
//            System.out.println();
//        }
//    }
//}

