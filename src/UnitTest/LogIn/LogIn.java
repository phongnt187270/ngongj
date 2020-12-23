package UnitTest.LogIn;

import UnitTest.Constant;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LogIn {
    public static void main(String[] args) throws Exception {

    }
    public static LoginResp getInfoFromServer(String phonenumber, String password) throws IOException {
        URL url = new URL(Constant.LOG_IN + "?phonenumber=" + phonenumber + "&password=" + password);
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

            return g.fromJson(java_string_content, LoginResp.class);
        }
        finally {
            connection.disconnect();
        }
    }
    public static void case1() throws IOException {
        System.out.println("Case 1: Success");
        LoginResp loginResp = getInfoFromServer("0968823005", "abcdef");
        try {
            assert "1000".equals(loginResp.code) : "Fail";
            System.out.println("Success");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case2() throws IOException {
        System.out.println("Case 2: Phone number has not signed up");
        LoginResp loginResp = getInfoFromServer("0968823007", "abcdef");
        try {
            assert "1000".equals(loginResp.code) : "Fail";
            System.out.println("Success");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case3() throws IOException {
        System.out.println("Case 3: Invalid phone number");
        LoginResp loginResp = getInfoFromServer("968823005", "abcdef");
        try {
            assert "1000".equals(loginResp.code) : "Fail";
            System.out.println("Invalid phone number");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case4() throws IOException {
        System.out.println("Case 4: Invalid password");
        LoginResp loginResp = getInfoFromServer("0968823005", "a");
        try {
            assert "1000".equals(loginResp.code) : "Fail";
            System.out.println("Wrong password");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case5() throws IOException {
        System.out.println("Case 5: Phone number and password is null");
        LoginResp loginResp = getInfoFromServer("", "");
        try {
            assert "1000".equals(loginResp.code) : "Fail";
            System.out.println("You have not enter phone number and password");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case6() throws IOException {
        System.out.println("Case 1: Success");
        LoginResp loginResp = getInfoFromServer("0968823005", "abcdef");
//        try {
//            assert "1000".equals(loginResp.code) : "Fail";
//            System.out.println("Success");
//        }
//        catch (AssertionError e) {
//            e.printStackTrace();
//        }
    }
    public static void case7() throws IOException {
        System.out.println("Case 7: Phone number and password are the same");
        LoginResp loginResp = getInfoFromServer("0968823005", "0968823006");
        try {
            assert "1000".equals(loginResp.code) : "Fail";
            System.out.println("Success");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
//    public static void case8() throws IOException {
//        System.out.println("Case 1: Success");
//        LoginResp loginResp = getInfoFromServer("0968823005", "abcdef");
//        try {
//            assert "1000".equals(loginResp.code) : "Fail";
//            System.out.println("Success");
//        }
//        catch (AssertionError e) {
//            e.printStackTrace();
//        }
//    }
}
