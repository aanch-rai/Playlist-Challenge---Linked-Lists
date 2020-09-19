import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Album> album_collection = new ArrayList<Album>();
    private static LinkedList<Song> playlist = new LinkedList<Song>();

    public static void main(String[] args) {
        Album album1 = new Album("Ghost Stories", "Coldplay");
        album1.add_song_to_album("Magic", 4.45);
        album1.add_song_to_album("Ink", 3.48);
        album1.add_song_to_album("Always in my Head", 3.37);
        album_collection.add(album1);

        //second album
        Album album2 = new Album("Up all Night", "One Direction");
        if (find_album(album2.getAlbum_name()) != null) {
            System.out.println("Sorry! this Album already exists");
        }
        album2.add_song_to_album("What makes you beautiful!", 3.19);
        album2.add_song_to_album("Gotta be you", 4.04);
        album2.add_song_to_album("One Thing", 3.17);
        album_collection.add(album2);

        boolean main_loop = true;

        while (main_loop) {
            System.out.println("Click on 1 to view all the albums and songs");
            System.out.println("Click on 2 to add song to playlist");
            System.out.println("Click on 3 to exit and navigate");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n");
                    for (Album checkedAlbum : album_collection) {
                        System.out.println(checkedAlbum.getAlbum_name());
                        checkedAlbum.print_songs_in_album();
                        System.out.println("\n");
                    }
                    break;

                case 2:
                    add_to_playlist();
                    break;

                case 3:
                    main_loop = false;
                    break;

                default:
                    System.out.println("Invalid entry");
                    break;
            }
        }

        System.out.println("Now lets navigate the playlist");
        boolean playlist_loop = true;
        boolean going_forward = true;
        ListIterator<Song> it = playlist.listIterator();

        while (playlist_loop) {
            System.out.println("\nEnter 1 to skip song");
            System.out.println("Enter 2 to go to previous song");
            System.out.println("Enter 3 to Replay the current song");
            System.out.println("Enter 4 to view all songs in playlist:");
            System.out.println("Enter 5 to quit");
            System.out.println("Enter 6 to remove current song from record");

            int play_list_choice = scanner.nextInt();
            scanner.nextLine();

            switch (play_list_choice) {
                case 1:
                    if (!going_forward) {
                        if (it.hasNext()) {
                            it.next();
                        }
                        going_forward = true;
                    }

                    if (it.hasNext()) {
                        System.out.println("Playing: " + it.next().toString());
                    } else {
                        System.out.println("Reached last song!");
                        going_forward = false;
                    }
                    break;

                case 2:
                    if (going_forward) {
                        if (it.hasPrevious()) {
                            it.previous();
                        }
                        going_forward = false;
                    }

                    if (it.hasPrevious()) {
                        System.out.println("Playing: " + it.previous().toString());
                    } else {
                        System.out.println("Reached beginning of the song list");
                        going_forward = true;
                    }
                    break;

                case 3:
                    if (!going_forward) {
                        if (it.hasNext()) {
                            System.out.println("Replaying: " + it.next().toString());
                            going_forward = true;

                        } else if (it.hasPrevious()) {
                            System.out.println("Replaying: " + it.previous().toString());
                        }
                    } else {
                        if (it.hasPrevious()) {
                            System.out.println("Replaying: " + it.previous().toString());
                            going_forward = false;
                        } else if (it.hasNext()) {
                            System.out.println("Replaying: " + it.next().toString());
                        }
                    }
                    break;
                    

                case 4:
                    ListIterator<Song> all = playlist.listIterator();
                    while (all.hasNext()) {
                        System.out.println("song: " + (all.nextIndex() + 1) + ":" + all.next().toString());
                    }
                    break;

                case 5:
                    playlist_loop = false;
                    break;

                case 6: if(playlist.size()>0){
                    it.remove();
                    if(it.hasNext()){
                        System.out.println("Now playing:"+it.next().toString());
                    }else if (it.hasPrevious()){
                        System.out.println("Now playing: "+it.previous().toString() );
                    }
                }
                break;

                default:
                    System.out.println("Wrong input");
                    break;
            }

        }

    }


    private static Album find_album(String album_name) {
        for (Album checkedAlbum : album_collection) {
            if (checkedAlbum.getAlbum_name().equals(album_name))
                return checkedAlbum;
        }
        return null;
    }


    private static void add_to_playlist() {
        System.out.println("Enter album name");
        String album_name = scanner.nextLine();
        if (find_album(album_name) == null) {
            System.out.println("Sorry! Album does'nt exist");
            return;
        }
        System.out.println("How may songs do you wish to add from this album");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {

            System.out.println("Enter song name");
            String song_name = scanner.nextLine();
            if (find_album(album_name).find_song_in_album(song_name) == null) {
                System.out.println("Sorry! song not in album!");
                return;
            }
            playlist.add(find_album(album_name).find_song_in_album(song_name));

        }
    }

}
