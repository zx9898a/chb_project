package com.chb.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtilMain {
	public static void main(String[] args) {
//		NumberUtil util = new NumberUtil();
//		String filepath = "C:\\Users\\Tim\\Desktop\\EX\\Li\\L111.txt";
//		String filepath2 = "C:\\Users\\Tim\\Desktop\\EX\\539\\111\\11112.txt";
//		log.info("Processing: Start ");
//		for (int i = 0; i < 500; i++) {
//			util.dataProcess(filepath);
//			log.info("Processing: " + i);
//		}

//		String value = "112_03_05";
//		DateUtil date = new DateUtil();
//		String res = date.transferMinguoDateToADDate(value);
//		log.info("Date: " +res);
//		log.info("length: " +value.length());

		NumberUtil util = new NumberUtil();
		String filepath = "C:\\Users\\Tim\\Desktop\\EX\\Li\\L111.txt";
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			fileReader = new FileReader(filepath);
			bufferedReader = new BufferedReader(fileReader);
			String str;
			while ((str = bufferedReader.readLine()) != null) {
				// System.out.println(bufferedReader.readLine());
				stringBuffer.append(str);
				stringBuffer.append("\r\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//		log.info("value: " + stringBuffer.toString());
		String value = stringBuffer.toString();
		String[] splitStr = value.split("\n");
//		log.info(value);
		for (int i = 0; i < splitStr.length; i++) {
			// 1 20 39
			if (i % 19 == 1) {
				String[] cut = splitStr[i].split("	");
//				log.info(cut[0]);
				for (int j = 0; j < cut.length; j++) {
					log.info(cut[j]);
				}

			}
		}
	}

}
