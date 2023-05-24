package com.chb.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.dao.BatchDAO;
import com.chb.entity.BatchEntity;
import com.chb.service.BatchService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BatchServiceImpl implements BatchService {

	public static final Gson gson = new GsonBuilder().create();

	@Autowired
	BatchDAO batchDao;

	@Override
	public List<BatchEntity> sendBatchDetail(BatchEntity entity) {
		List<BatchEntity> reslut = new ArrayList(
				Arrays.asList(batchDao.sendBatchDetail(entity.getProductId()).toArray()));
		return reslut;
	}

}
