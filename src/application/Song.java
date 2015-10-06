package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Song {
	private StringProperty songName;
	private StringProperty artist;

	public Song(String title, String name) {
		this.songName = new SimpleStringProperty(title);
		if (name == null) {
			this.artist = new SimpleStringProperty("Unknown");
		} else {
			this.artist = new SimpleStringProperty(name);
		}
	}
	public Song (String s) {
		String[] names = s.split(":");
		if (names.length >= 2) {
			this.songName = new SimpleStringProperty(names[0]);
			this.artist = new SimpleStringProperty(names[1]);
		} else {
			this.songName = new SimpleStringProperty(names[0]);
			this.artist = new SimpleStringProperty("Unknown");
		}
	}
	
	public String getSongName() {
		return songName.get();
	}
	
	public StringProperty getSongNameProperty() {
		return songName;
	}
	
	public void setSongName(String name) {
		this.songName.set(name);
	}
	
	public String getArtist() {
		return artist.get();
	}
	
	public StringProperty getArtistProperty() {
		return artist;
	}
	
	public void setArtist(String name) {
		this.artist.set(name);
	}
	
	
	@Override
	public String toString() {
		String result = "";
		result = result + getSongName() + ":" + getArtist();
		return result;
	}
	
	public boolean equals(Song s) {
		return this.toString().equals(s.toString());
	}
	
	public int hashCode() {
		return this.toString().hashCode();
	}
	
}
