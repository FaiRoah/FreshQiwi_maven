package com.fairoah.freshQiwi;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;

import com.fairoah.freshQiwi.exception.NoValuteForDateFoundException;
import com.fairoah.freshQiwi.exception.WrongCurrencyNameException;
import com.fairoah.freshQiwi.model.Valute;
import com.fairoah.freshQiwi.wrapper.ValCurs;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class MainApp {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		Map<String, String> arguments = new HashMap<>();

		// Parse command-line arguments
		for (String arg : args) {
			if (arg.startsWith("--") && arg.contains("=")) {
				String[] parts = arg.split("=", 2);
				String key = parts[0].substring(2); // Remove the leading "--"
				String value = parts[1];
				arguments.put(key, value);
			}
		}

		// Access the parsed data
		String charCode = arguments.get("code");
		String date = arguments.get("date");


		date = formatDate(date);

		final Content getResult = Request.Get("https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date).execute()
				.returnContent();

		String xmlData = getResult.asString();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(new StringReader(xmlData));

			// Access the parsed data
			List<Valute> valutes = valCurs.getValutes();
			if (valutes == null)
				throw new NoValuteForDateFoundException(date);
			boolean reachedEnd = false;
			for (Valute valute : valutes) {
				if (!reachedEnd) {
					if (valute.getCharCode().equals(charCode)) {
						System.out.println(charCode + " " + "(" + valute.getName() + "): " + valute.getValue());
						break;
					}
				}
				if (valute.equals(valutes.get(valutes.size() - 1))) {
	                reachedEnd = true;
	            }
			}
			if (reachedEnd == true) {
				throw new WrongCurrencyNameException(charCode);
				
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (NoValuteForDateFoundException e) {
			e.printStackTrace();
		} catch (WrongCurrencyNameException e) {
			e.printStackTrace();
		}
		

	}

	public static String formatDate(String date) {
		String inputDateStr = date;

		// Parse the input date using DateTimeFormatter
		LocalDate inputDate = LocalDate.parse(inputDateStr, DateTimeFormatter.ISO_LOCAL_DATE);

		// Format the date into "dd/MM/yyyy" format
		String outputDate = inputDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		return outputDate;
	}
}
