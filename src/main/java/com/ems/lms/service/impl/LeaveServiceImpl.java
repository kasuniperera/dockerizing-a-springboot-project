package com.ems.lms.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.lms.domain.Leave;
import com.ems.lms.repository.LeaveRepository;
import com.ems.lms.service.LeaveService;
import com.ems.lms.util.CommonUtil;

@Service
public class LeaveServiceImpl implements LeaveService{

	private static final Logger LOGGER = LoggerFactory.getLogger(LeaveServiceImpl.class);
	
	@Autowired
	private LeaveRepository repository;
	
	@Override
	public void save(Leave leave) {
		
		long startTime = System.currentTimeMillis();
		String requestString = CommonUtil.convertToString(leave);
		LOGGER.info("saveRequest : request={}", requestString);
		
		repository.save(leave);
		
		LOGGER.info("saveResponse : timeTaken={}|request={}", CommonUtil.getTimeTaken(startTime), requestString);
	}
	
	@Override
	public void update(Leave leave,Long id) {
		
		long startTime = System.currentTimeMillis();
		String requestString = CommonUtil.convertToString(leave);
		LOGGER.info("updateRequest : request={}|id={}", requestString,id);
		
		Optional<Leave> optionalLeave = repository.findById(id);
		
		if(optionalLeave.isPresent()) {
			Leave existingLeave =optionalLeave.get();
			existingLeave.setEmployee(leave.getEmployee());
			existingLeave.setDate(leave.getDate());
			repository.save(existingLeave);
		}else {
			LOGGER.error("updateResponse : timeTaken={}|request={}|id={}|response=Leave record not found", CommonUtil.getTimeTaken(startTime),
					requestString,id);
		}
		
		LOGGER.info("updateResponse : timeTaken={}|request={}|id={}", CommonUtil.getTimeTaken(startTime),
				requestString,id);
	}
	
	@Override
	public List<Leave> get(){
		long startTime = System.currentTimeMillis();
		LOGGER.info("getRequest : ");
		
		List<Leave> response= repository.findAll();
		
		String responseString = CommonUtil.convertToString(response);
		LOGGER.info("getResponse : timeTaken={}|response={}", CommonUtil.getTimeTaken(startTime),
				responseString);
		
		return response;
	}
	
	@Override
	public void delete(Long id) {
		
		long startTime = System.currentTimeMillis();
		LOGGER.info("deleteRequest : id={}",id);
		
		Optional<Leave> optionalLeave = repository.findById(id);
		
		if(optionalLeave.isPresent()) {
			repository.deleteById(id);
		}else {
			LOGGER.error("deleteResponse : timeTaken={}|id={}|response=leave record not found", CommonUtil.getTimeTaken(startTime),
					id);
		}
		
		LOGGER.info("deleteResponse : timeTaken={}|id={}", CommonUtil.getTimeTaken(startTime),
				id);
	}
}
