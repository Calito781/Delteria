package delteria.gserver.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class GServerAdapter {
	private final JsonParser parser = new JsonParser();
	
	public String convertToServer(JsonElement outgoing) {
		return Base64.encode(outgoing.toString().getBytes());
	}

	public JsonElement convertFromServer(String incoming) {
		try {
			String original = new String(Base64.decode(incoming));

			return parser.parse(original);
		} catch (Base64DecodingException e) {
			throw new RuntimeException(e);
		}
	}
}
