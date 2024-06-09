package com.site.FitTracker.fitrecord;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
public class FitRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// 사진파일 저장 장소
	@Column
	private String imagePath;

	// 시작시간 인증사진 (fitStime)
	// 이미지파일 이름
	@Column
	private String fitStimeImageName;
	// 이미지파일 크기
	private long fitStimeImageSize;
	
	// 종료시간 인증사진 (fitEtime)
	// 이미지파일 이름
	@Column
	private String fitEtimeImageName;
	// 이미지파일 크기
	private long fitEtimeImageSize;

	// 시작시간 수동입력
	private LocalDateTime fitStime;
	private LocalDateTime fitEtime;
	// 종료시간 수동입력
	
	// 걸음수 인증사진 (stepCount)
	// 이미지파일 이름
	@Column
	private String stepCountImageName;
	// 이미지파일 크기
	private long stepCountImageSize;
	
	// 걸음수 수동입력 (stepCount)
	@Column
	private String stepCount;
	
	// 메모
	@Column
	private String content;
	
	// 작성일시
	private LocalDateTime createDateTime;
	
}
