package com.ifmo.isdb.strattanoakmant.repository;

import com.ifmo.isdb.strattanoakmant.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByLoginAndPassword(String login, String password);
}
