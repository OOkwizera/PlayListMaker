package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Catalog {
	private ObservableList<PlayList> pLists;

	public Catalog() {
		pLists = FXCollections.observableArrayList();
		pLists.add(new PlayList("New Playlist"));
	}
	
	public Catalog(Catalog other) {
		pLists = FXCollections.observableArrayList();
		for (int i = 0; i < other.getCatalog().size(); i++) {
			PlayList copy = new PlayList(other.getCatalog().get(i));
			pLists.add(copy); 
		}
	}
	
	public void addPlayList(PlayList p) {
		pLists.add(p);
	}
	
	public void removePlayList(PlayList p) {
		if (size() > 1) {
			pLists.remove(p);
		}
	}
	
	public int size() {
		return pLists.size();
	}
	
	public ObservableList<PlayList> getCatalog() {
		return this.pLists;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (PlayList p : getCatalog()) {
			result += p.toString() + "$";
		}
		return result;
	}
	
	public boolean equals(Catalog p) {
		return this.toString().equals(p.toString());
	}
	
	public int hashCode() { return this.toString().hashCode(); }
	

	public void addPlayListFrom(String play) {
		String[] arrayPlayList = play.split("\t");
		String playName = arrayPlayList[0];
		PlayList p = new PlayList(playName);
		if (arrayPlayList.length > 1) {
			for (int i = 1; i < arrayPlayList.length; i++) {
				Song s = new Song(arrayPlayList[i]);
				p.addSong(s);
			}
		}
		this.addPlayList(p);
		
	}
}

