package com.springback.springemployees.controller;

import com.springback.springemployees.model.Employee;
import com.springback.springemployees.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    // GET - Obtener todos los empleados
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // GET - Obtener empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    // POST - Crear nuevo empleado
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    // PUT - Actualizar empleado
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    // DELETE - Eliminar empleado (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee deactivated successfully");
        response.put("id", id.toString());
        return ResponseEntity.ok(response);
    }

    // DELETE - Eliminar permanentemente
    @DeleteMapping("/{id}/permanent")
    public ResponseEntity<Map<String, String>> hardDeleteEmployee(@PathVariable Long id) {
        employeeService.hardDeleteEmployee(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee deleted permanently");
        response.put("id", id.toString());
        return ResponseEntity.ok(response);
    }

    // GET - Empleados por departamento
    @GetMapping("/department/{department}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable String department) {
        List<Employee> employees = employeeService.getEmployeesByDepartment(department);
        return ResponseEntity.ok(employees);
    }

    // GET - Empleados activos
    @GetMapping("/active")
    public ResponseEntity<List<Employee>> getActiveEmployees() {
        List<Employee> employees = employeeService.getActiveEmployees();
        return ResponseEntity.ok(employees);
    }

    // GET - Buscar por nombre
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestParam String term) {
        List<Employee> employees = employeeService.searchEmployeesByName(term);
        return ResponseEntity.ok(employees);
    }

    // PATCH - Activar/Desactivar empleado
    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<Employee> toggleEmployeeStatus(@PathVariable Long id) {
        Employee employee = employeeService.toggleEmployeeStatus(id);
        return ResponseEntity.ok(employee);
    }
}