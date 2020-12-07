package search;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Map;


public class Search {

	public static void main(String[] args) throws MalformedURLException, ProtocolException,
	IOException {
	    URL url = new URL(Constant.SIGN_UP);
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	    connection.setRequestMethod("POST");
	    Map<String, Object> params = new HashMap<>();
	    params.put("token", "XY987654321");
	    params.put("keyword", "clothes");
		params.put("user_id", "IUIDSJFis");
		params.put("index", 0);
		params.put("count", 0);

	    StringBuilder postData = new StringBuilder();
	    for (Map.Entry<String, Object> param : params.entrySet()) {
	        if (postData.length() != 0) {
	            postData.append('&');
	        }
	        postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	        postData.append('=');
	        postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	    }

	    byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	    connection.setDoOutput(true);
	    try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
	        writer.write(postDataBytes);
	        writer.flush();
	        writer.close();

	        StringBuilder content = new StringBuilder();

	        try (BufferedReader in = new BufferedReader(
	                new InputStreamReader(connection.getInputStream()))) {
	        String line;
	        content = new StringBuilder();
	           while ((line = in.readLine()) != null) {
	                content.append(line);
	                content.append(System.lineSeparator());
	            }
	        } catch (MalformedURLException e){
	        	System.out.println(e);
			} catch (ProtocolException e){
	        	System.out.println(e.getMessage());

			} catch (IOException e){
	        	System.out.println(e);
			}

			System.out.println(content.toString());

	        Gson g = new Gson();
	        ResponseSearch rp = g.fromJson(content.toString(), ResponseSearch.class);
            System.out.println(rp);
	        System.out.println("Unit test 1: Right token");
	        assert(rp.code == "1000");
	        System.out.println("Finished! Satisfied!");

//			System.out.println("Unit test 2: Empty token or token too short or old token");
//			assert(rp.token == "" || rp.token.length < 6 );
//			System.out.println("Finished! Satisfied!");
//
			System.out.println("Unit test 3:  Right token and parameter but no result found");
			assert(rp.code == "9994");
			System.out.println("Finished! Satisfied!");
//
			System.out.println("Unit test 4:  Right token and parameter but user_id is locked");
//			assert(rp.code == "9995");
//			System.out.println("Finished! Satisfied!");

			System.out.println("Unit test 5: Right token but user_id is incorrect");
			assert(rp.code == "9995");
//			System.out.println("Finished! Satisfied!");

			System.out.println("Unit test 6: Right parameter but no keyword ");
			assert(rp.code == "9995");

			System.out.println("Unit test 7: Right token and parameter but author_id is invalid");

	    } finally {
	        connection.disconnect();
	    }
	}

}

class ResponseSearch {
	public String code;
	public String message;
}
