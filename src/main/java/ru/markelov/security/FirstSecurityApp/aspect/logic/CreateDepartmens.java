package ru.markelov.security.FirstSecurityApp.aspect.logic;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreateDepartmens {
    private Path path;
    private List<Department> departmentList = new ArrayList<>();
    private Map<String,Department> departmentMap = new HashMap<>();

    public CreateDepartmens(Path path) {
        this.path = path;
        fillDepartmentList();
    }

    private void fillDepartmentList() {
        //        open the Exel Book and get sheet of sheetsName
        XSSFWorkbook allNicomDepartmensBook = null;
        try {
            allNicomDepartmensBook = new XSSFWorkbook(Files.newInputStream(path));
        } catch (IOException e) {
            System.out.println("Не может прочитан фаил. Возможно отсутствует/открыт/неверный путь/имя  и тд");
            throw new RuntimeException(e);
        }
        String sheetOneName = allNicomDepartmensBook.getSheetName(0);
        String sheetTwoName = allNicomDepartmensBook.getSheetName(1);
        XSSFSheet sheetOne = allNicomDepartmensBook.getSheet(sheetOneName);
        XSSFSheet sheetTwo = allNicomDepartmensBook.getSheet(sheetTwoName);
        addDepartmentsToListFromSheet(sheetOne);
        addDepartmentsToListFromSheet(sheetTwo);
        try {
            allNicomDepartmensBook.close();
        } catch (IOException e) {
            System.out.println("Не может закрыть фаил. Возможно отсутствует/открыт/ и тд");
            throw new RuntimeException(e);
        }
    }

    private void addDepartmentsToListFromSheet(XSSFSheet sheet) {
        int rowStart = 2;
        int cellStart = 0;
        int rowEnd = sheet.getLastRowNum();
        for (; rowStart < rowEnd; rowStart++) {
            XSSFRow row = sheet.getRow(rowStart);
            XSSFCell cell = row.getCell(cellStart);
            String cellValue = cell.getStringCellValue();
            if (!cell.equals("")) {
                List<String> list = new ArrayList<>();
                String districtOfCity = row.getCell(cellStart).getStringCellValue();
                String department = row.getCell(cellStart + 1).getStringCellValue().trim();
                String metroStationTemp = row.getCell(cellStart + 2).getStringCellValue();
                String metroStation = metroStationTemp.substring(0, metroStationTemp.indexOf("-")).trim();
                String timeWorkDay = row.getCell(cellStart + 3).getStringCellValue();
                String timeWorkWeekend = row.getCell(cellStart + 4).getStringCellValue();
                String addressDepartment = row.getCell(cellStart + 5).getStringCellValue();
                String weyToDepartment = row.getCell(cellStart + 9).getStringCellValue();

                Department dep = new Department(districtOfCity, department, metroStation, timeWorkDay, timeWorkWeekend, addressDepartment, weyToDepartment);
                departmentMap.put(department, dep);
                departmentList.add(dep);
            }
        }
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public List<String> getDepartmentTickerList() {
        List<String> listTickersDepartments = getDepartmentList()
                .stream()
                .map(e -> e.getDepartment())
                .collect(Collectors.toList());
        return listTickersDepartments;
    }
    public Map<String,Department> getDepartmentMap(){
   return departmentMap;
    }
}
