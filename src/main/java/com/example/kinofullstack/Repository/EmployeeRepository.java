package com.example.kinofullstack.Repository;

import com.example.kinofullstack.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> { }
