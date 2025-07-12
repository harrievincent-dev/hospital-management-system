package com.hospital.repository;

import com.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByDoctorId(String doctorId);
    Optional<Doctor> findByUserId(Long userId);
    List<Doctor> findBySpecialization(String specialization);
    List<Doctor> findByDepartment(String department);
    List<Doctor> findByStatus(Doctor.DoctorStatus status);
    
    @Query("SELECT d FROM Doctor d WHERE " +
           "LOWER(d.user.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
           "LOWER(d.user.lastName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
           "LOWER(d.specialization) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Doctor> searchDoctors(@Param("name") String name);
    
    @Query("SELECT DISTINCT d.specialization FROM Doctor d ORDER BY d.specialization")
    List<String> findAllSpecializations();
    
    @Query("SELECT COUNT(d) FROM Doctor d WHERE d.status = 'AVAILABLE'")
    Long countAvailableDoctors();
}
