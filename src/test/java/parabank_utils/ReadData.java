package parabank_utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {

    private static XSSFWorkbook wb;
    private static XSSFSheet sheet;
    private static DataFormatter df = new DataFormatter();

    public static void getWorkbook(String filepath) {
	try {
	    FileInputStream fis = new FileInputStream(filepath);
	    wb = new XSSFWorkbook(fis);
	} catch (Exception exception) {
	    System.out.println(exception.getMessage());
	}
    }

    public static int getRowCount(int sheetindex) {
	sheet = wb.getSheetAt(sheetindex);
	return sheet.getLastRowNum() + 1;
    }

    public static int getCellCount(int sheetindex) {
	sheet = wb.getSheetAt(sheetindex);
	XSSFRow row = sheet.getRow(0);
	return row.getLastCellNum();
    }

    public static String getData(int sheetindex, int row, int cell) {
	sheet = wb.getSheetAt(sheetindex);
	return df.formatCellValue(sheet.getRow(row).getCell(cell));
    }

}
