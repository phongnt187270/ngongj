package UnitTest.SetReadNotification;

import UnitTest.Constant;
import UnitTest.GetNotification.GetNotification;
import UnitTest.GetNotification.GetNotificationResp;
import UnitTest.LogIn.LogIn;
import UnitTest.LogIn.LoginResp;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SetReadNotification {
    public static void main(String[] args) {
        try {
            case1();
            case2();
            case3();
            case4();
            case5();
            case6();
            case7();
        }
        catch (Exception e) {
            System.out.println("Can not run");
        }
    }
    public static SetReadNotificationResp getInfoFromServer(String token, String notification_id) throws IOException {
        URL url = new URL(Constant.Set_Read_Notification + "?token=" + token + "&notification_id=" + notification_id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
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
            SetReadNotificationResp rp = g.fromJson(java_string_content, SetReadNotificationResp.class);
            return rp;
        }
        finally {
            connection.disconnect();
        }
    }
    public static void case1() throws IOException {
        System.out.println("Case 1: Success");
        LoginResp loginResp = LogIn.getInfoFromServer("", "");
        String token = loginResp.data.token;
        GetNotificationResp getNotificationResp = GetNotification.getInfoFromServer(token, "", "");
        String notification_id = getNotificationResp.data.notification_id;
        SetReadNotificationResp setReadNotificationResp = getInfoFromServer(token, notification_id);
        try {
            assert "1000".equals(setReadNotificationResp.code) : "Fail";
            System.out.println("Success");
            System.out.println("Notification read!");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case2() throws IOException {
        System.out.println("Case 2: Invalid token");
        SetReadNotificationResp setReadNotificationResp = getInfoFromServer("", "");
        try {
            assert "9998".equals(setReadNotificationResp.code) : "Fail";
            System.out.println("Invalid token");
            System.out.println("Moving to log in...");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case3() throws IOException {
        System.out.println("Case 3: Can not handle request");
        LoginResp loginResp = LogIn.getInfoFromServer("", "");
        String token = loginResp.data.token;
        GetNotificationResp getNotificationResp = GetNotification.getInfoFromServer(token, "", "");
        String notification_id = getNotificationResp.data.notification_id;
        SetReadNotificationResp setReadNotificationResp = getInfoFromServer(token, notification_id);
        try {
            assert "1001".equals(setReadNotificationResp.code) : "Fail";
            System.out.println("Can not connect to DB");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case4() throws IOException {
        System.out.println("Account locked");
        LoginResp loginResp = LogIn.getInfoFromServer("", "");
        try {
            assert "9995".equals(loginResp.code) : "Fail";
            System.out.println("Your account is locked");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
//        String token = loginResp.data.token;
//        GetNotificationResp getNotificationResp = GetNotification.getInfoFromServer(token, "", "");
//        String notification_id = getNotificationResp.data.notification_id;
//        SetReadNotificationResp setReadNotificationResp = getInfoFromServer(token, notification_id);

    }
    public static void case5() throws IOException {
        System.out.println("Case 5: last_update is null");
        LoginResp loginResp = LogIn.getInfoFromServer("", "");
        String token = loginResp.data.token;
        GetNotificationResp getNotificationResp = GetNotification.getInfoFromServer(token, "", "");
        String notification_id = getNotificationResp.data.notification_id;
        SetReadNotificationResp setReadNotificationResp = getInfoFromServer(token, notification_id);
        try {
            assert setReadNotificationResp.data.last_update == null : "Fail";
            System.out.println("This badge does not have last_update field");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case6() throws IOException {
        System.out.println("Case 6: badge is null");
        LoginResp loginResp = LogIn.getInfoFromServer("", "");
        String token = loginResp.data.token;
        GetNotificationResp getNotificationResp = GetNotification.getInfoFromServer(token, "", "");
        String notification_id = getNotificationResp.data.notification_id;
        SetReadNotificationResp setReadNotificationResp = getInfoFromServer(token, notification_id);
        try {
            assert setReadNotificationResp.data.badge == null : "Fail";
            System.out.println("This notification does not have badge");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case7() throws IOException {
        System.out.println("Case 7: Notification has been read");
        LoginResp loginResp = LogIn.getInfoFromServer("", "");
        String token = loginResp.data.token;
        GetNotificationResp getNotificationResp = GetNotification.getInfoFromServer(token, "", "");
//        String notification_id = getNotificationResp.data.notification_id;
//        SetReadNotificationResp setReadNotificationResp = getInfoFromServer(token, notification_id);
        try {
            assert "1".equals(getNotificationResp.data.read) : "Fail";
            System.out.println("This notification has been read");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
}
