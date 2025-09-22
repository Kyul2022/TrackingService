package com.example.trackee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trackee.model.TrackingUpdates;
import com.example.trackee.repository.TrackingUpdateRepository;

@Service
public class TrackingUpdateService {

	@Autowired
	private TrackingUpdateRepository trackRepo;
	
	public TrackingUpdates save (TrackingUpdates T) {
		return trackRepo.save(T);
	}
	
	public TrackingUpdates findByLatestDriver (String driver) {
		return trackRepo.findTopByDriverOrderByUpdatedAtDesc(driver);
	}
}
