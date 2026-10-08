package io.github.gabrieln.indexify.spotify;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author gabri
 */
public class SpotifyApi {

    private final HttpClient httpClient;
    private final SpotifyAuth auth;
    //private final ObjectMapper objectMapper;
    //private JsonNode json = null;


    public SpotifyApi (SpotifyAuth auth) {
        this.auth = auth;
        this.httpClient = HttpClient.newHttpClient();
        //this.objectMapper = objectMapper;
    }

    public String getPlaylist(String playlistId) throws Exception{

        String tokenJson = auth.getAccessToken();
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode json = objectMapper.readTree(tokenJson);
        String accessToken = json.get("access_token").asText();
            
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.spotify.com/v1/playlists/" + playlistId + "/items"))
        .header("Authorization", "Bearer " + accessToken).GET().build();
        
        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        
        );


        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "Spotify API request failed: "
                    + response.statusCode()
                    + " "
                    + response.body()
            );
        }
        
        return response.body();
    }

}
