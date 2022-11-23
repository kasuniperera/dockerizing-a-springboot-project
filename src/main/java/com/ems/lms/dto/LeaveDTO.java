package com.ems.lms.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LeaveDTO {

	private String employee;

	private LocalDateTime date;
}
