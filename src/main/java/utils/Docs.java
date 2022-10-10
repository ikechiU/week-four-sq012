package utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import model.Product;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Docs {

    public static List<Product> getProducts(List<String[]> productList) {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i < productList.size(); i++) {
            String[] arrayProducts = productList.get(i);
            //System.out.println(Arrays.toString(arrayProducts));
            products.add(new Product(arrayProducts[0], arrayProducts[1], Integer.parseInt(arrayProducts[2]), arrayProducts[3], arrayProducts[4], arrayProducts[5], arrayProducts[6], Double.parseDouble(arrayProducts[7])));
        }
        return products;
    }

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
