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
	
	public ObservableList<Song> getPlaylist() {
		return this.playlist;
	}
	
	public int getNumSongs() {
		return playlist.size();
	}
	
	@Override
	public String toString() {
		String result = "";
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
	
	public PlayList constructPlayList(String name, String s) {
		PlayList p = new PlayList(name);
		String[] songsArray = s.split("\t");
		for (String song: songsArray) {
			if (!"".equals(song)) {
				p.addSong(new Song (song));
			}
		}
		return p;
		
	}
}
