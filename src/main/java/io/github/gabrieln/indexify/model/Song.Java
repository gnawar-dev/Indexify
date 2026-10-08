package io.github.gabrieln.indexify.model;

public class Song {

    private int index;
    private String songName;
    private String artistName;

    public Song(int index, String songName, String artistName) {

        this.index = index;
        this.songName = songName;
        this.artistName = artistName;

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {

        return index + " " + songName + " - " + artistName;
    }
}