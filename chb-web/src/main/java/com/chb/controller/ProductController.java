package com.chb.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chb.entity.BatchEntity;
import com.chb.service.BatchService;
import com.chb.service.ProductPurchaseService;
import com.chb.service.ProductService;
import com.chb.vo.BasicOut;
import com.chb.vo.product.ProductPurchaseVO;
import com.chb.vo.product.ProductVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

	@Autowired
	ProductService productservice;

	@Autowired
	BatchService batchservice;

	@Autowired
	ProductPurchaseService productPurchaseservice;

	public static final GsonBuilder builder = new GsonBuilder()
			// .registerTypeAdapter(byte[].class, (JsonSerializer<byte[]>) (src, typeOfSrc,
			// context) -> new JsonPrimitive(Base64.getEncoder().encodeToString(src)))
			.registerTypeAdapter(byte[].class,
					(JsonDeserializer<byte[]>) (json, typeOfT, context) -> Base64.getDecoder()
							.decode(json.getAsString()))
			.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date());

	public static final Gson gson = builder.create();

	@CrossOrigin
	@PostMapping("/get")
	public String getinfo() {
		String result = "2000";
		try {

			log.info("Test Route : " + result);
		} catch (Exception e) {
			return result;
		}
		return result;

	}

	@CrossOrigin
	@PostMapping("/getAllProduct")
	public String getAllProduct() {
		BasicOut<List<ProductVO>> outvo = new BasicOut();
		String result;
		try {
			List<ProductVO> entity = productservice.readProductByAll();
			outvo.setCode(20000);
			outvo.setData(entity);
			result = gson.toJson(outvo);
			log.info("send ProductAll result " + result);
		} catch (Exception e) {
			outvo.setStatus("e");
			outvo.setCode(50008);
			outvo.setMessage(e.getCause().getMessage());
			result = gson.toJson(outvo);
			return result;
		}
		return result;
	}

	@CrossOrigin
	@PostMapping("/getAllProductById")
	public String getAllProductById(@RequestBody String res) {
		BasicOut<ProductVO> outvo = new BasicOut();
		String result;
		try {
			ProductVO vo = gson.fromJson(res, ProductVO.class);
			ProductVO entity = productservice.readProductById(vo);
			outvo.setCode(20000);
			outvo.setData(entity);
			result = gson.toJson(outvo);
			log.info("get ProductById result " + result);
		} catch (Exception e) {
			outvo.setStatus("e");
			outvo.setCode(50008);
			outvo.setMessage(e.getCause().getMessage());
			result = gson.toJson(outvo);
			return result;
		}
		return result;
	}

	@CrossOrigin
	@PostMapping("/sendProduct")
	public String snedProduct(@RequestBody String res) {
		BasicOut<ProductVO> outvo = new BasicOut();
		String result;
		try {
			ProductVO vo = gson.fromJson(res, ProductVO.class);
			productservice.insertProduct(vo);
			outvo.setCode(20000);
			outvo.setData(vo);
			result = gson.toJson(outvo);
			log.info("send Product result " + result);
		} catch (Exception e) {
			outvo.setStatus("e");
			outvo.setCode(50008);
			outvo.setMessage(e.getCause().getMessage());
			result = gson.toJson(outvo);
			return result;
		}
		return result;

	}

	@CrossOrigin
	@PostMapping("/updateProduct")
	public String updateProduct(@RequestBody String res) {
		BasicOut<ProductVO> outvo = new BasicOut();
		String result;
		try {
			ProductVO vo = gson.fromJson(res, ProductVO.class);
			productservice.updateProduct(vo);
			outvo.setCode(20000);
			outvo.setData(vo);
			result = gson.toJson(outvo);
			log.info("update Product result " + result);
		} catch (Exception e) {
			outvo.setStatus("e");
			outvo.setCode(50008);
			outvo.setMessage(e.getCause().getMessage());
			result = gson.toJson(outvo);
			return result;
		}
		return result;

	}

	@CrossOrigin
	@PostMapping("/getAllProductPurchase")
	public String getAllProductPurchase() {
		BasicOut<List<ProductPurchaseVO>> outvo = new BasicOut();
		String result;
		try {
			List<ProductPurchaseVO> entity = productPurchaseservice.readProductPurchaseByAll();
			outvo.setCode(20000);
			outvo.setData(entity);
			result = gson.toJson(outvo);
			log.info("send ProductAll result " + result);
		} catch (Exception e) {
			outvo.setStatus("e");
			outvo.setCode(50008);
			outvo.setMessage(e.getCause().getMessage());
			result = gson.toJson(outvo);
			return result;
		}
		return result;

	}

	@CrossOrigin
	@PostMapping("/sendBatchDetail")
	public String sendBatchDetail(@RequestBody String res) {
		BatchEntity reqVO = new BatchEntity();
		String result = "";
		BasicOut<List<BatchEntity>> outvo = new BasicOut();
		List<BatchEntity> entity = new ArrayList<BatchEntity>();
		try {
			reqVO = gson.fromJson(res, BatchEntity.class);
			entity = batchservice.sendBatchDetail(reqVO);
			result = gson.toJson(entity);
			outvo.setCode(20000);
			outvo.setData(entity);
			result = gson.toJson(outvo);
			log.info("Get value Batch " + result);
		} catch (Exception e) {
			outvo.setStatus("e");
			outvo.setCode(50008);
			outvo.setMessage(e.getCause().getMessage());
			result = gson.toJson(entity);
			return result;
		}
		return result;

	}
}
