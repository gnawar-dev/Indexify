package io.github.gabrieln.indexify.model;

import java.util.ArrayList;;

public class Playlist{

    private ArrayList<Song> songs = new ArrayList<>();
    private String playlistName;
    private String uri;
    private String path;
    
    public Playlist() {
    }

    public void setSong(Song song) {
        songs.add(song);
    }

    public ArrayList<Song> getSong() {
        return songs;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
    
    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    
}