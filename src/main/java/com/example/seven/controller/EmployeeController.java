package com.example.seven.controller;

import com.example.seven.entity.Employee;
import com.example.seven.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @PostMapping
    Employee createOrSaveEmployee(@RequestBody Employee newEmployee) {
        return service.createOrSaveEmployee(newEmployee);
    }

    @GetMapping("{id}")
    Employee getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PutMapping("{id}")
    Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return service.updateEmployee(newEmployee, id);
    }

    @DeleteMapping("{id}")
    void deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
    }
}