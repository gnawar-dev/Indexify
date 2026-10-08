package io.github.gabrieln.indexify;

import java.net.http.HttpClient;

import io.github.gabrieln.indexify.spotify.SpotifyApi;
import io.github.gabrieln.indexify.spotify.SpotifyAuth;
import io.javalin.Javalin;

/**
 *
 * @author gabri
 */
public class Main {

    public static void main(String[] args) {

        HttpClient httpClient = HttpClient.newHttpClient();
        SpotifyAuth spotifyAuth = new SpotifyAuth(
        System.getenv("SPOTIFY_CLIENT_ID"),
        System.getenv("SPOTIFY_CLIENT_SECRET"),
        httpClient
        );

        SpotifyApi spotifyApi = new SpotifyApi(spotifyAuth);

        Javalin app = Javalin.create().start(8000);
        
        try {
            System.out.println(spotifyApi.getPlaylist("0ADUUUuZGsYQnZMJfTS3am"));
        } catch (Exception ex) {
            System.getLogger(Main.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        
    }
}
