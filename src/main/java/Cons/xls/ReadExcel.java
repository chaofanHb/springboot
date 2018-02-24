package Cons.xls;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
public class ReadExcel {
    public static void main(String[] args) {
        File file=new File("C:/Users/Administrator.admin-PC/Desktop/t.xlsx");
        ReadExcel readExcel=new ReadExcel();
        List<User> list= readExcel.read(file);
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i).toString());
        }
    }

    public List<User> read(File file){
        List<User> result = new ArrayList<User>();
        try {
            Workbook workbook = WorkbookFactory.create(file);  //new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            int rowStart = sheet.getFirstRowNum()+1;
            int rowEnd = sheet.getLastRowNum();

            for (int i = rowStart; i <= rowEnd; i++) {
                Row row = sheet.getRow(i);
                User user = this.getUserFromRow(row,User.class);
                if (user != null) result.add(user);
            }
            workbook.close();
        }catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected User getUserFromRow(Row row,Class<?> t) {
        if(row == null) return null;
        int current = row.getFirstCellNum()+1;
        Cell cell = row.getCell(current);
        if(null != cell) {
            User user = new User();
            user.setUsername(cell.getStringCellValue());
            current++;

            cell = row.getCell(current);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            user.setPassword(cell.getStringCellValue());
            current++;

            cell = row.getCell(current);
            user.setNickname(cell.getStringCellValue());
            return user;
        }
        return null;
    }
}
