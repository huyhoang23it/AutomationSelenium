package fptu.econest.ultilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ReadExcelFile {
    public static FileInputStream inputStream;
    public static XSSFWorkbook workBook;
    public static XSSFSheet excelSheet;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static String getCellValue(String fileName, String sheetName, int rowNo, int cellNo) {
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(inputStream);
            excelSheet = workBook.getSheet(sheetName);
            cell = excelSheet.getRow(rowNo).getCell(cellNo);

            if (cell != null) {
                switch (cell.getCellType()) {
                    case STRING:
                        return cell.getStringCellValue();
                    case NUMERIC:
                        return String.valueOf((long) cell.getNumericCellValue());
                    case BOOLEAN:
                        return String.valueOf(cell.getBooleanCellValue());
                    case FORMULA:
                        return cell.getCellFormula();
                    default:
                        return "";
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (workBook != null) {
                    workBook.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int getRowCount(String fileName,String sheetName)
    {
        try
        {
            inputStream= new FileInputStream(fileName);
            workBook= new XSSFWorkbook(inputStream);
            excelSheet=workBook.getSheet(sheetName);
            int ttlRows= excelSheet.getLastRowNum() + 1;
            workBook.close();
            return ttlRows;
        }
        catch(Exception e)
        {
            return 0;
        }
    }
    public static int getColCount(String fileName,String sheetName)
    {
        try
        {
            inputStream= new FileInputStream(fileName);
            workBook= new XSSFWorkbook(inputStream);
            excelSheet=workBook.getSheet(sheetName);
            int ttlCells= excelSheet.getRow(0).getLastCellNum();
            workBook.close();
            return ttlCells;
        }

        catch(Exception e)
        {
            return 0;
        }
    }
    public String getStringData(int sheetIndex,int row,int column)
    {
        return workBook.getSheetAt(sheetIndex).getRow(row).getCell(column).getStringCellValue();
    }

    public String getStringData(String sheetName,int row,int column)
    {
        return workBook.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
    }

    public double getNumericData(String sheetName,int row,int column)
    {
        return workBook.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
    }
}
