package ru.markelov.security.FirstSecurityApp.aspect.logic;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class RepairMessage {
    private List<String> actualDepartmentTickerList;
    private List<Department> departmentList;
    private Map<String, Department> departmentMap;

    public RepairMessage(Path pathDepartments) {
        CreateDepartmens createDepartmens = new CreateDepartmens(pathDepartments);
        departmentList = createDepartmens.getDepartmentList();
        departmentMap = createDepartmens.getDepartmentMap();
        actualDepartmentTickerList = createDepartmens.getDepartmentTickerList();
    }

    public String getMessageComplete(Device device, ClientNicom client) {
        String name = client.getFirstName();
        if (name == null || name.isEmpty()) {
            name = "";
        } else name += ", ";
        if (device.getHasRepair()) {
            return getMessageWithRepair(device, client, name);
        } else {
            return getMessageWithOutRepair(device, client, name);
        }
    }

    private String getMessageWithRepair(Device device, ClientNicom client, String name) {
        String deviceName = device.getDevice();
        StringBuilder stringBuilderMessage = new StringBuilder();


        if (device.getDevice().equals("Неопределенная техника")) {
            deviceName = "";
        }
        if (actualDepartmentTickerList.contains(device.getDepartment())) {
            stringBuilderMessage
                    .append("Квитанция №: ")
                    .append(device.getFullTicketNumber())
                    .append("\n День добрый! Мы из Nicom-сервиса. ")
                    .append(name)
                    .append("Ваш аппарат - ")
                    .append(deviceName)
                    .append(" - ГОТОВ. Оплатить при получении необходимо НАЛИЧНЫМИ - ")
                    .append(device.getPriceToRepair())
                    .append("руб.\n Аппарат находится по адресу: метро - ")
                    .append(departmentMap.get(device.getDepartment()).getMetroStation())
                    .append(". ")
                    .append(departmentMap.get(device.getDepartment()).getAddressDepartment())
                    .append(". Время работы в будни - ")
                    .append(departmentMap.get(device.getDepartment()).getTimeWorkDay())
                    .append(". В выходные - ")
                    .append(departmentMap.get(device.getDepartment()).getTimeWorkWeekend())
                    .append(".\n Устройство выдается СТРОГО по ОРИГИНАЛУ квитанции или по ПАСПОРТУ.");
            return stringBuilderMessage.toString();

        } else if (device.getDepartment().equals("Б4")) {
            stringBuilderMessage
                    .append("Квитанция №: ")
                    .append(device.getFullTicketNumber())
                    .append("\n День добрый! Мы из Сервис-центра. ")
                    .append(name)
                    .append(" Ваш аппарат - ")
                    .append(deviceName)
                    .append(" - ГОТОВ. Оплатить при получении  необходимо - ")
                    .append(device.getPriceToRepair())
                    .append("руб.\n Аппарат находится по адресу: г. Мытищи, ул.Борисовка, д.4. Время работы в будни - 10-20ч. В выходные - 10-18ч.")
                    .append("\n Устройство выдается СТРОГО по ОРИГИНАЛУ квитанции или по ПАСПОРТУ человека сдававшего в ремонт. ");

            return stringBuilderMessage.toString();
        }
        return stringBuilderMessage
                .append("Квитанция №: ")
                .append(device.getFullTicketNumber())
                .append("\n День добрый! Мы из Nicom-сервиса. ")
                .append(name)
                .append(" Ваш аппарат - ")
                .append(deviceName)
                .append(" - ГОТОВ. Оплатить при получении  необходимо НАЛИЧНЫМИ - ")
                .append(device.getPriceToRepair())
                .append("руб.\n О МЕСТОНАХОЖДЕНИИ аппарата уточните по тел. 8495-545-06-08.")
                .append("\n Устройство выдается СТРОГО по ОРИГИНАЛУ квитанции или по ПАСПОРТУ человека сдававшего в ремонт. ")
                .toString();
    }


    private String getMessageWithOutRepair(Device device, ClientNicom client, String name) {
        String deviceName = device.getDevice();
        StringBuilder stringBuilderMessage = new StringBuilder();
        if (device.getDevice().equals("Неопределенная техника")) {
            deviceName = "";
        }
        if (actualDepartmentTickerList.contains(device.getDepartment())) {
            stringBuilderMessage
                    .append("Квитанция №: ")
                    .append(device.getFullTicketNumber())
                    .append("\n День добрый! Мы из Nicom-сервиса. ")
                    .append(name)
                    .append(" Ваш аппарат - ")
                    .append(deviceName)
                    .append(" - БЕЗ РЕМОНТА. В данный момент находится на пункте выдачи по адресу: метро - ")
                    .append(departmentMap.get(device.getDepartment()).getMetroStation())
                    .append(". ")
                    .append(departmentMap.get(device.getDepartment()).getAddressDepartment())
                    .append(". Время работы в будни - ")
                    .append(departmentMap.get(device.getDepartment()).getTimeWorkDay())
                    .append(". В выходные - ")
                    .append(departmentMap.get(device.getDepartment()).getTimeWorkWeekend())
                    .append(".\n Устройство выдается СТРОГО по ОРИГИНАЛУ квитанции или по ПАСПОРТУ человека сдававшего в ремонт. ")
            ;


            return stringBuilderMessage.toString();
        } else if (device.getDepartment().toUpperCase().equals("Б4")) {
            stringBuilderMessage
                    .append("Квитанция №: ")
                    .append(device.getFullTicketNumber())
                    .append("\n День добрый! Мы из Сервис-центра. ")
                    .append(name)
                    .append(" Ваш аппарат - ")
                    .append(deviceName)
                    .append(" - БЕЗ РЕМОНТА. В данный момент находится по адресу: г. Мытищи, ул.Борисовка, д.4. Время работы в будни - 10-20ч. В выходные - 10-18ч.")
                    .append("\n Устройство выдается СТРОГО по ОРИГИНАЛУ квитанции или по ПАСПОРТУ человека сдававшего в ремонт. ")
            ;

            return stringBuilderMessage.toString();
        }
        return stringBuilderMessage
                .append("Квитанция №: ")
                .append(device.getFullTicketNumber())
                .append("\n День добрый! Мы из Nicom-сервиса. ")
                .append(name)
                .append("Ваш аппарат - ")
                .append(deviceName)
                .append(" - БЕЗ РЕМОНТА. О МЕСТОНАХОЖДЕНИИ аппарата уточните по тел. 8495-545-06-08.")
                .append("\n Устройство выдается СТРОГО по ОРИГИНАЛУ квитанции или по ПАСПОРТУ человека сдававшего в ремонт. ")
                .toString();
    }


    public String getMessageWeyToDepartment(Device device) {
        return departmentMap.get(device.getDepartment()).getWeyToDepartment();
    }
}
