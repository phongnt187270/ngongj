package UnitTest;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	public static final String SIGN_UP = "";
	public static String LOG_IN = "https://hust-fb-it4895.herokuapp.com/it4788/login";
	public static Map<String, String> response_code = new HashMap<>() {{
		put("1000", "OK");
		put("9992", "Post is not existed");
		put("9993", "Verify code is incorrect");
		put("9994", "No data or end of list data");
		put("9995", "User is not validated");
		put("9996", "User existed");
		put("9997", "Method is invalid");
		put("9998", "Token is invalid \n" + "Moving to log in...");
		put("9999", "Exception error");
		put("1001", "Can not connect to DB");
		put("1002", "Parameter is not enough");
		put("1003", "Parameter type is invalid");
		put("1004", "Parameter value is invalid");
		put("1005", "Unknown error");
		put("1006", "File size is too big");
		put("1007", "Upload File Failed!");
		put("1008", "Maximum number of images");
		put("1009", "Not access");
		put("1010", "Action has been done previously by this user");
		put("1011", "Could not publish this post");
		put("1012", "Limited access");
	}};
	public static String Set_Read_Notification = "https://hust-fb-it4895.herokuapp.com/it4788/set_read_notification";
	public static String Get_Notification = "https://hust-fb-it4895.herokuapp.com/it4788/get_notification";
	public static String Get_User_Info = "https://hust-fb-it4895.herokuapp.com/it4788/get_user_info";
	public static String Set_User_Info = "https://hust-fb-it4895.herokuapp.com/it4788/set_user_info";
}
