package com.ifmo.isdb.strattanoakmant.repository;

import com.ifmo.isdb.strattanoakmant.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
