package parabank_utils;

import java.io.FileOutputStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteData {

    private static XSSFWorkbook wb;
    private static XSSFSheet sheet;

    public static void writeData(String[] headers) throws Exception {
	FileOutputStream fos = new FileOutputStream("src/test/java/parabank_testData/parabank_user_registration.xlsx");

	wb = new XSSFWorkbook();

	sheet = wb.createSheet("UserReg");

	XSSFRow row = sheet.createRow(0);
	int icount = 0;

	for (String header : headers) {

	    XSSFCell cell = row.createCell(icount);
	    cell.setCellValue(header);
	    icount++;
	}

	for (int i = 1; i <= row.getLastCellNum(); i++) {
	    XSSFRow row_val = sheet.createRow(i);
	    for (int j = 0; j < headers.length - 2; j++) {
		XSSFCell cell_val = row_val.createCell(j);
		cell_val.setCellValue(RandomStringUtils.randomAlphabetic(9));
	    }

	}

	for (int k = 1; k <= row.getLastCellNum(); k++) {
	    XSSFRow row_val = sheet.getRow(k);
	    for (int j = 10; j <= headers.length; j++) {
		XSSFCell cell_val = row_val.createCell(j - 1);
		cell_val.setCellValue("bipo");
	    }
	}

	wb.write(fos);
	fos.close();

    }
}
