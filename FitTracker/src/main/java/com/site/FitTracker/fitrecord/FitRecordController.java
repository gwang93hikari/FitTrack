package com.site.FitTracker.fitrecord;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/FitRecord")
@RequiredArgsConstructor
public class FitRecordController {

	private final FitRecordService fitRecordService;

	@PostMapping("/save")
	public String saveFitRecord(@RequestParam("fitStimeImage") MultipartFile fitStimeImage,
			@RequestParam("fitEtimeImage") MultipartFile fitEtimeImage,
			@RequestParam("stepCountImage") MultipartFile stepCountImage, @RequestParam("fitStime") String fitStime,
			@RequestParam("fitEtime") String fitEtime, @RequestParam("stepCount") String stepCount,
			@RequestParam("content") String content, RedirectAttributes redirectAttributes) {

		FitRecordDTO fitRecordDTO = new FitRecordDTO();

		fitRecordDTO.setFitStimeImage(fitStimeImage);
		fitRecordDTO.setFitEtimeImage(fitEtimeImage);
		fitRecordDTO.setStepCountImage(stepCountImage);

		if (!fitStime.isBlank()) {

			fitRecordDTO.setFitStime(LocalDateTime.parse(fitStime));
		}
		if (!fitEtime.isBlank()) {

			fitRecordDTO.setFitEtime(LocalDateTime.parse(fitEtime));

		}

		fitRecordDTO.setStepCount(stepCount);
		fitRecordDTO.setContent(content);

		try {
			FitRecord savedFitRecord = fitRecordService.saveFitRecord(fitRecordDTO);
			if (savedFitRecord != null) {
				redirectAttributes.addFlashAttribute("message", "등록이 완료되었습니다.");
				return "redirect:/";
			} else {
				redirectAttributes.addFlashAttribute("message", "등록에 실패하였습니다.");
				return "redirect:/error";
			}
		} catch (IOException e) {
			System.out.println("ERROR");
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("message", "오류가 발생하였습니다.");
			return "redirect:/error";
		}

	}

}
