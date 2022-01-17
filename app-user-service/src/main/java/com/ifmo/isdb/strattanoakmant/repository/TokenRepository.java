package com.ifmo.isdb.strattanoakmant.repository;

import com.ifmo.isdb.strattanoakmant.model.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<JwtToken, String> {
}
