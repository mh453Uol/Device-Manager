package utilities;

import java.io.IOException;

import org.junit.Test;

import com.mh453Uol.utilities.networking.Ping;
import com.mh453Uol.utilities.networking.SystemProcessExecutor;

public class PingingTest {

	@Test
	public void Ping_WhereIpAddressIsValid_ReturnsTrue() throws IOException {
		Ping ping = new Ping("192.111.111.1111");
		if(!ping.isValid()) {
			System.out.println("Ping is not valid!");
		}
		new SystemProcessExecutor().run(ping);
		System.out.println(ping.getResponse());
	}
}
