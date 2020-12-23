package UnitTest.SetUserInfo;
import UnitTest.Constant;
import UnitTest.GetUserInfo.GetUserInfo;
import UnitTest.GetUserInfo.GetUserInfoResp;
import UnitTest.LogIn.LogIn;
import UnitTest.LogIn.LoginResp;

import java.io.File;
import java.io.IOException;


public class SetUserInfo {
    public static void main(String[] args) throws IOException {
            case1();
//            case2();
//            case3();
//            case4();
//            case5();
//            case6();
//            case7();
//            case8();
//            case9();
//            case10();
//            case11();
    }
    public static SetUserInfoResp getInfoFromServer(String token, String username, String description, String address, String city,
                                                    String country, String link, String avatar, String cover_image) throws IOException {
        String requestURL = Constant.Set_User_Info + "?token=" + token
                + "&username=" + username
                //+ "&description="
                //+ description + "&address="
                //+ address + "&city=" + city + "&country="
                //+ country + "&link=" + link
        ;
        String charset = "UTF-8";
        FileUploader uploader = new FileUploader(requestURL, charset);
        if (!avatar.isEmpty()) {
            File avatar_link = new File(avatar);
            uploader.addFilePath("avatar", avatar_link);
        }
        if (!cover_image.isEmpty()) {
            File cover_image_link = new File(cover_image);
            uploader.addFilePath("cover_image", cover_image_link);
        }

        return uploader.finish();

    }
    public static void case1() throws IOException {
        System.out.println("Case 1: Success");
        LoginResp loginResp = LogIn.getInfoFromServer("0968823005", "abcdef");
        String token = loginResp.data.token;
        SetUserInfoResp setUserInfoResp = getInfoFromServer(token, "NamNgo1", "",
                "", "", "", "",
                "", ""
                //"C:/Users/NamNgo/Pictures/frontFace.jpg"
        );
        try {
            assert "1000".equals(setUserInfoResp.code) : "Fail";
            System.out.println("Success");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case2() throws IOException {
        System.out.println("Case 2: Invalid token");
        SetUserInfoResp setUserInfoResp = getInfoFromServer("abc", "", "", "", "",
                "", "", "", "");
        try {
            assert "9998".equals(setUserInfoResp.code) : "Fail";
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
        SetUserInfoResp setUserInfoResp = getInfoFromServer(token, "", "", "", "",
                "", "", "", "");
        try {
            assert "1001".equals(setUserInfoResp.code) : "Fail";
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
        System.out.println("Case 5: Invalid user name");
        LoginResp loginResp = LogIn.getInfoFromServer("0968823005", "abcdef");
        String token = loginResp.data.token;
        String user_name = "&NamNgo=";
        SetUserInfoResp setUserInfoResp = getInfoFromServer(token, user_name, "Main",
                "LinhDam", "HN", "VN", "https://www.facebook.com/profile.php?id=100015232384578",
                "C:/Users/NamNgo/Pictures/ITer.jpg", "C:/Users/NamNgo/Pictures/frontFace.jpg");
        try {
            assert !"1000".equals(setUserInfoResp.code) : "Fail";
            System.out.println("Invalid user name");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case6() throws IOException {
        System.out.println("Case 6: Invalid user name and server fix automatically");
        LoginResp loginResp = LogIn.getInfoFromServer("", "");
        String token = loginResp.data.token;
        String user_name = "";
        SetUserInfoResp setUserInfoResp = getInfoFromServer(token, "", "", "", "",
                "", "", "", "");
        GetUserInfoResp getUserInfoResp = GetUserInfo.getInfoFromServer(token, loginResp.data.id);
        try {
            assert !user_name.equals(getUserInfoResp.data.username) : "Fail";
            System.out.println("Your user name is invalid, and we fixed it to right form");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case7() throws IOException {
        System.out.println("Case 7: System does not active in user's country");
        LoginResp loginResp = LogIn.getInfoFromServer("0968823005", "abcdef");
        String token = loginResp.data.token;
        SetUserInfoResp setUserInfoResp = getInfoFromServer(token, "NamNgo", "Main",
                "LinhDam", "HN", "NorthKorea",
                "https://www.facebook.com/profile.php?id=100015232384578",
                "C:/Users/NamNgo/Pictures/ITer.jpg", "C:/Users/NamNgo/Pictures/frontFace.jpg");
        try {
            assert "System does not active in your country".equals(setUserInfoResp.message) : "Fail";
            System.out.println("Logging out...");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case8() throws IOException {
        System.out.println("Case 8: description field is too long");
        LoginResp loginResp = LogIn.getInfoFromServer("0968823005", "abcdef");
        String token = loginResp.data.token;
        String description = "12345678901234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890" + "12345678901234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890" + "12345678901234567890123456789012345678901234567890";
        //assert 150 > description.length() : description;
        SetUserInfoResp setUserInfoResp = getInfoFromServer(token, "NamNgo", description,
                "LinhDam", "HN", "VN",
                "https://www.facebook.com/profile.php?id=100015232384578",
                "C:/Users/NamNgo/Pictures/ITer.jpg", "C:/Users/NamNgo/Pictures/frontFace.jpg");
        try {
            assert "1003".equals(setUserInfoResp.code) : "Fail";
            System.out.println("Your description may not be greater than 150 characters");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case9() throws IOException {
        System.out.println("Case 9: Missing link(s)");
        LoginResp loginResp = LogIn.getInfoFromServer("", "");
        String token = loginResp.data.token;
        SetUserInfoResp setUserInfoResp = getInfoFromServer(token, "", "", "", "",
                "", "", "", "");
        try {
            assert "1000".equals(setUserInfoResp.code) : "Fail";
            assert setUserInfoResp.data.avatar == null ||
                    setUserInfoResp.data.cover_image == null ||
                    setUserInfoResp.data.link == null : "Fail";
//            System.out.println("Success");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case10() throws IOException {
        System.out.println("Case 10: Forbidden link");
        LoginResp loginResp = LogIn.getInfoFromServer("0968823005", "abcdef");
        String token = loginResp.data.token;
        SetUserInfoResp setUserInfoResp = getInfoFromServer(token, "NamNgo1", "Main",
                "LinhDam", "HaNoi", "VietNam", "vnhackers.com",
                "C:/Users/NamNgo/Pictures/ITer.jpg", "C:/Users/NamNgo/Pictures/frontFace.jpg");
        try {
            assert "1003".equals(setUserInfoResp.code) : "Fail";
            System.out.println("Your link is forbidden");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case11() throws IOException {
        System.out.println("Case 11: Missing fields");
        LoginResp loginResp = LogIn.getInfoFromServer("", "");
        String token = loginResp.data.token;
        SetUserInfoResp setUserInfoResp = getInfoFromServer(token, "", "", "", "",
                "", "", "", "");
        try {
            assert setUserInfoResp.data.avatar == null ||
                    setUserInfoResp.data.cover_image == null ||
                    setUserInfoResp.data.link == null ||
                    setUserInfoResp.data.city == null ||
                    setUserInfoResp.data.country == null : "Fail";
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
}