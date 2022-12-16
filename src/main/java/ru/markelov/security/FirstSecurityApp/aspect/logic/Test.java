package ru.markelov.security.FirstSecurityApp.aspect.logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("src/my.properties");
        properties.load(fis);

//        Создаем МАР устройств и и клиентов из 1с документа
        CreateNicomObjects nicom = new CreateNicomObjects(Path.of(properties.getProperty("file1C")));
        Thread thread1 = new Thread(nicom);
        thread1.start();
        thread1.join();
//        создаем сообщение
        RepairMessage rm = new RepairMessage(Path.of(properties.getProperty("fileDepartment")));
        Map<Device, ClientNicom> mapDevice = nicom.getMapDevice();
        System.out.println(mapDevice);
        mapDevice.entrySet()
                .stream()
                .filter(e->e.getKey().getHasRepair())
                .forEach(e -> {
                    System.out.println(rm.getMessageComplete(e.getKey(), e.getValue()));
                });

    }
}