package com.fairoah.freshQiwi.exception;

public class NoValuteForDateFoundException extends Exception {
	public NoValuteForDateFoundException(String date) {
		super();
		System.out.println("Данные о валютах на дату " + date + " не найдены. ");
	}

}
