package com.anurag.mva;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.opencsv.CSVReader;

public class CsvToXlsConverter {

	public static void convert(String tempPath) throws IOException, InterruptedException {
		
        String tempFile = tempPath + ".csv";
		CSVReader csvr = new CSVReader(new FileReader(new File(tempFile)));
		
        String[] record;

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Sheet1");

        int numberOfRows = 0;

        while ((record = csvr.readNext()) != null) {
            Row row = sheet.createRow(numberOfRows++);

            int numberOfColumns = 0;

            for (String cellValue : record) {
                Cell cell = row.createCell(numberOfColumns++);
                cell.setCellValue(cellValue);
                Thread.sleep(10000);
            }
            System.out.println();
        }
        
       String targetPath = tempPath + ".xls";
        
        FileOutputStream fileOut = new FileOutputStream(targetPath);
        wb.write(fileOut);
        
        wb.close();
        fileOut.close();
        csvr.close();

	}
}
