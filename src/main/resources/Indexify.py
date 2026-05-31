#dependecies
import csv
import os
from colorama import Fore
import re


def main():

        print("Welcome to INDEXIFY.\n " + "Pick an option:")
        while True:
            try:
                print("1: Index a playlist")
                print("2: Quit")
                #choice = int(input())
                choice = 1
                if choice == 1:
                    indexer()
                if choice == 2:
                    exit()
                else:
                    print("Enter a valid choice!")
                break
            except ValueError:
                print("Invalid input!")
                print("Pick one of the following:")
                break

def indexer():

    ILLEGAL_CHARACTERS = r'[/\:*?"<>|]'
    playlist_list = [] # will be used to organise the songs in the playlist based on their playlist position
    SongAndArtist = ""
    SPOTIFY_ID = "" # the spotify song ID from that row in the CSV
    Absent_songs = {} # dictionary of the songs that are not in the playlist. will be in format of "{songtitle - artist}:{spotify link}"
    absentSongsString = ""

    # this block of code takes all the entries in the playlist and categorises them based off the order they were added in and adds each entry to a list.
    
    playlist_csv = str(input("Please input the location of the CSV you wish to read:    ")) # obtains the path of the playlist CSV so that it can be opened.

    #playlist_csv = str(r"C:\Users\gabri\Desktop\ph0nk_radio.csv")

    if playlist_csv[0] and playlist_csv[-1] == '"':
        playlist_csv = playlist_csv[1:-1]

    with open(playlist_csv, 'r', encoding="utf-8") as playlist: # reads the playlist csv as denoted by 'r'
        PlaylistReader = csv.reader(playlist)
        next(PlaylistReader) # skips the first row which has the header of each column.
    
        for index, row in enumerate(PlaylistReader): # enumerate allows for access to the index of the element, and the element itself.
            song = ""
            
            i_0 = "0" # to be added to the start of double digit indexes for added robustness, e.g. 016
            i_00 = "00" # to be added to the start of single digit indexes for added robustness, e.g. 001
            SPOTIFY_ID = ""
            SPOTIFY_ID+=(row[0][14:-1])
            index +=1 # increments the index by 1 so that the last item denotes the number of items in the CSV file
            
            if index < 10:   
                index = str(index) # turns index into a string so it can be added to the end of i_0
                item_index = (i_00+(index))
                item_name = (row[1])
            elif index >=10:
                index = str(index) # turns index into a string so it can be added to the end of i_00
                item_index = (i_0+(index))
                item_name = (row[1])

            song = item_index +" "+ item_name # format e.g. 001 Songname
            artist = row[3]
            song_id = row[0][14:-1]
            playlist_list.append(song)
            Absent_songs[item_name] = artist
        print(f"absent song dict: {Absent_songs}")
    playlist_directory = (str(input(r"Please input desired playlist location:   ")))

    #playlist_directory = r"C:\Users\gabri\Desktop\SpotiDownloader.com - PH0NK Radio"

    if playlist_directory[0] and playlist_directory[-1] == '"':
        playlist_directory = playlist_directory[1:-1]

    os.chdir(playlist_directory) # change working directory to the playlist directory
    
    for songs in playlist_list:   
        found = False
        songtitle = songs[4:len(songs)]
        for file in os.listdir():
            
            file_name, ext = os.path.splitext(file)
            new_name = songs + ext

            if songtitle.lower() == file_name.lower():

                os.rename(file, new_name)
                print(f"{Fore.GREEN}indexed {songtitle} as {new_name}.")
                found = True
                break
            
            elif (file_name.lower() + ext) == new_name.lower():
                print(f"{Fore.YELLOW}'{songtitle.title()} already indexed!'")
                found = True
                break

        if not found:
            print(f"{Fore.RED}'{songtitle}' not found!")
            # Missing_songs = open("000 Missing Songs.txt", "a")

            # print(Absent_songs.get(songtitle))

            # artist = Absent_songs.get(songtitle.strip())
            # if artist == None:
            #     artist = "[NO ARTIST AVAILABLE]"
            # SongAndArtist = f"{songtitle + " - " + artist}"

            #absentSongsString += (f"{SongAndArtist}\n")
            #print(f"song id2 = {song_id}")

            #print(songtitle)
           # Missing_songs.write(f"Song: {SongAndArtist}" + "\nLink: https://open.spotify.com/track/" + f"{song_id}\n \n")

            
            # Debugging
            #print(playlist_list)
            # print("Row: ")
            # print(row)
            # print("\nSpotify ID: ")
            # print(SPOTIFY_ID)
            # print("\nSongAndArtist: " + SongAndArtist)
            
            # {row[0][14:len(row[0])]}


            #Missing_songs.write(f"Song: {item_name} , Link: https://open.spotify.com/track/{row[0][14:len(row[0])]}") ## error/bug
            #print(f"Song: {songtitle} , Link: https://open.spotify.com/track/") ## error/bug
            #print(f"{row[1]} - {SPOTIFY_ID}")
    #print(new_name)




if __name__ == "__main__":
    main()


