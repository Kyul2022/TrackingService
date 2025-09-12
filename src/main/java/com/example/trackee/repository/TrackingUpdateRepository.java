package com.example.trackee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trackee.model.TrackingUpdates;

@Repository
public interface TrackingUpdateRepository extends JpaRepository<TrackingUpdates, Integer> {

	TrackingUpdates findTopByDriverAndEnterpriseIdOrderByUpdatedAtDesc(String driver, String enterpriseId);
	}
