package pine.test.utility;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	// This method is to set the File path and to open the Excel file, Pass Excel
	// Path and Sheetname as Arguments to this method

	public static void setExcelFile(String Path, String SheetName) throws Exception {

		try {

			// Open the Excel file

			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e) {

			throw (e);

		}

	}

	public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow) throws Exception

	{

		String[][] tabArray = null;

		try {

			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startCol = 1;

			int ci = 0, cj = 0;

			int totalRows = 1;

			Row = ExcelWSheet.getRow(iTestCaseRow);
			int totalCols = Row.getLastCellNum();

			tabArray = new String[totalRows][totalCols - 1];

			for (int j = startCol; j < totalCols; j++, cj++)

			{

				tabArray[ci][cj] = getCellData(iTestCaseRow, j);

				System.out.println(tabArray[ci][cj]);

			}

		}

		catch (FileNotFoundException e)

		{

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e)

		{

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (tabArray);

	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num

	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

		} catch (Exception e) {

			return "";

		}

	}

	public static String getTestCaseName(String sTestCase) throws Exception {

		String value = sTestCase;

		try {

			int posi = value.indexOf("@");

			value = value.substring(0, posi);

			posi = value.lastIndexOf(".");

			value = value.substring(posi + 1);

			return value;

		} catch (Exception e) {

			throw (e);

		}

	}

	public static int getRowContains(String sTestCaseName, int colNum) throws Exception {

		int i;

		try {

			int rowCount = ExcelRead.getRowUsed();

			for (i = 0; i < rowCount; i++) {

				if (ExcelRead.getCellData(i, colNum).equalsIgnoreCase(sTestCaseName)) {

					break;

				}

			}

			return i;

		} catch (Exception e) {

			throw (e);

		}

	}

	public static ArrayList<Integer> getRowsContains(String sTestCaseName, int colNum) throws Exception {

		ArrayList<Integer> i = new ArrayList<>();

		try {

			int rowCount = ExcelRead.getRowUsed();
			System.out.println("RowCount: " + rowCount);
			for (int j = 0; j < rowCount + 1; j++) {

				if (ExcelRead.getCellData(j, colNum).equalsIgnoreCase(sTestCaseName)) {
					// System.out.println("Found " + sTestCaseName + " at " + j + "th row");
					i.add(j);

				}

			}

			return i;

		} catch (Exception e) {

			throw (e);

		}

	}

	public static Object[][] getTableArrayMultiple(String FilePath, String SheetName, String sTestCaseName)
			throws Exception

	{
		// iTestCaseRow
		String[][] tabArray = null;

		try {

			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startCol = 1;

			int ci = 0, cj = 0;

			ArrayList<Integer> allRows = getRowsContains(sTestCaseName, 0);
			int totalRows = allRows.size();
			// System.out.println(totalRows);
			Row = ExcelWSheet.getRow(allRows.get(0));
			int totalCols = Row.getLastCellNum();
			tabArray = new String[totalRows][totalCols - 1];
			System.out.println("Creating 2D array of size: " + totalRows + "*" + (totalCols - 1));
			for (Integer row : allRows) {
				Row = ExcelWSheet.getRow(row);
				System.out.println("GetRow: " + row);

				for (int j = startCol; j < totalCols; j++, cj++) {
					tabArray[ci][cj] = getCellData(row, j);
				}
				ci++;
				cj = 0;
			}
		}

		catch (FileNotFoundException e)

		{

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e)

		{

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (tabArray);

	}

	public static int getRowUsed() throws Exception {

		try {

			int RowCount = ExcelWSheet.getLastRowNum();

			return RowCount;

		} catch (Exception e) {

			System.out.println(e.getMessage());

			throw (e);

		}

	}

}
