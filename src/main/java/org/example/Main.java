package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {

        String nome = "joão";
        HttpURLConnection conn = getRequest("https://api.agify.io/?name="+nome);

        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();

            String inputLine = bufferedReader.readLine();
            while ( inputLine != null) {
                stringBuffer.append(inputLine);
                inputLine = bufferedReader.readLine();
            }
            bufferedReader.close();

            ObjectMapper objectMapper = new ObjectMapper();
            Pessoa pessoa = objectMapper.readValue(stringBuffer.toString(), Pessoa.class);

            System.out.println(pessoa);

        } else {
            System.out.println("GET request not worked");
        }
    }

    public static HttpURLConnection getRequest(String url) throws IOException {
        URL uri = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setRequestMethod("GET");
        return conn;
    }
}