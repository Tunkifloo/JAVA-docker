package com.springback.springemployees.service;

import com.springback.springemployees.model.Employee;

import java.util.List;

public interface EmployeeService {

    // Crear nuevo empleado
    Employee createEmployee(Employee employee);

    // Obtener todos los empleados
    List<Employee> getAllEmployees();

    // Obtener empleado por ID
    Employee getEmployeeById(Long id);

    // Actualizar empleado
    Employee updateEmployee(Long id, Employee employee);

    // Eliminar empleado (soft delete)
    void deleteEmployee(Long id);

    // Eliminar permanentemente
    void hardDeleteEmployee(Long id);

    // Buscar por departamento
    List<Employee> getEmployeesByDepartment(String department);

    // Buscar empleados activos
    List<Employee> getActiveEmployees();

    // Buscar por nombre
    List<Employee> searchEmployeesByName(String searchTerm);

    // Activar/Desactivar empleado
    Employee toggleEmployeeStatus(Long id);
}