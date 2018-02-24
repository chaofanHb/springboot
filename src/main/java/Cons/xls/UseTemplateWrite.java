package Cons.xls;

import Cons.entity.CustomerBill;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/8/8.
 */
public class UseTemplateWrite {

    protected String tempFileName;
    protected OutputStream os;
    protected Workbook book = null;

    public UseTemplateWrite() {
        super();
    }

    public static String createName(String fileName) {
        if (fileName == null) {
            return null;
        }
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (fileName.indexOf(")") >= 0) {
            String v = fileName.substring(fileName.lastIndexOf("(") + 1, fileName.lastIndexOf(")"));
            Pattern pattern = Pattern.compile("[0-9]*");
            if (pattern.matcher(v).matches()) {
                Integer num = Integer.valueOf(v);
                num++;
                return fileName.substring(0, fileName.lastIndexOf("(") + 1) + num + ")" + suffix;
            }
        } else {
            return fileName.substring(0, fileName.lastIndexOf(".")) + "(1)" + suffix;
        }
        return null;
    }

    public UseTemplateWrite(String fileName) {
        try {
            File file = new File(fileName);
            while (file.exists()) {
                file = new File(createName(file.getPath()));
            }
            fileName = file.getPath();
            tempFileName = fileName + "-temp";
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            //由于Workbook在编辑表格时需要占用一个文件，所以创建一个tempFile作为工作空间  最后将数据写入到真正的表格
            if ("xls".equals(suffix)) {
                Files.copy(new File("E:\\file\\template.xls").toPath(), new File(fileName).toPath());
                Files.copy(new File("E:\\file\\template.xls").toPath(), new File(tempFileName).toPath());
            } else if ("xlsx".equals(suffix)) {
                Files.copy(new File("E:\\file\\template.xlsx").toPath(), new File(fileName).toPath());
                Files.copy(new File("E:\\file\\template.xlsx").toPath(), new File(tempFileName).toPath());
            }
            InputStream inputStream = new FileInputStream(tempFileName);
            book = WorkbookFactory.create(inputStream);
            os = new FileOutputStream(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public void Write(List<User> list) throws IOException {
        Sheet sheet = book.getSheetAt(0);
        int rowLast = sheet.getLastRowNum();
        for (int i = 0; i < rowLast + list.size(); i++) {
            Row currentRow = sheet.createRow(i + 1);
            currentRow.createCell(0).setCellFormula("ROW() - 1");
            currentRow.createCell(1).setCellValue(list.get(i).getUsername());
            //克隆文本格式单元格
            CellStyle cellStyle = sheet.getRow(0).getCell(2).getCellStyle();

            Cell cell = currentRow.createCell(2);
            cell.setCellType(Cell.CELL_TYPE_STRING);//表格查看点击时会失效
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getPassword());

            currentRow.createCell(3).setCellValue(list.get(i).getNickname());
        }
        book.write(os);
        book.close();
        new File(tempFileName).delete();
    }

    public void WriteAsCustomerBill(List<CustomerBill> list) {
        Sheet sheet = book.getSheetAt(0);
        int rowLast = sheet.getLastRowNum();
        for (int i = 0; i < rowLast + list.size(); i++) {
            CellStyle cellStyle = sheet.getRow(0).getCell(2).getCellStyle();
            Row currentRow = sheet.createRow(i + 1);
            currentRow.createCell(0).setCellFormula("ROW() - 1");
            currentRow.createCell(1).setCellValue(list.get(i).getCustomerCompanyName());
            //克隆文本格式单元格
            Cell cell2 = currentRow.createCell(2);
            cell2.setCellType(Cell.CELL_TYPE_STRING);
            cell2.setCellStyle(cellStyle);
            cell2.setCellValue(list.get(i).getPaymentAmount());

            Cell cell3 = currentRow.createCell(3);
            cell3.setCellType(Cell.CELL_TYPE_STRING);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(list.get(i).getAddAmount());

            Cell cell4 = currentRow.createCell(4);
            cell4.setCellType(Cell.CELL_TYPE_STRING);
            cell4.setCellStyle(cellStyle);
            cell4.setCellValue(list.get(i).getTurnover());

            currentRow.createCell(5).setCellValue(list.get(i).getTotalMonth());

            Cell cell6 = currentRow.createCell(6);
            cell6.setCellType(Cell.CELL_TYPE_STRING);
            cell6.setCellStyle(cellStyle);
            cell6.setCellValue(list.get(i).getCorreMoney());

            Cell cell7 = currentRow.createCell(7);
            cell7.setCellType(Cell.CELL_TYPE_STRING);
            cell7.setCellStyle(cellStyle);
            cell7.setCellValue(list.get(i).getSettleAccount());

            Cell cell8 = currentRow.createCell(8);
            cell8.setCellType(Cell.CELL_TYPE_STRING);
            cell8.setCellStyle(cellStyle);
            cell8.setCellValue(list.get(i).getMonthStartAmount());

            Cell cell9 = currentRow.createCell(9);
            cell9.setCellType(Cell.CELL_TYPE_STRING);
            cell9.setCellStyle(cellStyle);
            cell9.setCellValue(list.get(i).getMonthEndAmount());

            Cell cell10 = currentRow.createCell(10);
            cell10.setCellType(Cell.CELL_TYPE_STRING);
            cell10.setCellStyle(cellStyle);
            cell10.setCellValue(list.get(i).getBillAmount());
        }
        try {
            book.write(os);
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new File(tempFileName).delete();
    }
    public static void main(String[] args) {
        UseTemplateWrite useTemplateWrite = new UseTemplateWrite("E:\\t.xls");
        User user1 = new User("admin", "110.0", "管理员");
        User user2 = new User("user1", "108.5", "用户1");
        User user3 = new User("user2", "1000001564632000000000000.00", "用户2");
        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user3);
        list.add(user2);
        try {
            useTemplateWrite.Write(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
