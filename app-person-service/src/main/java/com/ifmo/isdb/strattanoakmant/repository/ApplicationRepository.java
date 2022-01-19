package com.ifmo.isdb.strattanoakmant.repository;

import com.ifmo.isdb.strattanoakmant.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
