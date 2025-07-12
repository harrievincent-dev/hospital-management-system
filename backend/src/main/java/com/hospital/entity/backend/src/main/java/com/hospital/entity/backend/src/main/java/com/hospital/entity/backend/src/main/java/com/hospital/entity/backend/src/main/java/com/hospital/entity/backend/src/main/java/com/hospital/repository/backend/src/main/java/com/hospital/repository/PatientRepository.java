package com.hospital.repository;

import com.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByPatientId(String patientId);
    Optional<Patient> findByUserId(Long userId);
    
    @Query("SELECT p FROM Patient p WHERE " +
           "LOWER(p.user.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
           "LOWER(p.user.lastName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
           "LOWER(p.patientId) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Patient> searchPatients(@Param("name") String name);
    
    @Query("SELECT COUNT(p) FROM Patient p")
    Long countTotalPatients();
}
