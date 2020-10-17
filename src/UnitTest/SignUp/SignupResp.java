package UnitTest.SignUp;

import com.google.gson.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static UnitTest.Server.dataBase;

public class SignupResp {
    public InputData data;
    public String code;
    public String message;
    Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");

    public SignupResp(String json) {
        data = new Gson().fromJson(json, InputData.class);
    }
    public String getCode() {
        String phonenumber = this.data.phonenumber;
        String password = this.data.password;
        Matcher matcher = pattern.matcher(password);
        boolean userExisted = dataBase.containsKey(phonenumber);
        boolean validPhonenumber = phonenumber.length() == 10 && phonenumber.charAt(0) == '0';
        boolean validPassword = !phonenumber.equals(password) && password.length() <= 10 && password.length() >= 6 && matcher.matches();
        if (!userExisted && validPhonenumber && validPassword) {
            this.message = "OK";
            this.code = "1000";
        }
        else if (userExisted && validPassword) {
            this.message = "User existed";
            this.code = "9996";
        }
        else if (!validPhonenumber && validPassword) {
            this.message = "Invalid phone number";
            this.code = "";
        }
        else if (validPhonenumber && !validPassword) {
            this.message = "Invalid password";
            this.code = "";
        }
        else if (phonenumber.length() == 0 && password.length() == 0) {
            this.message = "You have not enter phone number and password";
            this.code = "";
        }
        return this.code;
    }

}
