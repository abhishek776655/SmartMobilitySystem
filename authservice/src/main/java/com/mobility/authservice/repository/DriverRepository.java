package com.mobility.authservice.repository;

import com.mobility.authservice.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByUserId(Long userId);
    Optional<Driver> findByLicenceNumber(String licenseNumber);
}
