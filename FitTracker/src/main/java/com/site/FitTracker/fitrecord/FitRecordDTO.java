package com.site.FitTracker.fitrecord;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter

public class FitRecordDTO {
	private MultipartFile fitStimeImage;
	private MultipartFile fitEtimeImage;
	private MultipartFile stepCountImage;
	private String fitStimeImageName;
	private long fitStimeImageSize;
	private String fitEtimeImageName;
	private long fitEtimeImageSize;
	private LocalDateTime fitStime;
	private LocalDateTime fitEtime;
	private String stepCountImageName;
	private long stepCountImageSize;
	private String stepCount;
	private String content;
	private LocalDateTime createDateTime;

}
