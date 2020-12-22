package UnitTest.GetUserInfo;
import UnitTest.Constant;
import UnitTest.LogIn.LogIn;
import UnitTest.LogIn.LoginResp;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetUserInfo {
    public static void main(String[] args)  {
        try {
            case1();
            case2();
            case3();
            case4();
            case5();
            case6();
            case7();
            case8();
            case9();
            case10();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static GetUserInfoResp getInfoFromServer(String token, String user_id) throws IOException {
        URL url = new URL(Constant.Get_User_Info + "?token=" + token + "&user_id=" + user_id);
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
            return g.fromJson(java_string_content, GetUserInfoResp.class);
        }
        finally {
            connection.disconnect();
        }
    }
    public static void case1() throws IOException {
        System.out.println("Case 1: Success");
        LoginResp loginResp = LogIn.getInfoFromServer("0968823005", "abcdef");
        String token = loginResp.data.token;
        String user_id = "63";
        GetUserInfoResp getUserInfoResp = getInfoFromServer(token, user_id);
        try {
            assert "1000".equals(getUserInfoResp.code) : "Fail";
            System.out.println("Success");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case2() throws IOException {
        System.out.println("Case 2: Invalid token");
        GetUserInfoResp getUserInfoResp = getInfoFromServer("abc", "");
        try {
            assert "9998".equals(getUserInfoResp.code) : "Fail";
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
        String user_id = "";
        GetUserInfoResp getUserInfoResp = getInfoFromServer(token, user_id);
        try {
            assert "1001".equals(getUserInfoResp.code) : "Fail";
            System.out.println("Can not connect to DB");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case4() throws IOException {
        System.out.println("Case 4: Account locked");
        LoginResp loginResp = LogIn.getInfoFromServer("", "");
        try {
            assert "9995".equals(loginResp.code) : "Fail";
            System.out.println("Your account is locked");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case5() throws IOException {
        System.out.println("Case 5: user_id not exist");
        LoginResp loginResp = LogIn.getInfoFromServer("0968823005", "abcdef");
        String token = loginResp.data.token;
        String user_id = "200";
        GetUserInfoResp getUserInfoResp = getInfoFromServer(token, user_id);
        try {
            assert "1004".equals(getUserInfoResp.code) : "Fail";
            System.out.println("This user is not exist");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case6() throws IOException {
        System.out.println("Case 6: username or id is null");
        LoginResp loginResp = LogIn.getInfoFromServer("", "");
        String token = loginResp.data.token;
        String user_id = "";
        GetUserInfoResp getUserInfoResp = getInfoFromServer(token, user_id);
        try {
            assert getUserInfoResp.data.username == null || getUserInfoResp.data.id == null : "Fail";
            System.out.println("This user is not exist");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case7() throws IOException {
        System.out.println("Case 7: Be blocked by user");
        LoginResp loginResp = LogIn.getInfoFromServer("0968823005", "abcdef");
        String token = loginResp.data.token;
        String user_id = "115";
        GetUserInfoResp getUserInfoResp = getInfoFromServer(token, user_id);
        System.out.println("You are blocked by this user");
    }
    public static void case8() throws IOException {
        System.out.println("Case 8: description is null");
        LoginResp loginResp = LogIn.getInfoFromServer("0968823005", "abcdef");
        String token = loginResp.data.token;
        String user_id = "";
        GetUserInfoResp getUserInfoResp = getInfoFromServer(token, user_id);
        try {
            assert getUserInfoResp.data.description == null : "Fail";
            System.out.println("This user does not have description");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case9() throws IOException {
        System.out.println("Case 9: Missing some fields");
        LoginResp loginResp = LogIn.getInfoFromServer("", "");
        String token = loginResp.data.token;
        String user_id = "";
        GetUserInfoResp getUserInfoResp = getInfoFromServer(token, user_id);
        try {
            assert getUserInfoResp.data.created == null ||
                    getUserInfoResp.data.avatar == null ||
                    getUserInfoResp.data.cover_image == null ||
                    getUserInfoResp.data.link == null ||
                    getUserInfoResp.data.address == null ||
                    getUserInfoResp.data.country == null ||
                    getUserInfoResp.data.listing == null ||
                    getUserInfoResp.data.is_friend == null ||
                    getUserInfoResp.data.online == null : "Fail";

            System.out.println("Missed some fields");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case10() throws IOException {
        System.out.println("Case 10: Missing address/city/country");
        LoginResp loginResp = LogIn.getInfoFromServer("0968823005", "abcdef");
        String token = loginResp.data.token;
        String user_id = "";
        GetUserInfoResp getUserInfoResp = getInfoFromServer(token, user_id);
        if (getUserInfoResp.data.country != null) {
            if (getUserInfoResp.data.city != null) {
                if (getUserInfoResp.data.address != null) {
                    System.out.println("Address: " + getUserInfoResp.data.address + ", "
                            + getUserInfoResp.data.city + ", "
                            + getUserInfoResp.data.country);
                }
                else System.out.println("Address: " + getUserInfoResp.data.city + ", " + getUserInfoResp.data.country);
            }
            else System.out.println("Address: " + getUserInfoResp.data.country);
        }
        else System.out.println("Address: None");
    }
}

