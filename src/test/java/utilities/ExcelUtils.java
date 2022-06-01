package utilities;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelUtils {
    public static HSSFWorkbook workbook;
    public static HSSFSheet sheet;

    public  ExcelUtils(String excelPath, String sheetName){
        try {
            InputStream file = new FileInputStream(excelPath);

             workbook = new HSSFWorkbook(new POIFSFileSystem(file));
             sheet = workbook.getSheet(sheetName);
        } catch (IOException exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
    }

    public static void  getCellData(int rowNum, int colNum){
        // String projectDir = System.getProperty("user.dir");
        // String excelPath = projectDir+"/data/TestData.xlsx";
        //or--> String excelPath = "./data/TestData.xlsx";

        DataFormatter formatter = new DataFormatter();
        Object value =formatter.formatCellValue( sheet.getRow(rowNum).getCell(colNum));
        System.out.println(value);
  }

    public static void getRowCount(){
        int rowCount = sheet.getPhysicalNumberOfRows();
        System.out.println("No or Rows: "+rowCount);
    }
}
