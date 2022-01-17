package com.service;

import com.model.Employee;
import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<Employee> getAll();
    void add(Employee employee);
    void delete(long id);
    Optional<Employee> findId(long id);
}
