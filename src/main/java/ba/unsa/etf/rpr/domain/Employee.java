package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Employee is a Java Bean that represents an entity that exists in the Data Base
 * @author Zerina Jasarspahic
 */

public class Employee implements Idable {
    private Integer id;
    private String username;
    private String password;

    public Employee (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Employee () {}

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
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


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(username, employee.username) && Objects.equals(password, employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
