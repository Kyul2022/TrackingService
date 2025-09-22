package com.example.trackee.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trackee.dto.TrackingRequest;
import com.example.trackee.dto.TrackingResponse;
import com.example.trackee.model.TrackingUpdates;
import com.example.trackee.service.TrackingUpdateService;

@RestController
@RequestMapping("/api/tracking")
public class TrackingUpdateController {

	@Autowired
	private TrackingUpdateService trackService;
	
	@PostMapping
    public ResponseEntity<?> getTrackingInfos(@RequestBody TrackingRequest request) {
		try {
		    TrackingUpdates T = trackService.findByLatestDriver(request.getDriver());
		    TrackingResponse response = new TrackingResponse();
		    
		    if(T == null) {
		        response.setCity(request.getDepartureAgency());
		        response.setDriver(request.getDriver()); // Use request.getDriver() instead of T.getDriver()
		        return ResponseEntity.ok(response);
		    }
		    
		    response.setCity(T.getCity());
		    response.setDriver(T.getDriver());
		    response.setLat(T.getLat());
		    response.setLon(T.getLon());

		    return ResponseEntity.ok(response);
		} catch(Exception E) {
		    return ResponseEntity
		        .badRequest()
		        .body(Map.of("error", E.getMessage()));
		}
	}
	
	@PostMapping("/setInfos")
    public ResponseEntity<?> setTrackingInfos(@AuthenticationPrincipal Jwt jwt, @RequestBody TrackingUpdates update) {
		try {
            String email = jwt.getClaimAsString("email");
            System.out.println("Hey : "+email);
        	String EnterpriseId = jwt.getClaimAsString("family_name");
            update.setDriver(email);
            update.setEnterpriseId(EnterpriseId);
			update.setUpdatedAt(LocalDateTime.now());
			TrackingUpdates T = trackService.save(update);
            return ResponseEntity.ok(T);
		} catch(Exception E) {
            System.out.println("Hey : "+E.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", E.getMessage()));		
            }
	}
	
	
}
