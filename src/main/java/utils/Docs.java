package utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Docs {

    public static List<String[]> readCSVUsingScanner(File csvFile) {
        List<String[]> returnValue = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(csvFile);
            while (scanner.hasNextLine()) {
                String[] rowArr = scanner.nextLine().split(",");
                returnValue.add(rowArr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    public static List<String[]> readCSVUsingBufferedReader(File csvFile) {
        List<String[]> returnValue = new ArrayList<>();
        String line = "";

        try {
            FileReader filereader = new FileReader(csvFile);
            BufferedReader bufferedReader = new BufferedReader(filereader);
             while ((line = bufferedReader.readLine()) != null) {
                 String[] rowArr = line.split(",");
                 returnValue.add(rowArr);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    //Using Apache POI Library
    public static String[][] readExcelSheet(String excelFile) {
        String[][] dataTable = null;
        try {
            FileInputStream file = new FileInputStream(excelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int numRows = sheet.getLastRowNum() + 1;
            int numCols = sheet.getRow(0).getLastCellNum();

            dataTable = new String[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < numCols; j++) {
                    XSSFCell cell = row.getCell(j);
                    dataTable[i][j] = cell.toString();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
        return dataTable;
    }


    //Using Opencsv Library
    public static List<String[]> readCSV(String csvFile) {
        List<String[]> returnValue = new ArrayList<>();

        try {
            FileReader filereader = new FileReader(csvFile);

            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            returnValue = csvReader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnValue;
    }


}
