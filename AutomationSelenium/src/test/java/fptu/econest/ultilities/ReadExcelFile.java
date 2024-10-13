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
            // Mở tệp Excel từ đường dẫn.
            inputStream = new FileInputStream(fileName);
            // Tạo đối tượng WorkBook từ FileInputStream.
            workBook = new XSSFWorkbook(inputStream);
            // Lấy sheet theo tên từ file Excel.
            excelSheet = workBook.getSheet(sheetName);
            // Lấy giá trị của ô (cell) từ một hàng (row) và cột (cell) xác định.
            cell = excelSheet.getRow(rowNo).getCell(cellNo);
// Kiểm tra nếu ô không rỗng và trả về giá trị của nó.
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
            // Đếm tổng số hàng trong sheet
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
            // Lấy tổng số cột từ hàng đầu tiên.
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
