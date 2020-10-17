package UnitTest.LogIn;

import UnitTest.JsonReader;
import com.google.gson.Gson;

import static UnitTest.Server.dataBase;
import static UnitTest.Server.token;

public class LoginResp {
    public InputData data;
    public String code;
    public String message;
    public LoginResp(String json) {
        data = new Gson().fromJson(json, InputData.class);
    }
    public String getCode() {
        String phonenumber = data.phonenumber;
        String password = data.password;
        String devtoken = data.tokenID;
        boolean validPhonenumber = dataBase.containsKey(phonenumber);
        boolean validPassword = password.equals(dataBase.get(phonenumber));
        boolean validToken = token.contains(devtoken);
        if (validPhonenumber && validPassword && validToken) {
            this.message = "OK";
            this.code = "1000";
        }
        if (!validPhonenumber) {
            this.message = "User not existed";
        }
        //if ()
        return this.code;
    }
}
