package ru.markelov.security.FirstSecurityApp.aspect.logic;

public class Device implements Comparable<Device> {
    private String model;
    private String device;
    private String department;
    private String departmentHasDevice;
    private boolean hasRepair;
    boolean isCash;
    boolean isPayment;
    private Double fullpriceToRepair;
    private Double priceToRepair;
    private String fullTicketNumber;
    private Integer ticketNumber;

    public Device(String model, String device, String fullTicketNumber, String fullPrice,
                  String price, String deliveryDate, String departmentHasDevice) {
        this.model = model.trim();
        this.device = normalizeDevice(device);
        this.fullTicketNumber = normalizeNumberTicket(fullTicketNumber);
        this.fullpriceToRepair = normalizeFullPrice(fullPrice);
        this.priceToRepair = normalizeFullPrice(price);

//        this.departmentType = Departments.valueOf(getDepartment().trim());
//        normalizDate(deliveryDate);
        this.departmentHasDevice = departmentHasDevice(departmentHasDevice);
    }
    private double normalizeFullPrice(String fullPrice){
        Double tempPrice;
        if (fullPrice == null || fullPrice.isEmpty() || fullPrice.equals("#NULL!")) {
            hasRepair = false;
            tempPrice = 0d;
        } else {
            hasRepair = true;
            try {
                tempPrice = Double.parseDouble(fullPrice);
            } catch (NumberFormatException e) {
                System.out.println("Ячейка <Сумма> или <Сумма доплаты> содержит НЕ ЧИСЛО");
                System.out.println(this);
                throw new RuntimeException(e);
            }
        }
        return tempPrice;
    }
    public boolean getHasRepair() {
        return hasRepair;
    }

    public String getFullTicketNumber() {
        return fullTicketNumber;
    }


    public String getModel() {
        return model;

    }

    public String getDevice() {
        return device;
    }

    public String getDepartment() {
        return department;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public Double getFullpriceToRepair() {
        return fullpriceToRepair;
    }

    public Integer getPriceToRepair() {
        return (int)(double)priceToRepair;
    }

    public String getDepartmentHasDevice() {
        return departmentHasDevice;
    }
    private String normalizeDevice(String deviceName){
        if (deviceName.contains("неопределенная техника")) {
            return  "Неопределенная техника";
        } else {
            return deviceName.trim();
        }

    }

   private String departmentHasDevice(String departmentHasDevice) {
        String[] temp = departmentHasDevice.split(" ");
        if (temp[0].equals("Хранение"))
            return temp[1];
        return temp[0];
    }

    private String normalizeNumberTicket(String numberTicket) {
        String ticket= numberTicket.trim();
        if(ticket.contains("-")) {
            String[] departmentWithNumber = ticket.split("-");
            department = departmentWithNumber[0].trim().toUpperCase();
            ticketNumber = Integer.parseInt(departmentWithNumber[1].trim());
        }else {
            department = ticket.substring(0,2);
            ticketNumber = 0;
        }
        return ticket;
    }

    void normalizDate(String date) {
//        LocalDate d = LocalDate.parse(date);

    }

    @Override
    public int compareTo(Device o) {
        if (this.department.compareTo(o.getDepartment()) == 0) {
            return ticketNumber.compareTo(o.ticketNumber);
        }
        return this.department.compareTo(o.getDepartment());
    }

    @Override
    public String toString() {
        return ", , , , , , , , , , , , , , , , , , , , , , ,\n" +
                "model= " + model + "\n" +
                "device= " + device + "\n" +
                "department= " + department + "\n" +
                "ticketNumber= " + department + "-" + ticketNumber + "\n" +
                "priceToRepair= " + priceToRepair + "\n";
//                "devicetype= " + type + "\n";
    }
}
