package ru.markelov.security.FirstSecurityApp.models;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotEmpty(message = "№ мастера не должен быть пустым")
//    @Size(min=3, max=3, message = "Номер мастера 3х значный, 1я цифра - мастерская (1-Мурановская, 2-Студеный, 3-Сокол)")
//    @Column(name = "numberemployee")
//    private String numberEmployee;
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<ClientsDB> clientsDBList;

//    @Size(min = 3, max = 3, message = "№ мастера - 3х значное число. 1я цифра - мастерская (1-Мурановская, 2-Студеный, 3-Сокол)")
    @Min(value = 100, message = "№ мастера - 3х значное число. 1я цифра - мастерская (1-Мурановская, 2-Студеный, 3-Сокол)")
    @Max(value = 399,message = "№ мастера - 3х значное число. 1я цифра - мастерская (1-Мурановская, 2-Студеный, 3-Сокол)")
    @NotEmpty(message = "№ мастера не должен быть пустым")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "path")
    private String path;

    @Column(name = "pathdep")
    private String pathdep;

    @Column(name = "employeename")
    private String employeeName;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "department")
    private String department;
    public String getEmployeeName() {
        return employeeName;
    }

    public List<ClientsDB> getClientsDBList() {
        return clientsDBList;
    }

    public void setClientsDBList(List<ClientsDB> clientsDBList) {
        this.clientsDBList = clientsDBList;
    }

    public void setEmployeeName(String numberEmployee) {
        this.employeeName = numberEmployee;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathdep() {
        return pathdep;
    }

    public void setPathdep(String pathdep) {
        this.pathdep = pathdep;
    }

    public Employee(String username) {
        this.username = username;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", path='" + path + '\'' +
                ", pathdep='" + pathdep + '\'' +
                '}';
    }
}
