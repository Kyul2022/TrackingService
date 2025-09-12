package com.example.trackee.dto;

import lombok.Data;

@Data
public class TrackingResponse {

	private String driver;
	private float lat;
	private float lon;
	private String city;
}
