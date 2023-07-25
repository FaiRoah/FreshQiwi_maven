package com.fairoah.freshQiwi.exception;

public class WrongCurrencyNameException extends Exception {

	public WrongCurrencyNameException(String charCode) {
		super();
		System.out
				.println("Данные о валюте " + charCode + " Не найдены. Убедитесь, что верно вводите название валюты.");
	}

}
