import java.util.ArrayList;

public class Album {
    private String album_name;
    private String band_name;
    private ArrayList<Song> songs = new ArrayList<Song>();

    public Album(String album_name, String band_name) {
        this.album_name = album_name;
        this.band_name = band_name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void add_song_to_album( String song_name, double song_duration){
        Song new_song = new Song(song_name,song_duration);
        songs.add(new_song);
    }

    public void print_songs_in_album(){
        for(Song checkedSong: this.songs){
            System.out.println(checkedSong.getTitle()+", duration:"+checkedSong.getDuration());
        }
    }

    public Song find_song_in_album(String song_name){
        for(int i=0;i<songs.size();i++){
            if(songs.get(i).getTitle().equals(song_name)){
                return songs.get(i);
            }
        }
        return null;
    }


}
