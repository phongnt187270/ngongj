package UnitTest.SetUserInfo;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class FileUploader {
    private final String boundary;
    private static final String LINE_FEED = "\r\n";
    private final HttpURLConnection connection;
    private final OutputStream outputStream;
    private final PrintWriter writer;
    public FileUploader(String requestURL, String charset) throws IOException {
        boundary = "===" + System.currentTimeMillis() + "===";
        URL url = new URL(requestURL);
        connection = (HttpURLConnection) url.openConnection();
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        outputStream = connection.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
    }
    public void addFilePath(String fieldName, File uploadFile) throws IOException {
        String fileName = uploadFile.getName();
        writer.append("--").append(boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"").append(fieldName).append("\"; filename=\"").append(fileName).append("\"")
                .append(LINE_FEED);
        writer.append("Content-Type: ").append(URLConnection.guessContentTypeFromName(fileName))
                .append(LINE_FEED);
        writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.flush();

        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();

        writer.append(LINE_FEED);
        writer.flush();
    }
    public SetUserInfoResp finish() throws IOException {
        writer.append(LINE_FEED).flush();
        writer.append("--").append(boundary).append("--").append(LINE_FEED);
        writer.close();
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
        return g.fromJson(java_string_content, SetUserInfoResp.class);
    }
}
