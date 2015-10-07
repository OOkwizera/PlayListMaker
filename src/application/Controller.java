package application;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import application.net.*;

public class Controller {

	private Catalog catalog = new Catalog();
	private PlayList selectedPlayList;
	private Song selectedSong;
	
	@FXML
	private SplitPane canvas;
	@FXML
	private GridPane playListGrid;
	@FXML
	private GridPane songGrid;
	
	@FXML
	private TextField enterTitle;
	@FXML
	private TextField enterSongName;
	@FXML
	private TextField enterArtist;
	
	@FXML 
	private TableView<PlayList> catalogTable;
	@FXML
	private TableColumn<PlayList, String> playListTitle; 
	@FXML 
	private TableView<Song> playListTable;
	@FXML
	private TableColumn<Song, String> songTitle;
	@FXML
	private TableColumn<Song, String> songArtist;
	
	@FXML
	public void initialize() {
		
		catalogTable.setItems(catalog.getCatalog());
		
		playListTitle.setCellValueFactory(
				cellData -> cellData.getValue().titleProperty());
		songTitle.setCellValueFactory( 
				cellData -> cellData.getValue().getSongNameProperty());
		
		songArtist.setCellValueFactory(
				cellData -> cellData.getValue().getArtistProperty());

		
		canvas.setOnKeyPressed(k -> handlePress(k.getCode()));
		
		playListTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> setSelectedSong(newValue));
		
		catalogTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> setSelectedPlayList(newValue));
	   
	}
	
	public void setSelectedPlayList(PlayList playList) {
		selectedPlayList = playList;
		if (selectedPlayList != null) {
			playListTable.setItems(selectedPlayList.getPlaylist());
		}
		else {
			playListTable.setItems(null);
		}
	}
	
	public void setSelectedSong(Song song) {
		selectedSong = song;
	}
	
	@FXML
	public void handlePress(KeyCode code) {
		if (code == KeyCode.ENTER) {
			addPlayList();
			addSong();
		}
		
	}
	
	public void deletePlayList(){
		if (selectedPlayList != null && catalog.getCatalog().contains(selectedPlayList)) {
			selectedPlayList.clear();
			catalog.removePlayList(selectedPlayList);
			
		}
	}
	
	public void deleteSong(){
		if (selectedSong != null && selectedPlayList.getPlaylist().contains(selectedSong)) {
			selectedPlayList.removeSong(selectedSong);
		
		}
	} 
	
	public void addPlayList(){
		if (!enterTitle.getText().isEmpty()) {
			PlayList playList = new PlayList(enterTitle.getText()); 
			catalog.addPlayList(playList);
			enterTitle.clear();
		}
	}
	
	public void editPlayList(){}
	
	public void addSong(){ 
		if (!enterSongName.getText().isEmpty() && selectedPlayList != null) {
			Song song = new Song(enterSongName.getText(), enterArtist.getText()); 
			selectedPlayList.addSong(song);
			enterSongName.clear();
			enterArtist.clear();
		}
	}
	
	public void editSong(){}
	
	public void sharePlayList(){}
	
}
