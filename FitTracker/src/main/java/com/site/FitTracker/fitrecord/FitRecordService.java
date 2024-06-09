package com.site.FitTracker.fitrecord;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FitRecordService {

	private final FitRecordRepository fitRecordRepository;

	@Value("${image.upload-dir}")
	private String uploadDir;

	public FitRecord saveFitRecord(FitRecordDTO fitRecordDTO) throws IOException {

		String fitStimeImageName = fitRecordDTO.getFitStimeImage().getOriginalFilename();
		String fitEtimeImageName = fitRecordDTO.getFitEtimeImage().getOriginalFilename();
		String stepCountImageName = fitRecordDTO.getStepCountImage().getOriginalFilename();

		String imagePath = uploadDir;

		// Save start image

		File fitStimeImage = new File(imagePath + fitStimeImageName);
		fitRecordDTO.getFitStimeImage().transferTo(fitStimeImage);

		// Save end image

		File fitEtimeImage = new File(imagePath + fitEtimeImageName);
		fitRecordDTO.getFitEtimeImage().transferTo(fitEtimeImage);

		// Save stepcount image

		File stepCountImage = new File(imagePath + stepCountImageName);
		fitRecordDTO.getStepCountImage().transferTo(stepCountImage);

		FitRecord fitrecord = new FitRecord();
		fitrecord.setImagePath(imagePath);
		fitrecord.setFitEtimeImageName(fitEtimeImageName);
		fitrecord.setFitEtimeImageSize(fitRecordDTO.getFitEtimeImage().getSize());

		fitrecord.setFitStimeImageName(fitStimeImageName);
		fitrecord.setFitStimeImageSize(fitRecordDTO.getFitStimeImage().getSize());

		fitrecord.setStepCountImageName(stepCountImageName);
		fitrecord.setStepCountImageSize(fitRecordDTO.getStepCountImage().getSize());

		fitrecord.setFitStime(fitRecordDTO.getFitStime());
		fitrecord.setFitEtime(fitRecordDTO.getFitEtime());
		fitrecord.setStepCount(fitRecordDTO.getStepCount());
		fitrecord.setContent(fitRecordDTO.getContent());
		fitrecord.setCreateDateTime(LocalDateTime.now());

		return fitRecordRepository.save(fitrecord);

	}

}
