package ru.markelov.security.FirstSecurityApp.models;

import ru.markelov.security.FirstSecurityApp.aspect.logic.StatusMessage;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class ClientsDB implements Comparable<ClientsDB> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee owner;

    @Column(name = "department")
    private String department;

    @Column(name = "ticketNumber")
    private Integer ticketNumber;
    @Column(name = "name")
    private String firstName;
    @Column(name = "surname")
    private String secondName;
    @Column(name = "thirdname")
    private String thirdName;
    @Column(name = "device")
    private String device;
    @Column(name = "price")
    private Integer priceToRepair;
    @Column(name = "phoneNumberOne")
    private Long phoneNumberOne;
    @Column(name = "phoneNumberTwo")
    private Long phoneNumberTwo;
    @Column(name = "massage")
    private String massage;

    @Column(name = "datesend")
//    @Temporal(TemporalType.DATE)
    private LocalDate localDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusMessage statusMessage;

    public ClientsDB() {
    }

    public ClientsDB(String department, Integer ticketNumber, String firstName, String secondName, String thirdName, String device, Integer priceToRepair, Long phoneNumberOne, Long phoneNumberTwo, Employee owner) {
        this.department = department;
        this.ticketNumber = ticketNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.device = device;
        this.priceToRepair = priceToRepair;
        this.phoneNumberOne = phoneNumberOne;
        this.phoneNumberTwo = phoneNumberTwo;
        this.owner = owner;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public StatusMessage getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(StatusMessage statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Integer getPriceToRepair() {
        return priceToRepair;
    }

    public void setPriceToRepair(Integer priceToRepair) {
        this.priceToRepair = priceToRepair;
    }

    public Long getPhoneNumberOne() {
        return phoneNumberOne;
    }

    public void setPhoneNumberOne(Long phoneNumberOne) {
        this.phoneNumberOne = phoneNumberOne;
    }

    public Long getPhoneNumberTwo() {
        return phoneNumberTwo;
    }

    public void setPhoneNumberTwo(Long phoneNumberTwo) {
        this.phoneNumberTwo = phoneNumberTwo;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public int compareTo(ClientsDB o) {
        if (this.department.compareTo(o.getDepartment()) == 0) {
            return ticketNumber.compareTo(o.ticketNumber);
        }
        return this.department.compareTo(o.getDepartment());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientsDB clientsDB = (ClientsDB) o;
        return department.equals(clientsDB.department) && ticketNumber.equals(clientsDB.ticketNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, ticketNumber);
    }

    @Override
    public String toString() {
        return "ClientsDB{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", ticketNumber=" + ticketNumber +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", thirdName='" + thirdName + '\'' +
                ", device='" + device + '\'' +
                ", priceToRepair=" + priceToRepair +
                ", phoneNumberOne=" + phoneNumberOne +
                ", phoneNumberTwo=" + phoneNumberTwo +
                ", massage='" + massage + '\'' +
                ", localDate=" + localDate +
                ", statusMessage=" + statusMessage +
                '}';
    }
}
