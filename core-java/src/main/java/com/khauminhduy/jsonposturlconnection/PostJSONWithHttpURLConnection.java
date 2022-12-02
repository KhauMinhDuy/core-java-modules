package com.khauminhduy.jsonposturlconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PostJSONWithHttpURLConnection {

	public static void main(String[] args) throws IOException {
		URL url = new URL("https://reqres.in/api/users");

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");

		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		connection.setRequestProperty("Accept", "application/json");

		connection.setDoOutput(true);

		// JSON String need to be constructed for the specific resource.
		// We may construct complex JSON using any third-party JSON libraries such as
		// jackson or org.json
		String jsonInputString = "{\"name\": \"DK\", \"job\": \"Programmer\"}";

		try (OutputStream os = connection.getOutputStream();) {
			byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);
		}

		int code = connection.getResponseCode();
		System.out.println(code);

		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println(response.toString());
		}

	}

}
