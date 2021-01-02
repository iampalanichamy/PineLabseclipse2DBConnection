package pine.test.myMain;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import pine.test.utility.Constant;
import pine.test.utility.ExcelRead;

public class DataProviderWithExcel {

	private static String sTestCaseName;
	private static int iTestCaseRow;

	@DataProvider
	public Object[][] Authentication(Method m) throws Exception {

		// Setting up the Test Data Excel file

		ExcelRead.setExcelFile(Constant.File_TestData, "TestData");

		sTestCaseName = m.getName();
		iTestCaseRow = ExcelRead.getRowContains(sTestCaseName, 0);
		System.out.println("iTestCaseRow: " + iTestCaseRow);
		Object[][] testObjArray = ExcelRead.getTableArray(Constant.File_TestData, "TestData", iTestCaseRow);

		return (testObjArray);
	}

	@DataProvider
	public Object[][] getExcelDataMultiple(Method m) throws Exception {

		// Setting up the Test Data Excel file

		/*
		 * ExcelRead.setExcelFile(Constant.File_TestData, "TestData");
		 * 
		 * sTestCaseName = m.getName(); iTestCaseRows =
		 * ExcelRead.getRowsContains(sTestCaseName, 0); System.out.println("Size : " +
		 * iTestCaseRows.size()); for (Integer rowNum : iTestCaseRows) {
		 * System.out.println("ROW NUMBER: "+rowNum); } HashMap<Integer,
		 * ArrayList<String>> testObjMap = new HashMap<>();
		 * //System.out.println("TestCaseName: "+ sTestCaseName+ ";"+"iTestCaseRow: " +
		 * iTestCaseRow); for (int testCaseRow : iTestCaseRows) { //Object[][]
		 * testObjArray = ExcelRead.getTableArray(Constant.File_TestData, "TestData",
		 * iTestCaseRow); ArrayList<String> value = new ArrayList<String>(); Object [][]
		 * testArray = ExcelRead.getTableArray(Constant.File_TestData, "TestData",
		 * testCaseRow); System.out.println("Got Row :"+ testCaseRow); for (int i=0;
		 * i<testArray.length;i++) { for(int j=0; j<testArray[i].length;j++) {
		 * value.add(testArray[i][j].toString()); }
		 * 
		 * } testObjMap.put(testCaseRow, value);
		 * 
		 * } for(Integer items : testObjMap.keySet()) {
		 * 
		 * //String item = testObjMap.get(items); ArrayList<String> item =
		 * testObjMap.get(items); for(int i=0;i<item.size();i++) { System.out.println(i
		 * + " : " + item.get(i)); } }
		 * 
		 * return (testObjMap);
		 */

		ExcelRead.setExcelFile(Constant.File_TestData, "TestData");

		sTestCaseName = m.getName();
		// iTestCaseRow = ExcelRead.getRowContains(sTestCaseName, 0);
		// System.out.println("TestCaseName: "+ sTestCaseName+ ";"+"iTestCaseRow: " +
		// iTestCaseRow);
		Object[][] testObjArray = ExcelRead.getTableArrayMultiple(Constant.File_TestData, "TestData", sTestCaseName);

		return (testObjArray);

	}

}
