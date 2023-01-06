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

public class CreateNicomObjects implements Runnable {
    private String fullName, phoneNumberOne, device, model, fullTicketNumber, deliveryDate, fullPrice, price, departmentHasDevice;

    private List<ClientNicom> clientsNicom;
    private List<Device> devices;
    private Map<Device, ClientNicom> mapDevice;
    private Path path;
    private boolean listFullDevice;

    public CreateNicomObjects(Path path) throws IOException {
        this.path = path;
    }

    @Override
    public void run() {
        try {
            create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ClientNicom> getClientsNicom() {
        return clientsNicom;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public Map<Device, ClientNicom> getMapDevice() {
        return mapDevice;
    }


    void create() throws IOException {
        clientsNicom = new ArrayList<>();
        devices = new ArrayList<>();
        mapDevice = new HashMap<>();
        XSSFWorkbook myExcelBook = new XSSFWorkbook(Files.newInputStream(path));
//        System.out.println(myExcelBook.getSheetName(0));
        XSSFSheet myExcelSheet = myExcelBook.getSheet(myExcelBook.getSheetName(0));
        //      A B C D I F G H I J  K  L  M  N
//      0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
//      Первая ячейка (0,0) - (rowSTR, columnStart)
//           r - с 8 строки, 6 ячейка
        int rowStart = 7;
        int rowEnd = myExcelSheet.getLastRowNum() - 1;
        int columnStart = 0;
        int columnTicket = 7;

            XSSFRow rowTemp = myExcelSheet.getRow(rowStart);
            XSSFCell cell = rowTemp.getCell(columnTicket);
            try {
                cell.getStringCellValue();
            } catch (IllegalStateException e){
                columnTicket = 6;
            }
//        System.out.println("Проверь стартовые и конечный ячейки by default: rowStart =" + rowStart + " rowEnd = " + rowEnd);
        for (; rowStart < rowEnd; rowStart++) {
            XSSFRow row = myExcelSheet.getRow(rowStart);
            String cellStart = String.valueOf(row.getCell(columnTicket));
//            if (cellStart == null) {
//                columnTicket = 6;
//            }

            if (cellStart.equals("") || cellStart == null || cellStart.isEmpty() || cellStart.equals("#NULL!")) {
            } else {
//                Device cell
                departmentHasDevice = String.valueOf(row.getCell(columnStart));
                fullTicketNumber = String.valueOf(row.getCell(columnTicket)); //H
                model = String.valueOf(row.getCell(columnStart + 8));
                device = String.valueOf(row.getCell(columnStart + 9));
                fullPrice = String.valueOf(row.getCell(columnStart + 12));
                price = String.valueOf(row.getCell(columnStart + 13));
                deliveryDate = String.valueOf(row.getCell(columnStart + 5));

//                Client Cell
                fullName = String.valueOf(row.getCell(columnStart + 10));
                phoneNumberOne = String.valueOf(row.getCell(columnStart + 11));

//              Create Nicom Object
                Device tempDevice = new Device(model, device, fullTicketNumber, fullPrice, price, deliveryDate, departmentHasDevice);
                ClientNicom tempClientNicom = new ClientNicom(fullName, phoneNumberOne);

                mapDevice.put(tempDevice, tempClientNicom);
                devices.add(tempDevice);
                clientsNicom.add(tempClientNicom);
            }
        }
        myExcelBook.close();
    }
}