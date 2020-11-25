package UnitTest;


import java.io.*;
import java.net.URL;
public class JsonReader {
    public JsonReader(){}
    public static String readJsonFromUrl(URL url) throws Exception {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                sb.append(chars, 0, read);
            }
            return sb.toString();

        }
        finally {
            if (reader != null) reader.close();
        }
    }
}
