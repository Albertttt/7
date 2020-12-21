package com.example.seven.controller;

import com.example.seven.entity.Employee;
import com.example.seven.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService service;

    @Test
    public void add() throws Exception {
        Employee employee = new Employee();
        employee.setPassword("test");
        employee.setEmail("test");
        employee.setLastName("test");
        employee.setFirstName("test");
        employee.setUserName("test");

        mockMvc.perform(post("/employees")
                .content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAll() throws Exception {
        Employee employee = new Employee();
        employee.setPassword("test");
        employee.setEmail("test");
        employee.setLastName("test");
        employee.setFirstName("test");
        employee.setUserName("test");

        Mockito.when(service.getAllEmployees()).thenReturn(Collections.singletonList(employee));
        mockMvc.perform(
                get("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("test"))
                .andExpect(jsonPath("$[0].email").value("test"))
        ;
    }

    @Test
    public void edit() throws Exception {
        Employee employee = new Employee();
        employee.setPassword("test");
        employee.setEmail("test");
        employee.setLastName("test");
        employee.setFirstName("test");
        employee.setUserName("test");

        Mockito.when(service.updateEmployee(Mockito.any(), Mockito.anyLong())).thenReturn(employee);
        mockMvc.perform(put("/employees/1")
                .content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("userName").value("test"));
    }
}
