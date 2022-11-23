package com.ems.lms.service;

import java.util.List;

import com.ems.lms.domain.Leave;

public interface LeaveService {

	public void save(Leave leave);
	
	public void update(Leave leave,Long id);
	
	public List<Leave> get();
	
	public void delete(Long id);
	
}
