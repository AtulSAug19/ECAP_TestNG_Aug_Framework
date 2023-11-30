package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetData {

	private int getColIndex(String fileName, String sheetName, String colName) throws IOException {
		int colIndex = 0;
		FileInputStream fileInput = new FileInputStream(new File(fileName));
		XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int totalCols = sheet.getRow(0).getLastCellNum();
		for (int i = 0; i < totalCols; i++) {
			if (colName.equalsIgnoreCase(sheet.getRow(0).getCell(i).getStringCellValue()))
				colIndex = i;
		}
		workbook.close();
		return colIndex;
	}

	public void writeValueInExcel(String fileName, String sheetName, int rowNumber, String colName, String value)
			throws EncryptedDocumentException, IOException {
		FileInputStream fileInput = new FileInputStream(new File(fileName));
		Workbook workbook = null;
		workbook = WorkbookFactory.create(fileInput);
		Sheet sheet = workbook.getSheet(sheetName);
		int colIndex = getColIndex(fileName, sheetName, colName);
		sheet.getRow(rowNumber).createCell(colIndex, CellType.STRING).setCellValue(value);
		FileOutputStream fileOutput = new FileOutputStream(new File(fileName));
		workbook.write(fileOutput);
		workbook.close();

	}

	public String getValueFromExcel(String fileName, String sheetName, int rowNumber, String colName) {
		String value = "";
		try {
			FileInputStream fileInput = new FileInputStream(new File(fileName));
			XSSFWorkbook workbook;

			workbook = new XSSFWorkbook(fileInput);

			XSSFSheet sheet = workbook.getSheet(sheetName);
			value = sheet.getRow(rowNumber).getCell(getColIndex(fileName, sheetName, colName)).getStringCellValue();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	public String[][] getAllDataFromExcel(String fileName, String sheetName) throws IOException {
		FileInputStream fileInput = new FileInputStream(new File(fileName));
		XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int totalRows = sheet.getLastRowNum();
		int totalcols = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[totalRows][totalcols];
		for (int i = 1; i < totalRows; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < totalcols; j++) {
				if (row.getCell(j).getCellType() == CellType.STRING) {
					data[i - 1][j] = row.getCell(j).getStringCellValue();
				} else if (row.getCell(j).getCellType() == CellType.NUMERIC) {
					data[i - 1][j] = String.valueOf(row.getCell(j).getNumericCellValue());
				}
			}
		}
		workbook.close();
		return data;
	}

}
