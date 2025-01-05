package com.BasePackage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class DownloadedExcelReader {
	
	public static int getLastSrNo() throws IOException {
        // Directory where files are downloaded
        String downloadDir = "C:\\Users\\pinku.peter\\Downloads";

        // Get the latest downloaded file
        File latestFile = getLatestFileFromDir(downloadDir);
        if (latestFile == null) {
            System.out.println("No files found in the download directory.");
            return -1; 
        }

        // Open the latest Excel file
        FileInputStream fis = new FileInputStream(latestFile);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("sheet1"); // Use the correct sheet name

        // Find the last row with data in "Sr No." column
        int lastSrNo = 0;
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell cell = row.getCell(0); // Assuming "Sr No." is the first column (index 0)
            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                lastSrNo = (int) cell.getNumericCellValue();
            }
        }

        // Close the workbook
        workbook.close();

        return lastSrNo; // Return the last Sr No.
    }

    /**
     * Method to get the most recently modified file in a directory.
     *
     * @param dirPath Path to the directory
     * @return File object of the most recently modified file, or null if no files exist
     */
    private static File getLatestFileFromDir(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".xlsx")); // Filter for Excel files

        if (files == null || files.length == 0) {
            return null;
        }

        // Sort files by last modified date in descending order
        return Arrays.stream(files)
                     .max(Comparator.comparingLong(File::lastModified))
                     .orElse(null);
    }
}
