package com.chb.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumberUtil {

	public int resPonseRandomOne(int startNum, int endNum) {
		int result = 0;
		try {
			if (endNum > startNum) {
				// random = (int)(Math.random()*(MAX-min+1)) + min;
				result = (int) (Math.random() * (endNum - startNum + 1)) + startNum;
			} else {
				result = -1;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	public List<Integer> responseRandomSize(int startNum, int endNum, int size) {
		List<Integer> result = new ArrayList<>();
		try {
			if (endNum > startNum) {
				// random = (int)(Math.random()*(MAX-min+1)) + min;
				for (int i = 0; i < size; i++) {
					result.add((int) (Math.random() * (endNum - startNum + 1)) + startNum);
				}
				checkRandom(result, startNum, endNum);
			} else {
				result.add(-1);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	private List<Integer> checkRandom(List<Integer> obj, int startNum, int endNum) {
		try {
			for (int i = 0; i < obj.size(); i++) {
				for (int j = obj.size() - 1; j > i; j--) {
					if (obj.get(i).equals(obj.get(j))) {
						obj.remove(j);
						obj.add((int) (Math.random() * (endNum - startNum + 1)) + startNum);
						// Reset
						return checkRandom(obj, startNum, endNum);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return obj;
	}

	public void dataProcess(String filePath) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			while (bufferedReader.readLine() != null) {
				System.out.println(bufferedReader.readLine());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			// close() method is used to close all the read
			// write connections
			try {
				fileReader.close();
				bufferedReader.close();
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}
}
