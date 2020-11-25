package UnitTest.LogIn;

import UnitTest.Constant;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LogIn {
    public static void main(String[] args) throws Exception {
        URL url = new URL(Constant.LOG_IN);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        Map<String, String> map = new HashMap<>();
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
            LoginResp rp = g.fromJson(content.toString(), LoginResp.class);
            System.out.println(map.get(rp.code));
        }
        finally {
            connection.disconnect();
        }
    }
}
