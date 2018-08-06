package utilities;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.mh453Uol.utilities.networking.Ping;
import com.mh453Uol.utilities.networking.SystemProcessExecutor;

public class PingingTest {

	@Test
	public void Ping_WhereIpAddressIsValid_IsValidReturnsFalse() throws IOException {
		Ping ping = new Ping("192.111.111.1111");
		Assert.assertFalse(ping.isValid());
	}
	
	@Test
	public void Ping_WhereIpIsValid_ReturnsPayload() throws IOException {
		Ping ping = new Ping("8.8.8.8");
		String errorResponse = "Ping request could not find host " + ping.getIpAddress() + ". Please check the name and try again.";

		if(ping.isValid()) {
			new SystemProcessExecutor().run(ping);
		}
		
		Assert.assertNotEquals("Response is :" + ping.getResponse(), ping.getResponse(), errorResponse);
	}
}
