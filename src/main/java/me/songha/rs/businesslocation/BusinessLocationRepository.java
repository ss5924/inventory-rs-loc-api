package me.songha.rs.businesslocation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessLocationRepository extends JpaRepository<BusinessLocation, Long> {
    List<BusinessLocation> findByBusinessLocationAddressContaining(String businessLocationAddress);
}
