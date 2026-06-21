package com.example.employee.controller;

import com.example.employee.dto.LeaveRequestDto;
import com.example.employee.entity.LeaveRequest;
import com.example.employee.service.LeaveRequestService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leaves")
public class LeaveController {

	private final LeaveRequestService leaveRequestService;

	public LeaveController(LeaveRequestService leaveRequestService) {
		this.leaveRequestService = leaveRequestService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LeaveRequest createLeave(@Valid @RequestBody LeaveRequestDto requestDto) {
		return leaveRequestService.createLeave(requestDto);
	}

	@GetMapping
	public List<LeaveRequest> getLeaves() {
		return leaveRequestService.getAllLeaves();
	}

	@PutMapping("/{id}/approve")
	public LeaveRequest approveLeave(@PathVariable Long id) {
		return leaveRequestService.approve(id);
	}

	@PutMapping("/{id}/reject")
	public LeaveRequest rejectLeave(@PathVariable Long id) {
		return leaveRequestService.reject(id);
	}
}
