package com.site.FitTracker.fitrecord;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FitRecordService {

	private final FitRecordRepository fitRecordRepository;

	// @Value("${image.upload-dir}")
	private String uploadDir = "C:/workspace/";

	public FitRecord saveFitRecord(FitRecordDTO fitRecordDTO) throws IOException {

		// 이미지 입력 체크
		boolean fitStimeImageCheck = false;
		boolean fitEtimeImageCheck = false;
		boolean stepCountImageCheck = false;

		if (!fitRecordDTO.getFitStimeImage().isEmpty()) {
			fitStimeImageCheck = true;
		}
		if (!fitRecordDTO.getFitEtimeImage().isEmpty()) {
			fitEtimeImageCheck = true;
		}
		if (!fitRecordDTO.getStepCountImage().isEmpty()) {
			stepCountImageCheck = true;
		}

		// 엔티티 선언
		FitRecord fitrecord = new FitRecord();

		// 이미지 저장경로
		String imagePath = uploadDir;

		// 현재 날짜를 가져와서 yyyymmdd형식으로 변환
		LocalDateTime now = LocalDateTime.now();
		String today = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		imagePath = uploadDir + File.separator + today + File.separator;

		// 오늘날짜의폴더가 없는경우 생성하기
		File directory = new File(imagePath);
		if (!directory.exists()) {
			directory.mkdirs(); // 폴더 생성
		}

		// 현재 시분초를 가져와서 파일 이름에 추가하기 위한 포맷
		String timeStamp = now.format(DateTimeFormatter.ofPattern("HHmmss"));

		// Save start image
		if (fitStimeImageCheck) {
			String fitStimeImageName = timeStamp + "_" + fitRecordDTO.getFitStimeImage().getOriginalFilename();
			File fitStimeImage = new File(imagePath + fitStimeImageName);
			fitRecordDTO.getFitStimeImage().transferTo(fitStimeImage);
			fitrecord.setFitStimeImageName(fitStimeImageName);
			fitrecord.setFitStimeImageSize(fitRecordDTO.getFitStimeImage().getSize());

		}

		// Save end image
		if (fitEtimeImageCheck) {
			String fitEtimeImageName = timeStamp + "_" + fitRecordDTO.getFitEtimeImage().getOriginalFilename();
			File fitEtimeImage = new File(imagePath + fitEtimeImageName);
			fitRecordDTO.getFitEtimeImage().transferTo(fitEtimeImage);
			fitrecord.setFitEtimeImageName(fitEtimeImageName);
			fitrecord.setFitEtimeImageSize(fitRecordDTO.getFitEtimeImage().getSize());

		}

		// Save stepcount image
		if (stepCountImageCheck) {
			String stepCountImageName = timeStamp + "_" + fitRecordDTO.getStepCountImage().getOriginalFilename();
			File stepCountImage = new File(imagePath + stepCountImageName);
			fitRecordDTO.getStepCountImage().transferTo(stepCountImage);
			fitrecord.setStepCountImageName(stepCountImageName);
			fitrecord.setStepCountImageSize(fitRecordDTO.getStepCountImage().getSize());
		}

		if (!(fitRecordDTO.getFitStime() == null)) {
			fitrecord.setFitStime(fitRecordDTO.getFitStime());
		}
		if (!(fitRecordDTO.getFitEtime() == null)) {
			fitrecord.setFitEtime(fitRecordDTO.getFitEtime());
		}

		fitrecord.setImagePath(imagePath);
		fitrecord.setStepCount(fitRecordDTO.getStepCount());
		fitrecord.setContent(fitRecordDTO.getContent());
		fitrecord.setCreateDateTime(LocalDateTime.now());

		return fitRecordRepository.save(fitrecord);

	}

}
