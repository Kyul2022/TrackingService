package com.example.trackee.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tracking_updates", 
indexes = {
    @Index(name = "idx_driver_enterprise_updated", columnList = "driver, enterpriseId, updatedAt")
})
public class TrackingUpdates {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String driver;
	
	private String enterpriseId;
	
	private float lat;
	
	private float lon;
	
	private String city;
	
	private LocalDateTime updatedAt;
}
