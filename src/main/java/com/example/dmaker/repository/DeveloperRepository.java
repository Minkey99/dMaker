package com.example.dmaker.repository;

import com.example.dmaker.code.StatusCode;
import com.example.dmaker.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/* Entity class, PK Type */
@Repository
public interface DeveloperRepository
        extends JpaRepository<Developer, Long> {
    Optional<Developer> findByMemberId(String memberId);
    List<Developer> findDevelopersByStatusCodeEquals(StatusCode statusCode);
}

