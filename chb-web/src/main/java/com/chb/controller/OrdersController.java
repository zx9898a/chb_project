package com.chb.controller;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chb.service.OrderService;
import com.chb.vo.BasicOut;
import com.chb.vo.order.OrderVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/orders")
public class OrdersController {

	@Autowired
	OrderService orderService;

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
		String resault = "2000";
		try {

			log.info(resault);
		} catch (Exception e) {
			return resault;
		}
		return resault;

	}

	@CrossOrigin
	@PostMapping("/getAllOrder")
	public String getAllOrder() {
		BasicOut<List<OrderVO>> outvo = new BasicOut();
		String result;
		try {
			List<OrderVO> entity = orderService.readOrderByAll();
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
	@PostMapping("/getAllOrderById")
	public String getAllOrderById(@RequestBody String res) {
		BasicOut<OrderVO> outvo = new BasicOut();
		String result;
		try {
			OrderVO vo = gson.fromJson(res, OrderVO.class);
			OrderVO entity = orderService.readOrderById(vo);
			outvo.setCode(20000);
			outvo.setData(entity);
			result = gson.toJson(outvo);
			log.info("get OrderById result " + result);
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
	@PostMapping("/sendOrder")
	public String snedOrder(@RequestBody String res) {
		BasicOut<OrderVO> outvo = new BasicOut();
		String result;
		try {
			OrderVO vo = gson.fromJson(res, OrderVO.class);
			orderService.insertOrder(vo);
			outvo.setCode(20000);
			outvo.setData(vo);
			result = gson.toJson(outvo);
			log.info("send Order result " + result);
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
	@PostMapping("/updateOrder")
	public String updateOrder(@RequestBody String res) {
		BasicOut<OrderVO> outvo = new BasicOut();
		String result;
		try {
			OrderVO vo = gson.fromJson(res, OrderVO.class);
			orderService.updateOrder(vo);
			outvo.setCode(20000);
			outvo.setData(vo);
			result = gson.toJson(outvo);
			log.info("update Order result " + result);
		} catch (Exception e) {
			outvo.setStatus("e");
			outvo.setCode(50008);
			outvo.setMessage(e.getCause().getMessage());
			result = gson.toJson(outvo);
			return result;
		}
		return result;

	}

}
