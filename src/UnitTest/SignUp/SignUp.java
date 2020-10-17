package UnitTest.SignUp;
import UnitTest.JsonReader;

import java.net.*;
public class SignUp {
    public void Test() throws Exception {
        URL url = new URL("https://project-facebook-clone.herokuapp.com/it4788/user/signup");
        String input = JsonReader.readJsonFromUrl(url);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept","application/json");
        try {
            SignupResp result = new SignupResp(input);
            String code = result.getCode();
            if (code.equals("1000")) {
                assert(result.message.equals("OK"));
            }
            else if (code.equals("996")) {

            }
        }
        finally {
            con.disconnect();
        }
    }
}
