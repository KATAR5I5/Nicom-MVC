package ru.markelov.security.FirstSecurityApp.models;

import javax.persistence.*;

@Entity
@Table(name = "departments_for_employee")
public class DepartmentsForEmployees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ticker")
    private String ticker;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "metro")
    private String metro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }
}
