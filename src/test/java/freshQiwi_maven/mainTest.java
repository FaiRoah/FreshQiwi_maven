package freshQiwi_maven;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.Test;

import com.fairoah.freshQiwi.MainApp;

class mainTest {

	@Test
	void test() throws ClientProtocolException, IOException {
		MainApp mainApp = new MainApp();
		
		String[] args = {"--code=USD", "--date=2022-10-08"};
		mainApp.main(args);
		
		String expectedOutput = "USD (Доллар США): 61,2475";

        //Не успел доделать :(
	}

}
