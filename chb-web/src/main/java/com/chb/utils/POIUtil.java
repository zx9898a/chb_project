package com.chb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class POIUtil {

	Gson gson = new GsonBuilder().create();

	public XSSFWorkbook writeXlxs(Map data) throws Exception {
		log.info(data.toString());
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Data");
		Row row = null;
		Cell cell = null;

		// Title
		sendHeader(sheet, cell, row, data);

		int rownum = 1;
		row = sheet.createRow(rownum++);
		int cellnum = 0;
		for (Object key : data.keySet()) {
			cell = row.createCell(cellnum++);
			if (data.get(key) instanceof String) {
				cell.setCellValue((String) data.get(key));
			} else if (data.get(key) instanceof Double) {
				cell.setCellValue((Double) data.get(key));
			} else if (data.get(key) instanceof Integer) {
				cell.setCellValue((Integer) data.get(key));
			} else if (data.get(key) instanceof Date) {
				cell.setCellValue((Date) data.get(key));
			}
		}

		return workbook;
	}

	public XSSFWorkbook writeXlxs(List list) throws Exception {
		log.info(list.toString());
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Data");
		Row row = null;
		Cell cell = null;

		Map headermap = gson.fromJson(gson.toJson(list.get(0)), Map.class);
		sendHeader(sheet, cell, row, headermap);

		int rownum = 1;
		Map map = new HashMap<>();
		for (Object key : list) {
			String data = gson.toJson(key);
			Map datamap = gson.fromJson(data, Map.class);
			map.put(rownum, datamap);
			row = sheet.createRow(rownum++);
			int cellnum = 0;
			for (Object obj : datamap.keySet()) {
				// System.out.print( datamap.get(obj) + " ");
				cell = row.createCell(cellnum++);
				if (datamap.get(obj) instanceof String) {
					cell.setCellValue((String) datamap.get(obj));
				} else if (datamap.get(obj) instanceof Double) {
					cell.setCellValue((Double) datamap.get(obj));
				} else if (datamap.get(obj) instanceof Integer) {
					cell.setCellValue((Integer) datamap.get(obj));
				} else if (datamap.get(obj) instanceof Date) {
					cell.setCellValue((Date) datamap.get(obj));
				}
			}
		}
		return workbook;
	}

	private void sendHeader(XSSFSheet sheet, Cell cell, Row row, Map data) throws Exception {
		int cellnum = 0;
		int rownum = 0;
		row = sheet.createRow(rownum++);
		for (Object key : data.keySet()) {
			cell = row.createCell(cellnum++);
			cell.setCellValue((String) key);
		}
	}

	public void readXlxs(File value) throws Exception {
		FileInputStream file = new FileInputStream(value);
		// Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		// Get first/desired sheet from the workbook
		XSSFSheet sheet = workbook.getSheetAt(0);
		// Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				// Check the cell type and format accordingly
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					log.info(cell.getNumericCellValue() + "t");
					// input Value
					break;
				case Cell.CELL_TYPE_STRING:
					log.info(cell.getStringCellValue() + "t");
					// input Value
					break;
				}
			}
			log.info("");
		}
		file.close();
	}

	public void xlxsOutputStream(String xlsname, XSSFWorkbook workbook) throws Exception {
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Tim\\Downloads\\" + xlsname + ".xlsx"));
			workbook.write(out);
			out.close();
			log.info(xlsname + ".xlsx written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
