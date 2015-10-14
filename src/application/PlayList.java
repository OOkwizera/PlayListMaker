package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayList {
	private ObservableList<Song> playlist;
	private StringProperty title;

	public PlayList(String name) {
		if (name.equals("")) {
			this.title = new SimpleStringProperty("No Title");
		} else {
			this.title = new SimpleStringProperty(name);
		}
		this.playlist  = FXCollections.observableArrayList();
	}
	
	public PlayList(PlayList other) {
		this.title = new SimpleStringProperty(other.getTitle());
		this.playlist = FXCollections.observableArrayList();
		for (int i = 0; i < other.getPlaylist().size(); i++) {
			String nameCopy = other.getPlaylist().get(i).getSongName();
			String artistCopy = other.getPlaylist().get(i).getArtist();
			Song songCopy = new Song(nameCopy, artistCopy);
			playlist.add(songCopy);
		}
	}
	
	public StringProperty titleProperty() {
		return title;
	}
	
	public String getTitle() {
		return title.get();
	}
	
	public void setTitle(String name) {
		this.title.set(name);
	}
	
	public void addSong(Song s) {
		playlist.add(s);
	}
	
	public void removeSong(Song s) {
		playlist.remove(s);
	}
	
	public void clear() {
		playlist.clear();
	}
	
	//unnecessary comment
	
	public ObservableList<Song> getPlaylist() {
		return this.playlist;
	}
	
	public int getNumSongs() {
		return playlist.size();
	}
	
	@Override
	public String toString() {
		String result = getTitle() + "\t";
		for (Song song: this.getPlaylist() ) {
			result += song.toString();
			result += "\t";
		}
		return result;
	}
	
	public boolean equals(PlayList p) {
		return this.toString().equals(p.toString());
	}
	
	public int hashCode() { return this.toString().hashCode(); }
	
}
