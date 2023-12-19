package DataProviders;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    public static List<List<String>> readExcel(String filePath, String sheetName) {
        List<List<String>> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                List<String> rowData = new ArrayList<>();

                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String cellValue = getCellValueAsString(cell);
                    rowData.add(cellValue);
                }

                data.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else {
            return "";
        }
    }
}
//usage
//public class ExampleTest {
//    public static void main(String[] args) {
//        String filePath = "path/to/your/excel/file.xlsx";
//        String sheetName = "Sheet1";
//
//        List<List<String>> excelData = ExcelReader.readExcel(filePath, sheetName);
//
//        // Process the excelData as per your requirement
//        for (List<String> rowData : excelData) {
//            for (String cellValue : rowData) {
//                System.out.print(cellValue + "\t");
//            }
//            System.out.println();
//        }
//    }
//}

