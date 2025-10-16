package com.springback.springemployees.repository;

import com.springback.springemployees.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Buscar empleados por departamento
    List<Employee> findByDepartment(String department);

    // Buscar empleados activos
    List<Employee> findByIsActive(Boolean isActive);

    // Buscar por email
    Optional<Employee> findByEmail(String email);

    // Buscar por nombre o apellido (case insensitive)
    @Query("SELECT e FROM Employee e WHERE " +
            "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Employee> searchByName(@Param("searchTerm") String searchTerm);

    // Contar empleados por departamento
    Long countByDepartment(String department);
}