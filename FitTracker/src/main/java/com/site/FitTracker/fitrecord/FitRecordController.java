package com.site.FitTracker.fitrecord;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/FitRecord")
@RequiredArgsConstructor
public class FitRecordController {

	private final FitRecordService fitRecordService;

	@PostMapping("/save")
	public ResponseEntity<FitRecord> saveFitRecord(@RequestParam("fitStimeImage") MultipartFile fitStimeImage,
			@RequestParam("fitEtimeImage") MultipartFile fitEtimeImage,
			@RequestParam("stepCountImage") MultipartFile stepCountImage, @RequestParam("fitStime") String fitStime,
			@RequestParam("fitEtime") String fitEtime, @RequestParam("stepCount") String stepCount,
			@RequestParam("content") String content) {
		
		FitRecordDTO fitRecordDTO = new FitRecordDTO();
		
		fitRecordDTO.setFitStimeImage(fitStimeImage);
		fitRecordDTO.setFitEtimeImage(fitEtimeImage);
		fitRecordDTO.setStepCountImage(stepCountImage);
		
		fitRecordDTO.setFitStime(LocalDateTime.parse(fitStime));
		fitRecordDTO.setFitStime(LocalDateTime.parse(fitEtime));
		
		fitRecordDTO.setStepCount(stepCount);
		fitRecordDTO.setContent(content);
		
		
		try {
			
			FitRecord savedFitRecord = fitRecordService.saveFitRecord(fitRecordDTO);
			return ResponseEntity.ok(savedFitRecord);
			
		} catch (IOException e) {
			return ResponseEntity.status(500).build();
		}
		

	}

}
