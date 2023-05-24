package com.chb.service;

import java.util.List;

import com.chb.entity.BatchEntity;


public interface BatchService {
	List<BatchEntity> sendBatchDetail(BatchEntity vo);
}
