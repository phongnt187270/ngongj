package UnitTest.SignUp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import UnitTest.Constant;
import UnitTest.JsonReader;
import com.google.gson.Gson;



public class SignUp {

	public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException {
	    URL url = new URL(Constant.SIGN_UP);
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		Map<String,String> map = new HashMap<>();
		map.put("1000", "OK");
		map.put("9996", "User existed!");
		map.put("1002", "Missing field!");
	    connection.setRequestMethod("POST");
	    connection.setDoOutput(true);
	    try {
	        StringBuilder content;

	        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String line;
				content = new StringBuilder();
				while ((line = in.readLine()) != null) {
					content.append(line);
					content.append(System.lineSeparator());
				}
			}
	        Gson g = new Gson();
	        SignupResp rp = g.fromJson(content.toString(), SignupResp.class);
			System.out.println(map.get(rp.code));
	    } finally {
	        connection.disconnect();
	    }
	}

}

