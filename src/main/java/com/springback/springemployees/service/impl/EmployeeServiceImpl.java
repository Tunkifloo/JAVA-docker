package com.springback.springemployees.service.impl;

import com.springback.springemployees.exception.ResourceNotFoundException;
import com.springback.springemployees.model.Employee;
import com.springback.springemployees.repository.EmployeeRepository;
import com.springback.springemployees.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhone(employeeDetails.getPhone());
        employee.setPosition(employeeDetails.getPosition());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setHireDate(employeeDetails.getHireDate());
        employee.setSalary(employeeDetails.getSalary());
        employee.setIsActive(employeeDetails.getIsActive());

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employee.setIsActive(false);
        employeeRepository.save(employee);
    }

    @Override
    public void hardDeleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getActiveEmployees() {
        return employeeRepository.findByIsActive(true);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> searchEmployeesByName(String searchTerm) {
        return employeeRepository.searchByName(searchTerm);
    }

    @Override
    public Employee toggleEmployeeStatus(Long id) {
        Employee employee = getEmployeeById(id);
        employee.setIsActive(!employee.getIsActive());
        return employeeRepository.save(employee);
    }
}