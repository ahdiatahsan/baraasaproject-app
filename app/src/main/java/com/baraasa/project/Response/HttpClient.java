//package com.example.baraasaproject.Response;
//
//import android.content.Context;
//
//import java.io.BufferedReader;
//import java.io.IOError;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//public class HttpClient {
//    Context context;
//    private String url, method = "GET", data = null, response = null;
//    private Integer statusCode = 0;
//    private Boolean token = false;
//    private LocalStorage localStorage;
//
//    public HttpClient(Context context, String url) {
//        this.context = context;
//        this.url = url;
//        localStorage = new LocalStorage(context);
//    }
//
//    public void setMethod(String method){
//        this.method = method.toUpperCase();
//    }
//
//    public void setData(String data) {
//        this.data = data;
//    }
//
//    public void setToken(Boolean token) {
//        this.token = token;
//    }
//
//    public String getResponse() {
//        return response;
//    }
//
//    public Integer getStatusCode() {
//        return statusCode;
//    }
//
//    public void send() {
//        try {
//            URL sUrl = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) sUrl.openConnection();
//            connection.setRequestMethod(method);
//            connection.setRequestProperty("Content_Type","application/json");
//            connection.setRequestProperty("X-Requested_With","XMLHttpRequest");
//            if (token){
//                connection.setRequestProperty("Authourization","Bearer"+localStorage.getToken());
//            }
//            if(!method.equals("GET")){
//                connection.setDoOutput(true);
//            }
//            if (data != null){
//                OutputStream outputStream = connection.getOutputStream();
//                outputStream.write(data.getBytes());
//                outputStream.flush();
//                outputStream.close();
//            }
//            statusCode = connection.getResponseCode();
//
//            InputStreamReader inputStreamReader;
//            if (statusCode >= 200 && statusCode <= 299){
//                inputStreamReader = new InputStreamReader(connection.getInputStream());
//            } else {
//                inputStreamReader = new InputStreamReader(connection.getErrorStream());
//            }
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            StringBuffer stringBuffer = new StringBuffer();
//            String line;
//            while ((line = bufferedReader.readLine())!= null){
//                stringBuffer.append(line);
//            }
//            bufferedReader.close();
//            response =stringBuffer.toString();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//}
