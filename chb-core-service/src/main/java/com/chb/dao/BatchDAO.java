package com.chb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chb.entity.BatchEntity;
import com.chb.repository.BatchRepository;


@Repository
public class BatchDAO {

	private BatchRepository batchRepository;

	public BatchDAO(BatchRepository batchRepository) {
		super();
		this.batchRepository = batchRepository;
	}

	public List<BatchEntity> sendBatchDetail(String Id) {
		return batchRepository.sendBatchDetail(Id);
	}
	
}
