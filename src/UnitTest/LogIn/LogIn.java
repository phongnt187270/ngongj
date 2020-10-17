package UnitTest.LogIn;

import UnitTest.JsonReader;

import java.net.HttpURLConnection;
import java.net.URL;

public class LogIn {
    public void Test() throws Exception {
        try {
            URL url = new URL("");
            String input = JsonReader.readJsonFromUrl(url);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            LoginResp result = new LoginResp(input);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
