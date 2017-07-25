package com.rsm.functions;

import com.rsm.model.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

	private FileReader file;
	private BufferedReader readFile;
	private String filePath = "src/main/resources/files/file";

	public List<String> read() {

		List<String> books = new ArrayList<>();

		try {
			this.file = new FileReader(filePath);
			this.readFile = new BufferedReader(this.file);

			String line = this.readFile.readLine();

			while (line != null) {
				books.add(line);
				line = this.readFile.readLine();
			}
		} catch (IOException e) {
			System.err.printf("Error opening file: ", e.getMessage());
		}
		return books;
	}

	public List<Book> formatData(List<String> books) {

		List<Book> formatedList = new ArrayList<>();

		for (int i = 0; i < books.size(); i++) {
			String format = books.get(i).replace(" ", "");
			String id = format.substring(0, 10);
			String description = books.get(i).substring(10, books.get(i).length() - 23).trim();

			String price = format.substring(format.length() - 23, format.length() - 8);
			price = price.replaceFirst("^0+(?!$)", "");
			String splitPrice1 = price.substring(0, price.length() - 2);
			String sliptPrice2 = price.substring(price.length() - 2, price.length());
			BigDecimal formatedPrice = new BigDecimal(splitPrice1 + "." + sliptPrice2);

			String date = format.substring(format.length() - 8, format.length());
			String day = date.substring(0, date.length() - 6);
			String mouth = date.substring(2, date.length() - 4);
			String year = date.substring(4, date.length());
			String finalDate = (day + "/" + mouth + "/" + year);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			Book book = new Book();
			book.setId(id);
			book.setDescription(description);
			book.setPrice(formatedPrice);

			java.util.Date dt;
			try {
				dt = dateFormat.parse(finalDate);
				java.sql.Date d = new java.sql.Date(dt.getTime());
				book.setDate(d);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

			formatedList.add(book);
		}
		return formatedList;
	}

}