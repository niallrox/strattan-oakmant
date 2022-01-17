package com.ifmo.isdb.strattanoakmant.repository;

import com.ifmo.isdb.strattanoakmant.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query(value = "SELECT type FROM position AS p " +
            "INNER JOIN position_employer AS pe ON p.id = pe.id " +
            "WHERE pe.employer_id = :employerId ", nativeQuery = true)
    String findRoleById(@Param("employerId") Long employerId);

    boolean existsByType(String type);
}
