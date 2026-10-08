package io.github.gabrieln.indexify.spotify;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;



/**
 *
 * @author gabri
 */


public class SpotifyAuth {

  private static final String TOKEN_URL = "https://accounts.spotify.com/api/token";

  private final String clientId;
  private final String clientSecret;
  private final HttpClient httpClient;

  public SpotifyAuth(String clientId, String clientSecret, HttpClient httpClient) {

    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.httpClient = httpClient;

  }

  public String getAccessToken() throws Exception {

        // System.out.println("Client ID: " + System.getenv("SPOTIFY_CLIENT_ID"));
        // System.out.println("Client Secret exists: " +
        // (System.getenv("SPOTIFY_CLIENT_SECRET") != null));

    String credentials = clientId.trim() + ":" + clientSecret.trim();
    String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
    String body = "grant_type=client_credentials";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TOKEN_URL))
                .header(
                        "Authorization",
                        "Basic " + encodedCredentials
                )
                .header(
                        "Content-Type",
                        "application/x-www-form-urlencoded"
                )
                .POST(
                        HttpRequest.BodyPublishers.ofString(body)
                )
                .build();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "Spotify authentication failed: "
                    + response.statusCode()
                    + " "
                    + response.body()
            );
        }
        return response.body();

  }

}
