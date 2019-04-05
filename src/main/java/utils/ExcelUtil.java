package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	
	// read the excel file and return the row data in the form of hashMap 
	/**
	 * @param fileLocation This is the location of the Excel file 
	 * @param sheetName This is the sheet name for the excel file 
	 * @param rowNumber This is the row number for the data 
	 * @return map object 
	 */
	public HashMap<String, String> getRowdata(String fileLocation, String sheetName, int rowNumber) {
		HashMap<String, String> data = new HashMap<String, String>();

		try {
			FileInputStream file = new FileInputStream(new File(fileLocation));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			XSSFRow headerRow = sheet.getRow(0);
			XSSFRow dataRow = sheet.getRow(rowNumber);
			for (int i = 0; i < dataRow.getLastCellNum(); i++) {
				data.put(headerRow.getCell(i).toString(), dataRow.getCell(i).toString());
			}

			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}
