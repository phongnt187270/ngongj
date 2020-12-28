package UnitTest.Search;

import UnitTest.Constant;
import UnitTest.LogIn.LogIn;
import UnitTest.LogIn.LoginResp;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchResp {
    public String code;
    public String message;
    public Data data;
}
