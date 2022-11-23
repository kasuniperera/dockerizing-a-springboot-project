package com.ems.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.lms.domain.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long>{

}
