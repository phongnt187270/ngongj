package UnitTest.GetNotification;

import UnitTest.Constant;
import UnitTest.SetReadNotification.SetReadNotificationResp;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetNotification {
    public static void main(String[] args) {

    }
    public static GetNotificationResp getInfoFromServer(String token, String index, String count) throws IOException {
        URL url = new URL(Constant.Get_Notification + "?token=" + token + "&index=" + index + "&count=" + count);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
            String java_string_content = content.toString();
            System.out.println(java_string_content);
            Gson g = new Gson();
            GetNotificationResp rp = g.fromJson(java_string_content, GetNotificationResp.class);
            return rp;
        }
        finally {
            connection.disconnect();
        }
    }
}
