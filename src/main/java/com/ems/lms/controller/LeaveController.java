package com.ems.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.lms.domain.Leave;
import com.ems.lms.dto.LeaveDTO;
import com.ems.lms.dto.LmsApiResponse;
import com.ems.lms.service.LeaveService;
import com.ems.lms.util.CommonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/leave")
@CrossOrigin
public class LeaveController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LeaveController.class);
	
	@Autowired
	private LeaveService service;
	
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping()
	@ApiOperation(value = "Save leave details.", notes = "Save leave details.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	public ResponseEntity<LmsApiResponse> save(@RequestBody LeaveDTO leaveDto){
		
		long startTime = System.currentTimeMillis();
		String requestString = CommonUtil.convertToString(leaveDto);
		LOGGER.info("saveRequest : request={}", requestString);

		Leave leave = mapper.convertValue(leaveDto, Leave.class);
		service.save(leave);
		
		LOGGER.info("saveResponse : timeTaken={}|request={}", CommonUtil.getTimeTaken(startTime), requestString);
		
		return ResponseEntity.ok(new LmsApiResponse(200,"Leave record successfully saved"));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update leave details.", notes = "Update leave details.The query parameter id is mandatory.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@ApiImplicitParams({ @ApiImplicitParam(value = "Id of the leave record", paramType = "query", name = "id", required = true, dataType = "long") })
	public ResponseEntity<LmsApiResponse> update(@RequestBody LeaveDTO leaveDto,
			@PathVariable(value = "id") Long id){
		
		long startTime = System.currentTimeMillis();
		String requestString = CommonUtil.convertToString(leaveDto);
		LOGGER.info("updateRequest : request={}|id={}", requestString,id);
		
		Leave leave = mapper.convertValue(leaveDto, Leave.class);
		service.update(leave, id);
		
		LOGGER.info("updateResponse : timeTaken={}|request={}|id={}", CommonUtil.getTimeTaken(startTime),
				requestString,id);
		
		return ResponseEntity.ok(new LmsApiResponse(200,"Leave record successfully updated"));
	}
	
	@GetMapping()
	@ApiOperation(value = "Get all leave details.", notes = "Get leave details.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	public ResponseEntity<LmsApiResponse> get(){
		
		long startTime = System.currentTimeMillis();
		LOGGER.info("getRequest : ");
		
		List<Leave> leaveRecords=service.get();
		List<LeaveDTO> response = new ArrayList<>();
		if(!leaveRecords.isEmpty()) {
			leaveRecords.forEach(leave->{
				LeaveDTO leaveDto = mapper.convertValue(leave, LeaveDTO.class);
				response.add(leaveDto);
			});
		}
		
		String responseString = CommonUtil.convertToString(response);
		LOGGER.info("getResponse : timeTaken={}|response={}", CommonUtil.getTimeTaken(startTime),
				responseString);
		
		return ResponseEntity.ok(new LmsApiResponse(200,"Success",response));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete a leave record.", notes = "Delete a leave record.The query parameter id is mandatory.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	@ApiImplicitParams({ @ApiImplicitParam(value = "Id of the leave record", paramType = "query", name = "id", required = true, dataType = "long") })
	public ResponseEntity<LmsApiResponse> delete(@PathVariable(value = "id") Long id){
		
		long startTime = System.currentTimeMillis();
		LOGGER.info("deleteRequest : id={}",id);
		
		service.delete(id);
		
		LOGGER.info("deleteResponse : timeTaken={}|id={}", CommonUtil.getTimeTaken(startTime),
				id);
		return ResponseEntity.ok(new LmsApiResponse(200,"A leave record successfully deleted"));
	}
}
