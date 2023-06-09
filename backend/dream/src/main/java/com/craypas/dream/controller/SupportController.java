package com.craypas.dream.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.craypas.dream.model.dto.support.RequestDto;
import com.craypas.dream.model.service.SupportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dream/support")
public class SupportController {
	private final SupportService supportService;

	// 꿈 후원요청 작성
	@PostMapping("/req")
	public ResponseEntity<?> createSupport(@Valid @RequestBody final RequestDto.Create requestDto) {
		return new ResponseEntity<>(supportService.createSupport(requestDto), HttpStatus.CREATED);
	}

	// 꿈 후원요청 단일 조회
	@GetMapping("/req/{sid}")
	public ResponseEntity<?> getSupport(@PathVariable final Long sid) {
		return new ResponseEntity<>(supportService.getSupport(sid), HttpStatus.OK);
	}

	// 꿈 후원요청 전체 조회
	@GetMapping("/req")
	public ResponseEntity<?> getSupportList(@RequestParam("sortType") final Integer sortType) {
		return new ResponseEntity<>(supportService.getSupportList(sortType), HttpStatus.OK);
	}

	// 꿈 후원요청 수정시 필요한 정보 조회
	@GetMapping("/req/update/{sid}")
	public ResponseEntity<?> getSupportForUpdate(@PathVariable final Long sid) {
		return new ResponseEntity<>(supportService.getSupportForUpdate(sid), HttpStatus.OK);
	}

	// 꿈 후원요청 수정
	@PutMapping("/req/{sid}")
	public ResponseEntity<?> updateSupport(@PathVariable final Long sid,
		@Valid @RequestBody final RequestDto.Create requestDto) {
		return new ResponseEntity<>(supportService.updateSupport(sid, requestDto), HttpStatus.OK);
	}

	// 꿈 후원요청 삭제
	@DeleteMapping("/req/{sid}")
	public ResponseEntity<?> deleteSupport(@PathVariable final Long sid) {
		supportService.deleteSupport(sid);
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}

	// 꿈 후원요청 검색
	@GetMapping("/req/search")
	public ResponseEntity<?> searchSupport(@RequestParam("keyword") final String keyword) {
		return new ResponseEntity<>(supportService.searchSupport(keyword), HttpStatus.OK);
	}

	// 꿈 후원하기
	@PostMapping("/res")
	public ResponseEntity<?> createSupportUser(@RequestParam("sid") final Long sid, @RequestParam("uid") final Long uid,
		@RequestParam("point") final Integer point) {
		return new ResponseEntity<>(supportService.createSupportUser(sid, uid, point), HttpStatus.CREATED);
	}

	// 꿈 후원 취소하기
	@DeleteMapping("/res")
	public ResponseEntity<?> deleteSupportUser(@RequestParam("sid") final Long sid,
		@RequestParam("uid") final Long uid) {
		return new ResponseEntity<>(supportService.deleteSupportUser(sid, uid), HttpStatus.OK);
	}

	// 후원했던 요청 목록 조회
	@GetMapping("/res/{uid}")
	public ResponseEntity<?> getUserSupportList(@PathVariable final Long uid) {
		return new ResponseEntity<>(supportService.getUserSupportList(uid), HttpStatus.OK);
	}

	// 작성한 후원요청 목록 조회
	@GetMapping("/req/user/{uid}")
	public ResponseEntity<?> getMySupportList(@PathVariable final Long uid) {
		return new ResponseEntity<>(supportService.getMySupportList(uid), HttpStatus.OK);
	}
}
