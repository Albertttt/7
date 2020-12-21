package com.example.seven.service;

import com.example.seven.entity.Employee;
import com.example.seven.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeServiceTest {

    @TestConfiguration
    static class EmployeeServiceTestConfig {
        @Bean
        public EmployeeService employeeServiceTest() {
            return new EmployeeService();
        }
    }

    @Autowired
    public EmployeeService service;

    @Test
    public void add() {
        Employee newEmployee = addDefault();

        Assert.assertNotEquals(newEmployee.getId(), Long.valueOf(0));
        Assert.assertEquals(newEmployee.getFirstName(), "test");
        Assert.assertEquals(newEmployee.getPassword(), "test");

    }

    @Test
    public void getAll() {
        addDefault();
        Assert.assertTrue(service.getAllEmployees().size() > 0);
    }

    @Test
    public void getById() throws NotFoundException {
        Employee newEmployee = addDefault();

        Employee employee = service.getEmployeeById(newEmployee.getId());
        Assert.assertEquals(employee.getId(), newEmployee.getId());
        Assert.assertEquals(employee.getEmail(), "test");
        Assert.assertEquals(employee.getLastName(), "test");
    }

    @Test
    public void update() throws NotFoundException {
        Employee newEmployee = addDefault();

        Employee employee = new Employee();
        employee.setFirstName("test");
        employee.setUserName("test");

        Employee changed = service.updateEmployee(employee, newEmployee.getId());
        Assert.assertEquals(changed.getId(), newEmployee.getId());
        Assert.assertEquals(changed.getFirstName(), "test");
        Assert.assertEquals(changed.getUserName(), "test");
    }

    @Test(expected = NotFoundException.class)
    public void deleteById() {
        Employee newEmployee = addDefault();

        service.deleteEmployee(newEmployee.getId());
        service.get(newEmployee.getId());
    }

    public Employee addDefault() {
        Employee employee = new Employee();
        employee.setPassword("test");
        employee.setEmail("test");
        employee.setLastName("test");
        employee.setFirstName("test");
        employee.setUserName("test");

        return service.createOrSaveEmployee(employee);
    }
}
