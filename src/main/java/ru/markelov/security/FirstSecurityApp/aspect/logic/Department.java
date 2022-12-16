package ru.markelov.security.FirstSecurityApp.aspect.logic;

public class Department {
    private String districtOfCity;
    private String department;
    private String metroStation;
    private String timeWorkDay;
    private String timeWorkWeekend;
    private String addressDepartment;
    private String weyToDepartment;

    public Department() {
    }

    public Department(String districtOfCity, String department, String metroStation, String timeWorkDay, String timeWorkWeekend, String addressDepartment, String weyToDepartment) {
        this.districtOfCity = districtOfCity;
        this.department = department;
        this.metroStation = metroStation;
        this.timeWorkDay = timeWorkDay;
        this.timeWorkWeekend = timeWorkWeekend;
        this.addressDepartment = addressDepartment;
        this.weyToDepartment = weyToDepartment;
    }

    public String getDistrictOfCity() {
        return districtOfCity;
    }

    public void setDistrictOfCity(String districtOfCity) {
        this.districtOfCity = districtOfCity;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public String getTimeWorkDay() {
        return timeWorkDay;
    }

    public void setTimeWorkDay(String timeWorkDay) {
        this.timeWorkDay = timeWorkDay;
    }

    public String getTimeWorkWeekend() {
        return timeWorkWeekend;
    }

    public void setTimeWorkWeekend(String timeWorkWeekend) {
        this.timeWorkWeekend = timeWorkWeekend;
    }

    public String getAddressDepartment() {
        return addressDepartment;
    }

    public void setAddressDepartment(String addressDepartment) {
        this.addressDepartment = addressDepartment;
    }

    public String getWeyToDepartment() {
        return weyToDepartment;
    }

    public void setWeyToDepartment(String weyToDepartment) {
        this.weyToDepartment = weyToDepartment;
    }

    @Override
    public String toString() {
        return "--------------------------------------\n" +
                "districtOfCity= " + districtOfCity + "\n" +
                "department= " + department + "\n" +
                "metroStation= " + metroStation + "\n" +
                "timeWorkDay= " + timeWorkDay + "\n" +
                "timeWorkWeekend= " + timeWorkWeekend + "\n" +
                "addressDepartment= " + addressDepartment + "\n" +
                "weyToDepartment= " + weyToDepartment + "\n";

    }
}
