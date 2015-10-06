package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Catalog {
	private ObservableList<PlayList> pLists;

	public Catalog() {
		pLists = FXCollections.observableArrayList();
	}
	
	public void addPlayList(PlayList p) {
		pLists.add(p);
	}
	
	public void removePlayList(PlayList p) {
		pLists.remove(p);
	}
	
	public int size() {
		return pLists.size();
	}
	
	public ObservableList<PlayList> getCatalog() {
		return this.pLists;
	}
}
