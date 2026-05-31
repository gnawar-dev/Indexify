package io.github.gabrieln.indexify.model;

import java.io.File;

public class Playlist implements Song {

    private Song song;
    private String playlistName;
    
    public Playlist(Song song, String playlistName, String path) {
        this.song = song;
        this.playlistName = playlistName;
        this.path = path;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public song getSong() {
        return song;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public void setPath(string path) {
        this.path = path;
    }
}