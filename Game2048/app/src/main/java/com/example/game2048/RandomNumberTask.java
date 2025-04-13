package com.example.game2048;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RandomNumberTask {
    // Используем публичное API, возвращающее число (например, random.org)
    // Пример URL: https://www.random.org/integers/?num=1&min=1&max=100&col=1&base=10&format=plain&rnd=new
    public static int getRandomNumber() {
        int number = 50; // значение по умолчанию
        try {
            URL url = new URL("https://www.random.org/integers/?num=1&min=1&max=100&col=1&base=10&format=plain&rnd=new");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine = in.readLine();
            in.close();
            number = Integer.parseInt(inputLine.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }
}
